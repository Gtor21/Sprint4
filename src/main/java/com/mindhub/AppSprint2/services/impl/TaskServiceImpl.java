package com.mindhub.AppSprint2.services.impl;

import com.mindhub.AppSprint2.dtos.TaskDto;
import com.mindhub.AppSprint2.dtos.UserDto;
import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.repositories.TaskRepository;
import com.mindhub.AppSprint2.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskDto findById(Long id) {
        return taskRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }

    @Override
    public long countUsers() {
        return taskRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto save(TaskDto dto) {
        Task entity = convertToEntity(dto);
        entity = taskRepository.save(entity);
        return convertToDTO(entity);
    }

    // metodos task
    private TaskDto convertToDTO(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setTitle(task.getTitle());
        dto.setUserDto(convertToUserDTO(task.getUserEntity()));
        return dto;
    }

    private Task convertToEntity(TaskDto dto) {
        Task entity = new Task();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setTitle(dto.getTitle());
        entity.setUserEntity(convertToUserEntity(dto.getUserDto()));
        return entity;
    }

    //metodos user
    private UserEntity convertToUserEntity(UserDto userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setId(userDTO.getId());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        return userEntity;
    }
    private UserDto convertToUserDTO(UserEntity userEntity) {
        UserDto userDTO = new UserDto();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setEmail(userEntity.getEmail());
        return userDTO;
    }

}
