package com.entity.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.TaskResponse;
import com.entity.task.service.impl.UserServiceimpl;
import com.entity.task.webconstants.WebConstants;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceimpl userServiceimpl;

    public UserController(UserServiceimpl userServiceimpl) {
        this.userServiceimpl = userServiceimpl;
    }

    @GetMapping("/task/findall")
    public ResponseEntity<BaseResponse<List<TaskResponse>>> findAllTask() {
        List<TaskResponse> tasks = userServiceimpl.findAllTask();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(WebConstants.BASE_FAIL, null), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new BaseResponse<>(WebConstants.BASE_SUCCESS, tasks), HttpStatus.OK);
    }

}
