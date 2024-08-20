package com.mindhub.AppSprint2.dtos;

import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.models.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Representa una tarea en el sistema.")
public class TaskRequestDto {

    @Schema(description = "Titulo de la tarea.")
    private String title;

    @Schema(description = "Descripcion de la tarea.")
    private String description;

    @Schema(description = "Estado de la tarea.", example = "IN_PROGRESS")
    private TaskStatus status;

    @Schema(description = "Usuario realiza la tarea")
    private UserDto user;

    public TaskRequestDto() {
    }

    public TaskRequestDto(Task task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
