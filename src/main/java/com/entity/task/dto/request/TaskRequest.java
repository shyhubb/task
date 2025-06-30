package com.entity.task.dto.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskRequest {
    private String taskName;
    private String description;
    private LocalDateTime dueDate;
    private Long taskPriorityId; // Sử dụng ID
    private Long taskStatusId; // Sử dụng ID

    public TaskRequest(String taskName, String description, LocalDateTime dueDate, Long taskPriorityId,
            Long taskStatusId) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.taskPriorityId = taskPriorityId;
        this.taskStatusId = taskStatusId;
    }

    public TaskRequest() {
    }

}