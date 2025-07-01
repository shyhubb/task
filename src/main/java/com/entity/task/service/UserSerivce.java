package com.entity.task.service;

import java.util.List;

import com.entity.task.dto.request.TaskRequest;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.TaskResponse;
import com.entity.task.dto.response.TaskSummaryResponse;

public interface UserSerivce {
    public List<TaskResponse> findAllTask();

    public BaseResponse<TaskResponse> findTaskById(Long id);

    public BaseResponse<String> updateTask(Long id, TaskRequest taskRequest);

    public BaseResponse<String> deleteTask(Long id);

    public BaseResponse<List<TaskSummaryResponse>> findAllTaskSummary();

    public BaseResponse<List<TaskResponse>> findTaskByStatus(Long id);

    public BaseResponse<List<TaskResponse>> findTaskByPriority(Long id);
}
