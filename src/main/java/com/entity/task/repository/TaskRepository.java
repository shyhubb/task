package com.entity.task.repository;

import com.entity.task.entities.Task;
import com.entity.task.entities.TaskPriority;
import com.entity.task.entities.TaskStatus;
import com.entity.task.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @SuppressWarnings("null")
    List<Task> findAll();

    List<Task> findByUser(User user);

    Task findByTaskName(String taskName);

    List<Task> findByTaskPriorityAndUser(TaskPriority taskPriority, User user);

    List<Task> findByTaskStatusAndUser(TaskStatus taskStatus, User user);

}
