package com.entity.task.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String account;
    private String password;
    private String confimPassword;

    public RegisterRequest(String account, String password, String confimPassword) {
        this.account = account;
        this.password = password;
        this.confimPassword = confimPassword;
    }

    public RegisterRequest() {
    }

}
