package com.mindhub.AppSprint2.dtos;

import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.models.TaskStatus;

public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private UserDto userDto;
    public TaskDto() {
    }

    public TaskDto(Task task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
