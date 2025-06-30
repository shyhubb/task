package com.entity.task.service.impl;

import org.springframework.stereotype.Service;

import com.entity.task.dto.request.LoginRequest;
import com.entity.task.dto.request.RegisterRequest;
import com.entity.task.entities.Role;
import com.entity.task.entities.User;
import com.entity.task.repository.RoleRepository;
import com.entity.task.repository.UserRepository;
import com.entity.task.security.JwtTokenProvider;
import com.entity.task.service.AuthService;
import com.entity.task.webconstants.WebConstants;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthServiceimpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public AuthServiceimpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public String Login(LoginRequest loginRequest) {
        User user = userRepository.findByAccount(loginRequest.getAccount());
        String role = user.getRole().getRoleName();
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenProvider.generateToken(loginRequest.getAccount(), role);
            return token;
        }
        return WebConstants.ACCOUNT_OR_PASSWORD_NOT_CORRECT;
    }

    @Override
    public String Register(RegisterRequest registerRequest) {
        if (userRepository.findByAccount(registerRequest.getAccount()) != null)
            return WebConstants.ACCOUT_EXITS;
        User user = new User();
        user.setAccount(registerRequest.getAccount());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(roleRepository.findByRoleName("USER"));
        userRepository.save(user);
        return WebConstants.CREATE_ACCOUNT_SUCCESSFULLY;
    }

}
