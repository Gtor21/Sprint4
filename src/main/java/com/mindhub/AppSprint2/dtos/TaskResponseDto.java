package com.mindhub.AppSprint2.dtos;

import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.models.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Representa una tarea en el sistema.")
public class TaskResponseDto {

    @Schema(description = "ID único de la tarea.", example = "1")
    private Long id;

    @Schema(description = "Titulo de la tarea.")
    private String title;

    @Schema(description = "Descripcion de la tarea.")
    private String description;

    @Schema(description = "Estado de la tarea.", example = "IN_PROGRESS")
    private TaskStatus status;

    public TaskResponseDto() {
    }

    public TaskResponseDto(Task task) {
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

}
