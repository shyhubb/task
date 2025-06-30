package com.entity.task.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.entity.task.entities.User;
import com.entity.task.repository.UserRepository;

@Service
public class CurrentUserDetails {

    private final UserRepository userRepository;

    public CurrentUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // lay user trong request
    public User getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        // Lấy UserDetails từ Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByAccount(userDetails.getUsername());
        return user;
    }
}
