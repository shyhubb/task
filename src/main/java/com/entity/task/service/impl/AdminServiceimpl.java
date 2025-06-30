package com.entity.task.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.TaskResponse;
import com.entity.task.dto.response.UserResponse;
import com.entity.task.entities.Task;
import com.entity.task.entities.User;
import com.entity.task.repository.TaskRepository;
import com.entity.task.repository.UserRepository;
import com.entity.task.service.AdminService;
import com.entity.task.webconstants.WebConstants;

@Service
public class AdminServiceimpl implements AdminService {
    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public AdminServiceimpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ArrayList<TaskResponse> findAllTask() {
        List<Task> tasks = taskRepository.findAll();
        ArrayList<TaskResponse> taskRes = new ArrayList<>();

        for (Task task : tasks) {
            TaskResponse taskResponse = new TaskResponse();
            taskResponse.setId(task.getId());
            taskResponse.setDescription(task.getDescription());
            taskResponse.setDueDate(task.getDueDate());
            taskResponse.setTaskName(task.getTaskName());
            taskResponse.setUserId(task.getUser().getId());
            taskResponse.setTaskPriority(task.getTaskPriority().getDescription());
            taskResponse.setTaskPriorityLevel(task.getTaskPriority().getLevel());
            taskResponse.setTaskStatus(task.getTaskStatus().getStatus());
            taskRes.add(taskResponse);
        }
        return taskRes;
    }

    @Override
    public List<UserResponse> findAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setAccount(user.getAccount());
            userResponse.setAddress(user.getAddress());
            userResponse.setName(user.getName());
            userResponse.setPhoneNumber(user.getPhoneNumber());
            userResponse.setRole(user.getRole().getRoleName());
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public BaseResponse deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return new BaseResponse<>(WebConstants.USER_NOT_FOUND + " Id: " + id, null);
        } else
            userRepository.deleteById(id);
        return new BaseResponse<>(WebConstants.DELETE_USER_SUCCESSFULLY + id, null);
    }
}
