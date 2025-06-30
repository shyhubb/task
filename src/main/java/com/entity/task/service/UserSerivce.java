package com.entity.task.service;

import java.util.List;

import com.entity.task.dto.request.TaskRequest;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.TaskResponse;

public interface UserSerivce {
    public List<TaskResponse> findAllTask();

    public BaseResponse<TaskResponse> findTaskById(Long id);

    public BaseResponse<String> updateTask(Long id, TaskRequest taskRequest);

    public BaseResponse<String> deleteTask(Long id);
}
