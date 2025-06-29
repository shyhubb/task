package com.entity.task.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private String message;
    private T data;

    public BaseResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

}
