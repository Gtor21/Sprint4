package com.mindhub.AppSprint2.services.impl;

import com.mindhub.AppSprint2.dtos.TaskRequestDto;
import com.mindhub.AppSprint2.dtos.TaskResponseDto;
import com.mindhub.AppSprint2.exeptions.NotFoundTaskException;
import com.mindhub.AppSprint2.mappers.TaskMapper;
import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.repositories.TaskRepository;
import com.mindhub.AppSprint2.services.TaskService;
import com.mindhub.AppSprint2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        String errorMessage = String.format("La Tarea N° %d no fue encontrada, por favor verifica que sea la correcta", id);
        return taskRepository.findById(id)
                .map(taskMapper::toTaskResponseDto)
                .orElseThrow(() -> new NotFoundTaskException(errorMessage));
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
        Task entity = taskMapper.toTask(dto);
        entity = taskRepository.save(entity);
        return taskMapper.toTaskResponseDto(entity);
    }

}
