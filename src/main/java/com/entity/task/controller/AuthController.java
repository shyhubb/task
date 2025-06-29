package com.entity.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entity.task.dto.request.LoginRequest;
import com.entity.task.dto.request.RegisterRequest;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.LoginResponse;
import com.entity.task.service.impl.AuthServiceimpl;
import com.entity.task.webconstants.WebConstants;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceimpl authServiceimpl;

    public AuthController(AuthServiceimpl authServiceimpl) {
        this.authServiceimpl = authServiceimpl;
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        String token = authServiceimpl.Login(loginRequest);
        if (token.equals(WebConstants.ACCOUNT_OR_PASSWORD_NOT_CORRECT))
            return new ResponseEntity<>(new BaseResponse<>(WebConstants.ACCOUNT_OR_PASSWORD_NOT_CORRECT, null),
                    HttpStatus.BAD_REQUEST);
        LoginResponse loginResponse = new LoginResponse(token);
        return new ResponseEntity<>(new BaseResponse<>(WebConstants.LOGIN_SUCCESSFULLY, loginResponse),
                HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<String>> Register(@RequestBody RegisterRequest registerRequest) {
        String message = authServiceimpl.Register(registerRequest);
        if (message.equals(WebConstants.ACCOUT_EXITS))
            return new ResponseEntity<>(new BaseResponse<String>(WebConstants.ACCOUT_EXITS, null),
                    HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new BaseResponse<String>(WebConstants.CREATE_ACCOUNT_SUCCESSFULLY, null),
                HttpStatus.CREATED);
    }

}
