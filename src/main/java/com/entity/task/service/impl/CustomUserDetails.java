package com.entity.task.service.impl;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.task.entities.User;
import com.entity.task.repository.UserRepository;

@Service
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    CustomUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(account);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with account: " + account);
        }

        // --- THE FIX IS HERE ---
        // Ensure the role is prefixed with "ROLE_"
        String roleWithPrefix = "ROLE_" + user.getRole().getRoleName(); // Assuming roles like "ADMIN", "USER"
        // You might want to store roles in uppercase
        // already
        return new org.springframework.security.core.userdetails.User(
                user.getAccount(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(roleWithPrefix)));
    }
}