package com.entity.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entity.task.service.impl.CurrentUserDetails;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CurrentUserDetails currentUserDetails;

    public UserController(CurrentUserDetails currentUserDetails) {
        this.currentUserDetails = currentUserDetails;
    }
}
