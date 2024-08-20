package com.mindhub.AppSprint2.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private RolEnum rol;

    @OneToMany( mappedBy = "userEntity", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String username, String password, String email, RolEnum rol) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        task.setUserEntity(this);
        tasks.add(task);
    }

    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rol = rol;
    }
}
