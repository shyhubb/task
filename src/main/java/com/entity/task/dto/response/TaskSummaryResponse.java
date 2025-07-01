package com.entity.task.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskSummaryResponse {
    private Long id;
    private String taskName;
    private LocalDateTime dueDate;
    private String taskStatus;

    public TaskSummaryResponse(Long id, String taskName, LocalDateTime dueDate, String taskStatus) {
        this.id = id;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
    }

    public TaskSummaryResponse() {
    }

}
