package com.entity.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entity.task.entities.TaskPriority;

@Repository
public interface TaskProrityRepository extends JpaRepository<TaskPriority, Long> {
    // Additional query methods can be defined here if needed

}
