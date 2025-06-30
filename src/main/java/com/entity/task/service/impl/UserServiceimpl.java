package com.entity.task.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.task.dto.response.TaskResponse;
import com.entity.task.entities.Task;
import com.entity.task.entities.User;
import com.entity.task.repository.TaskRepository;
import com.entity.task.service.UserSerivce;

@Service
public class UserServiceimpl implements UserSerivce {

    private final TaskRepository taskRepository;

    private final CurrentUserDetails currentUserDetails;

    public UserServiceimpl(TaskRepository taskRepository, CurrentUserDetails currentUserDetails) {
        this.taskRepository = taskRepository;
        this.currentUserDetails = currentUserDetails;
    }

    @Override
    public List<TaskResponse> findAllTask() {
        User user = currentUserDetails.getUserDetails();
        List<Task> tasks = taskRepository.findByUser(user);
        if (tasks.isEmpty()) {
            return null;
        }
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

}
