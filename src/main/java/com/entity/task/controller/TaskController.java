package com.entity.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.task.dto.request.TaskRequest;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.service.impl.TaskServiceimpl;
import com.entity.task.webconstants.WebConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskServiceimpl taskServiceimpl;

    public TaskController(TaskServiceimpl taskServiceimpl) {
        this.taskServiceimpl = taskServiceimpl;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<String>> createTask(@RequestBody TaskRequest taskRequest) {
        BaseResponse<String> createTask = taskServiceimpl.createTask(taskRequest);
        String status = createTask.getMessage();
        if (status.equals(WebConstants.STATUS_OR_PRORITY_NOT_FOUND)) {
            return new ResponseEntity<>(createTask, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createTask, HttpStatus.CREATED);
    }

}
