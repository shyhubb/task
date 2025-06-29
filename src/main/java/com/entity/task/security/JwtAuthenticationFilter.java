package com.entity.task.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.entity.task.service.impl.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetails userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetails userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException { // Simplified throws clause for standard IOException

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String account = null;

        logger.debug("Processing request for URI: {}", request.getRequestURI());
        logger.debug("Received Authorization header: {}", authHeader);

        // Lấy JWT từ header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            logger.debug("Extracted token: {}", token);

            try {
                account = jwtTokenProvider.getAccountFromToken(token);
                logger.debug("Extracted account from token: {}", account);
            } catch (SignatureException ex) {
                logger.error("Invalid JWT signature: {}", ex.getMessage());
                // Handle response for invalid signature if needed (e.g., send 401 Unauthorized)
                // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // return; // Stop filter chain execution
            } catch (MalformedJwtException ex) {
                logger.error("Invalid JWT token: {}", ex.getMessage());
                // Handle response for malformed token
            } catch (ExpiredJwtException ex) {
                logger.error("JWT token is expired: {}", ex.getMessage());
                // Handle response for expired token
            } catch (UnsupportedJwtException ex) {
                logger.error("JWT token is unsupported: {}", ex.getMessage());
                // Handle response for unsupported token
            } catch (IllegalArgumentException ex) {
                logger.error("JWT claims string is empty: {}", ex.getMessage());
                // Handle response for illegal argument (e.g., token is null or empty)
            } catch (Exception ex) { // Catch any other unexpected exceptions during token processing
                logger.error("An unexpected error occurred during JWT processing: {}", ex.getMessage(), ex);
            }
        } else {
            logger.debug("No Bearer token found in Authorization header or header is null.");
        }

        // Xác minh token và lưu thông tin vào SecurityContextHolder
        // Chỉ xử lý nếu có account và chưa có thông tin xác thực nào trong
        // SecurityContextHolder
        if (account != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(account);

                if (jwtTokenProvider.validateToken(token)) { // Modified to pass userDetails for validation
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Lưu vào SecurityContextHolder
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.debug("Authenticated user: {}", account);
                } else {
                    logger.warn("JWT token validation failed for account: {}", account);
                }
            } catch (org.springframework.security.core.userdetails.UsernameNotFoundException ex) {
                logger.warn("User not found from token: {}", account);
            } catch (Exception ex) {
                logger.error("Error setting authentication in security context: {}", ex.getMessage(), ex);
            }
        }

        filterChain.doFilter(request, response);
    }
}