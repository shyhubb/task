package com.entity.task.security;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Thêm CORS
                .csrf(csrf -> csrf.disable()) // Tắt CSRF cho REST API (thường làm)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ứng
                                                                                                              // dụng
                                                                                                              // không
                                                                                                              // giữ
                                                                                                              // trạng
                                                                                                              // thái
                                                                                                              // session
                                                                                                              // (phù
                                                                                                              // hợp
                                                                                                              // JWT)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll() // Các endpoint liên quan đến xác thực (đăng ký, đăng
                                                                 // nhập) được phép truy cập công khai
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Các endpoint bắt đầu bằng /admin/ CHỈ được
                        // phép cho người dùng có vai trò ADMIN
                        .anyRequest().permitAll()) // TẤT CẢ CÁC YÊU CẦU KHÁC (không khớp /auth/** và /admin/**) được
                                                   // phép truy cập công khai
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Thêm JWT
                                                                                                       // Filter trước
                                                                                                       // bộ lọc xác
                                                                                                       // thực
                                                                                                       // Username/Password
                                                                                                       // mặc định

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // Cho phép tất cả origins (trong môi trường
                                                                    // dev/test)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Cho phép các
                                                                                                   // phương thức HTTP
        configuration.setAllowedHeaders(Arrays.asList("*")); // Cho phép tất cả các header
        configuration.setAllowCredentials(true); // Cho phép gửi cookie, header Authorization
        configuration.setExposedHeaders(Arrays.asList("Authorization")); // Cho phép client đọc header Authorization

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Áp dụng cấu hình CORS cho tất cả các đường dẫn
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bean để mã hóa mật khẩu
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // Bean quản lý xác thực
    }
}
