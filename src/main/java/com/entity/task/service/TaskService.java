package com.entity.task.service;

import com.entity.task.dto.request.TaskRequest;
import com.entity.task.dto.response.BaseResponse;

public interface TaskService {

    public BaseResponse<String> createTask(TaskRequest taskRequest);
}
