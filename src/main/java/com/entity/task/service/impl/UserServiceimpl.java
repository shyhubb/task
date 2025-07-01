package com.entity.task.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.entity.task.dto.request.TaskRequest;
import com.entity.task.dto.response.BaseResponse;
import com.entity.task.dto.response.TaskResponse;
import com.entity.task.dto.response.TaskSummaryResponse;
import com.entity.task.entities.Task;
import com.entity.task.entities.TaskPriority;
import com.entity.task.entities.TaskStatus;
import com.entity.task.entities.User;
import com.entity.task.repository.TaskPriorityRepository;
import com.entity.task.repository.TaskRepository;
import com.entity.task.repository.TaskStatusRepository;
import com.entity.task.service.UserSerivce;
import com.entity.task.webconstants.WebConstants;

@Service
public class UserServiceimpl implements UserSerivce {

    private final TaskRepository taskRepository;

    private final CurrentUserDetails currentUserDetails;

    private final TaskPriorityRepository taskProrityRepository;

    private final TaskStatusRepository taskStatusRepository;

    public UserServiceimpl(TaskRepository taskRepository, CurrentUserDetails currentUserDetails,
            TaskPriorityRepository taskProrityRepository,
            TaskStatusRepository taskStatusRepository) {
        this.taskRepository = taskRepository;
        this.currentUserDetails = currentUserDetails;
        this.taskProrityRepository = taskProrityRepository;
        this.taskStatusRepository = taskStatusRepository;
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

    @Override
    public BaseResponse<TaskResponse> findTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        User taskOwner = taskOptional.get().getUser();
        if (!taskOwner.equals(currentUserDetails.getUserDetails())) {
            return new BaseResponse<>(WebConstants.DOES_NOT_HAVE_PERMISSION, null);
        }
        if (!taskOptional.isPresent())
            return new BaseResponse<>(WebConstants.TASK_NOT_FOUND_BY_ID + id, null);
        Task task = taskOptional.get();
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setDueDate(task.getDueDate());
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setUserId(task.getUser().getId());
        taskResponse.setTaskPriority(task.getTaskPriority().getDescription());
        taskResponse.setTaskPriorityLevel(task.getTaskPriority().getLevel());
        taskResponse.setTaskStatus(task.getTaskStatus().getStatus());

        return new BaseResponse<>(WebConstants.BASE_SUCCESS, taskResponse);
    }

    @Override
    public BaseResponse<String> updateTask(Long id, TaskRequest taskRequest) {
        Optional<TaskPriority> optionalPriority = taskProrityRepository.findById(taskRequest.getTaskPriorityId());
        Optional<TaskStatus> optionalStatus = taskStatusRepository.findById(taskRequest.getTaskStatusId());

        if (optionalPriority.isEmpty() || optionalStatus.isEmpty()) {
            return new BaseResponse<>(WebConstants.STATUS_OR_PRORITY_NOT_FOUND, null);
        }
        TaskPriority taskPriority = optionalPriority.get();
        TaskStatus taskStatus = optionalStatus.get();

        Optional<Task> taskOptional = taskRepository.findById(id);
        User taskOwner = taskOptional.get().getUser();
        if (!taskOwner.equals(currentUserDetails.getUserDetails())) {
            return new BaseResponse<>(WebConstants.DOES_NOT_HAVE_PERMISSION, null);
        }
        if (!taskOptional.isPresent())
            return new BaseResponse<>(WebConstants.TASK_NOT_FOUND_BY_ID + id, null);
        Task task = taskOptional.get();
        task.setTaskName(taskRequest.getTaskName());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setTaskPriority(taskPriority);
        task.setTaskStatus(taskStatus);

        taskRepository.save(task);
        return new BaseResponse<>(WebConstants.BASE_SUCCESS, null);
    }

    @Override
    public BaseResponse<String> deleteTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        User taskOwner = taskOptional.get().getUser();
        if (!taskOwner.equals(currentUserDetails.getUserDetails())) {
            return new BaseResponse<>(WebConstants.DOES_NOT_HAVE_PERMISSION, null);
        }
        if (!taskOptional.isPresent())
            return new BaseResponse<>(WebConstants.TASK_NOT_FOUND_BY_ID + id, null);
        taskRepository.deleteById(id);
        return new BaseResponse<>(WebConstants.BASE_SUCCESS, null);

    }

    @Override
    public BaseResponse<List<TaskSummaryResponse>> findAllTaskSummary() {
        User user = currentUserDetails.getUserDetails();
        List<Task> tasks = taskRepository.findByUser(user);
        if (tasks.isEmpty()) {
            new BaseResponse<>(WebConstants.DONT_HAVE_TASK, null);
        }
        List<TaskSummaryResponse> taskSummaryResponse = new ArrayList<>();
        for (Task task : tasks) {
            TaskSummaryResponse taskSummary = new TaskSummaryResponse();
            taskSummary.setId(task.getId());
            taskSummary.setTaskName(task.getTaskName());
            taskSummary.setDueDate(task.getDueDate());
            taskSummary.setTaskStatus(task.getTaskStatus().getStatus());

            taskSummaryResponse.add(taskSummary);
        }
        return new BaseResponse<List<TaskSummaryResponse>>(WebConstants.BASE_SUCCESS, taskSummaryResponse);
    }

    @Override
    public BaseResponse<List<TaskResponse>> findTaskByStatus(Long id) {
        Optional<TaskStatus> taskStatusOptional = taskStatusRepository.findById(id);
        if (taskStatusOptional.isEmpty()) {
            return new BaseResponse<>(WebConstants.STATUS_NOT_FOUND_BY_ID + id, null);
        }
        TaskStatus taskStatus = taskStatusOptional.get();
        User user = currentUserDetails.getUserDetails();
        List<Task> tasks = taskRepository.findByTaskStatusAndUser(taskStatus, user);
        if (tasks.isEmpty()) {
            return new BaseResponse<>(WebConstants.TASK_NOT_FOUND_BY_STATUS_ID + id, null);
        }
        List<TaskResponse> taskResponse = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponse taskResponseTemp = new TaskResponse();
            taskResponseTemp.setId(task.getId());
            taskResponseTemp.setDescription(task.getDescription());
            taskResponseTemp.setDueDate(task.getDueDate());
            taskResponseTemp.setTaskName(task.getTaskName());
            taskResponseTemp.setUserId(task.getUser().getId());
            taskResponseTemp.setTaskPriority(task.getTaskPriority().getDescription());
            taskResponseTemp.setTaskPriorityLevel(task.getTaskPriority().getLevel());
            taskResponseTemp.setTaskStatus(task.getTaskStatus().getStatus());
            taskResponse.add(taskResponseTemp);
        }

        return new BaseResponse<>(WebConstants.BASE_SUCCESS, taskResponse);
    }

    @Override
    public BaseResponse<List<TaskResponse>> findTaskByPriority(Long id) {
        Optional<TaskPriority> taskPriorityOptional = taskProrityRepository.findById(id);
        if (taskPriorityOptional.isEmpty()) {
            return new BaseResponse<>(WebConstants.TASK_NOT_FOUND_BY_ID + id, null);
        }
        TaskPriority taskPriority = taskPriorityOptional.get();
        User user = currentUserDetails.getUserDetails();
        List<Task> tasks = taskRepository.findByTaskPriorityAndUser(taskPriority, user);
        if (tasks.isEmpty()) {
            return new BaseResponse<>(WebConstants.TASK_NOT_FOUND_BY_PRIORITY_ID + id, null);
        }
        List<TaskResponse> taskResponse = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponse taskResponseTemp = new TaskResponse();
            taskResponseTemp.setId(task.getId());
            taskResponseTemp.setDescription(task.getDescription());
            taskResponseTemp.setDueDate(task.getDueDate());
            taskResponseTemp.setTaskName(task.getTaskName());
            taskResponseTemp.setUserId(task.getUser().getId());
            taskResponseTemp.setTaskPriority(task.getTaskPriority().getDescription());
            taskResponseTemp.setTaskPriorityLevel(task.getTaskPriority().getLevel());
            taskResponseTemp.setTaskStatus(task.getTaskStatus().getStatus());
            taskResponse.add(taskResponseTemp);
        }

        return new BaseResponse<>(WebConstants.BASE_SUCCESS, taskResponse);
    }

}