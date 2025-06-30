package com.entity.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entity.task.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount(String account);

    @SuppressWarnings("null")
    List<User> findAll();

    @SuppressWarnings("null")
    Optional<User> findById(Long id);

}
