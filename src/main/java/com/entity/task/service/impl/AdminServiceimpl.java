package com.entity.task.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.task.dto.response.TaskResponse;
import com.entity.task.entities.Task;
import com.entity.task.repository.TaskRepository;
import com.entity.task.service.AdminService;

@Service
public class AdminServiceimpl implements AdminService {
    private final TaskRepository taskRepository;

    public AdminServiceimpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public ArrayList<TaskResponse> findAllTask() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks == null)
            return null;
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
        System.out.println(taskRes);
        return taskRes;
    }
}
