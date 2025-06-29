package com.entity.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.TaskResponse;
import com.entity.task.service.AdminService;
import com.entity.task.service.impl.AdminServiceimpl;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminServiceimpl adminServiceimpl;

    public AdminController(AdminServiceimpl adminServiceimpl) {
        this.adminServiceimpl = adminServiceimpl;
    }

    @GetMapping("/task/findall")
    public ResponseEntity<ArrayList<TaskResponse>> getAllTasks() {
        ArrayList<TaskResponse> tasks = adminServiceimpl.findAllTask();
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }
}