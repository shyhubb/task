package com.entity.task.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase id
    private Long id;
    @Column(name = "role", nullable = false, unique = true, length = 50)
    private String role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {
    }

    public Role(String role, List<User> users) {
        this.role = role;
        this.users = users;
    }

}
