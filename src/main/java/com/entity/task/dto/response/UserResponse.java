package com.entity.task.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String account;
    private String role;

    public UserResponse(Long id, String name, String phoneNumber, String address, String account, String role) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.account = account;
        this.role = role;
    }

    public UserResponse() {
    }

}
