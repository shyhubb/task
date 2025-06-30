package com.entity.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.task.dto.request.TaskRequest;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.TaskResponse;
import com.entity.task.service.impl.TaskServiceimpl;
import com.entity.task.service.impl.UserServiceimpl;
import com.entity.task.webconstants.WebConstants;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceimpl userServiceimpl;

    private final TaskServiceimpl taskServiceimpl;

    public UserController(UserServiceimpl userServiceimpl, TaskServiceimpl taskServiceimpl) {
        this.userServiceimpl = userServiceimpl;
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

    @GetMapping("/task/findall")
    public ResponseEntity<BaseResponse<List<TaskResponse>>> findAllTask() {
        List<TaskResponse> tasks = userServiceimpl.findAllTask();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(WebConstants.BASE_FAIL, null), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new BaseResponse<>(WebConstants.BASE_SUCCESS, tasks), HttpStatus.OK);
    }

    @GetMapping("/task/find/{id}")
    public ResponseEntity<BaseResponse<TaskResponse>> findTaskById(@PathVariable Long id) {
        BaseResponse<TaskResponse> taskResponse = userServiceimpl.findTaskById(id);
        if (taskResponse.getMessage().equals(WebConstants.BASE_SUCCESS)) {
            return new ResponseEntity<>(taskResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(taskResponse, HttpStatus.NOT_FOUND);
    }

    @PutMapping("task/update/{id}")
    public ResponseEntity<BaseResponse<String>> updateTask(@PathVariable Long id,
            @RequestBody TaskRequest taskRequest) {
        BaseResponse<String> update = userServiceimpl.updateTask(id, taskRequest);
        if (update.getMessage().equals(WebConstants.BASE_SUCCESS))
            return new ResponseEntity<>(update, HttpStatus.OK);
        return new ResponseEntity<>(update, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("task/delete/{id}")
    public ResponseEntity<BaseResponse<String>> deleteTask(@PathVariable Long id) {
        BaseResponse<String> deleteTask = userServiceimpl.deleteTask(id);
        if (deleteTask.getMessage().equals(WebConstants.BASE_SUCCESS)) {
            return new ResponseEntity<>(deleteTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(deleteTask, HttpStatus.BAD_REQUEST);
    }

}
