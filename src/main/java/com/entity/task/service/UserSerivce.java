package com.entity.task.service;

import java.util.List;
import com.entity.task.dto.response.TaskResponse;

public interface UserSerivce {
    public List<TaskResponse> findAllTask();
}
