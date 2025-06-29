package com.entity.task.entities; // <-- Đảm bảo package này là đúng

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List; // <-- Bạn sẽ cần import List

@Entity
@Setter
@Getter
public class TaskPriority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase id
    private Long id;
    private Integer level;
    private String description;
    // LƯU Ý QUAN TRỌNG:
    // Một TaskPriority có thể có NHIỀU Task (Many Tasks).
    // Do đó, trường này phải là một Collection (List, Set) chứ không phải một đối
    // tượng Task duy nhất.
    @OneToMany(mappedBy = "taskPriority")
    private List<Task> tasks; // <-- SỬA TỪ 'private Task task;' thành 'private List<Task> tasks;'

    public TaskPriority(Integer level, String description) { // <-- SỬA CONSTRUCTOR
        this.level = level;
        this.description = description;
        // KHÔNG NÊN truyền List<Task> vào constructor của TaskPriority,
        // mối quan hệ này được quản lý bởi JPA.
    }

    public TaskPriority() {
    }
}