package com.entity.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entity.task.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount(String account);
}
