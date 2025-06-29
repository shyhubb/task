package com.entity.task.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import com.entity.task.dto.response.TaskResponse;

@Service
public interface AdminService {
    public ArrayList<TaskResponse> findAllTask();

}
