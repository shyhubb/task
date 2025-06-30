package com.entity.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.TaskResponse;
import com.entity.task.dto.response.UserResponse;
import com.entity.task.service.impl.AdminServiceimpl;
import com.entity.task.webconstants.WebConstants;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminServiceimpl adminServiceimpl;

    public AdminController(AdminServiceimpl adminServiceimpl) {
        this.adminServiceimpl = adminServiceimpl;
    }

    @GetMapping("/task/findall")
    public ResponseEntity<BaseResponse<ArrayList<TaskResponse>>> findAllTask() {
        ArrayList<TaskResponse> tasks = adminServiceimpl.findAllTask();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(WebConstants.BASE_FAIL, null), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new BaseResponse<>(WebConstants.BASE_SUCCESS, tasks), HttpStatus.OK);
    }

    @GetMapping("/user/findall")
    public ResponseEntity<BaseResponse<List<UserResponse>>> findAllUser() {
        List<UserResponse> userResponses = adminServiceimpl.findAllUser();

        if (userResponses.isEmpty()) {
            return new ResponseEntity<>(
                    new BaseResponse<>(WebConstants.DONT_HAVE_USER_IN_SYSTEM, null),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new BaseResponse<>(WebConstants.BASE_SUCCESS, userResponses),
                    HttpStatus.OK);
        }
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable Long id) {
        BaseResponse response = adminServiceimpl.deleteUser(id);
        if (response.getMessage().equals(WebConstants.DELETE_USER_SUCCESSFULLY + id)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}