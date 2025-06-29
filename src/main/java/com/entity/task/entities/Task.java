package com.entity.task.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase id
    private Long id;
    private String taskName;
    private String description;
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "task_priority_id")
    private TaskPriority taskPriority;

    @ManyToOne
    @JoinColumn(name = "task_status_id")
    private TaskStatus taskStatus;

    public Task() {
    }

    public Task(String taskName, String description, LocalDateTime dueDate, TaskPriority taskPriority,
            TaskStatus taskStatus) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
    }
}