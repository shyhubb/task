package com.entity.task.entities; // <-- Đảm bảo package này là đúng

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase id
    private Long id;
    private String status; // e.g., "Pending", "In Progress", "Completed"
    private String description; // Description of the status

    // Mối quan hệ này đã đúng (List<Task> tasks)
    @OneToMany(mappedBy = "taskStatus")
    private List<Task> tasks;

    // Constructor này không nên nhận List<Task> làm tham số
    // vì List này thường được Hibernate quản lý.
    public TaskStatus(String status, String description) { // <-- SỬA CONSTRUCTOR
        this.status = status;
        this.description = description;
        // this.tasks = tasks; // <-- Bỏ dòng này
    }

    public TaskStatus() {
    }
}