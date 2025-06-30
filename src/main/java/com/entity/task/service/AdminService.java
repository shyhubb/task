package com.entity.task.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.TaskResponse;
import com.entity.task.dto.response.UserResponse;

@Service
public interface AdminService {
    public ArrayList<TaskResponse> findAllTask();

    public List<UserResponse> findAllUser();

    @SuppressWarnings("rawtypes")
    public BaseResponse deleteUser(Long id);
}
