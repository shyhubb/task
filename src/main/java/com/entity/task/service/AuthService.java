package com.entity.task.service;

import com.entity.task.dto.request.LoginRequest;
import com.entity.task.dto.request.RegisterRequest;

public interface AuthService {
    public String Login(LoginRequest loginRequest);

    public String Register(RegisterRequest registerRequest);
}
