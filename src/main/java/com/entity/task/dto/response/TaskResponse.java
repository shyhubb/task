package com.entity.task.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String taskName;
    private String description;
    private LocalDateTime dueDate;
    private Long userId;
    private String taskPriority;
    private Integer taskPriorityLevel;
    private String taskStatus;

    public TaskResponse(String taskName, String description, LocalDateTime dueDate, Long userId, String taskPriority,
            Integer taskPriorityLevel, String taskStatus) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.userId = userId;
        this.taskPriority = taskPriority;
        this.taskPriorityLevel = taskPriorityLevel;
        this.taskStatus = taskStatus;
    }

    public TaskResponse() {
    }
}
