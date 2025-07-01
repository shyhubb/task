package com.entity.task.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.entity.task.dto.request.TaskRequest;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.entities.Task;
import com.entity.task.entities.TaskPriority;
import com.entity.task.entities.TaskStatus;
import com.entity.task.repository.TaskPriorityRepository;
import com.entity.task.repository.TaskRepository;
import com.entity.task.repository.TaskStatusRepository;
import com.entity.task.service.TaskService;
import com.entity.task.webconstants.WebConstants;

@Service
public class TaskServiceimpl implements TaskService {

    private final TaskRepository taskRepository;

    private final CurrentUserDetails currentUserDetails;

    private final TaskStatusRepository taskStatusRepository;

    private final TaskPriorityRepository taskProrityRepository;

    public TaskServiceimpl(TaskRepository taskRepository, CurrentUserDetails currentUserDetails,
            TaskStatusRepository taskStatusRepository,
            TaskPriorityRepository taskProrityRepository) {
        this.taskRepository = taskRepository;
        this.currentUserDetails = currentUserDetails;
        this.taskStatusRepository = taskStatusRepository;
        this.taskProrityRepository = taskProrityRepository;
    }

    @Override
    public BaseResponse<String> createTask(TaskRequest taskRequest) {
        Optional<TaskPriority> optionalPriority = taskProrityRepository.findById(taskRequest.getTaskPriorityId());
        Optional<TaskStatus> optionalStatus = taskStatusRepository.findById(taskRequest.getTaskStatusId());

        if (optionalPriority.isEmpty() || optionalStatus.isEmpty()) {
            return new BaseResponse<>(WebConstants.STATUS_OR_PRORITY_NOT_FOUND, null);
        }

        TaskPriority taskPriority = optionalPriority.get();
        TaskStatus taskStatus = optionalStatus.get();

        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setUser(currentUserDetails.getUserDetails());
        task.setTaskPriority(taskPriority);
        task.setTaskStatus(taskStatus);

        taskRepository.save(task);

        return new BaseResponse<>(WebConstants.BASE_SUCCESS, null);
    }

}
