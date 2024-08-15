package com.mindhub.AppSprint2.dtos;

import com.mindhub.AppSprint2.models.UserEntity;
import org.apache.catalina.User;

public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;

    public UserDto() {
    }

    public UserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.email = userEntity.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
