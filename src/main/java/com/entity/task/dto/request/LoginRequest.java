package com.entity.task.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String account;
    private String password;

    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }

}
