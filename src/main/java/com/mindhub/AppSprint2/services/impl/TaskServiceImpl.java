package com.mindhub.AppSprint2.services.impl;

import com.mindhub.AppSprint2.Utils;
import com.mindhub.AppSprint2.dtos.TaskRequestDto;
import com.mindhub.AppSprint2.dtos.TaskResponseDto;
import com.mindhub.AppSprint2.exeptions.NotFoundTaskException;
import com.mindhub.AppSprint2.exeptions.UnauthorizedAccessException;
import com.mindhub.AppSprint2.mappers.TaskMapper;
import com.mindhub.AppSprint2.models.RolEnum;
import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.repositories.TaskRepository;
import com.mindhub.AppSprint2.services.TaskService;
import com.mindhub.AppSprint2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserService userService;

    @Override
    public TaskResponseDto findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundTaskException(String.format("La Tarea N° %d no fue encontrada, por favor verifica que sea la correcta", id)));

        // Mostrar solo tareas del usuario logeado
        if (!task.getUserEntity().getEmail().equals(Utils.getCurrentUser().getUsername())) {
            throw new UnauthorizedAccessException("No tienes permiso para acceder a esta tarea.");
        }

        return taskMapper.toTaskResponseDto(task);
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundTaskException(String.format("La Tarea N° %d no fue encontrada, por favor verifica que sea la correcta", id)));

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        Task updatedTask = taskRepository.save(task);

        return taskMapper.toTaskResponseDto(updatedTask);
    }

    @Override
    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }

    @Override
    public long countTask() {
        return taskRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponseDto save(TaskRequestDto dto) {
        if (dto == null) {
            throw new NotFoundTaskException("TaskRequestDto no puede ser null");
        }

        Task entity = taskMapper.toTask(dto);
        entity = taskRepository.save(entity);
        return taskMapper.toTaskResponseDto(entity);
    }

}
