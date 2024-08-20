package com.mindhub.AppSprint2.mappers;

import com.mindhub.AppSprint2.dtos.TaskRequestDto;
import com.mindhub.AppSprint2.dtos.TaskResponseDto;
import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponseDto toTaskResponseDto(Task task) {
        if (task == null) {
            return null;
        }
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        return dto;
    }

    public Task toTask(TaskRequestDto taskRequestDto ) {
        if (taskRequestDto == null) {
            return null;
        }

        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setStatus(taskRequestDto.getStatus());

        if (taskRequestDto.getUser() != null) {
            UserEntity user = new UserEntity();
            user.setId(taskRequestDto.getUser().getId());
            task.setUserEntity(user);
        }

        return task;
    }
}
