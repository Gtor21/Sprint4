package com.mindhub.AppSprint2.services;

import com.mindhub.AppSprint2.dtos.TaskDto;
import com.mindhub.AppSprint2.dtos.UserDto;
import com.mindhub.AppSprint2.models.Task;

public interface TaskService {

    TaskDto findById(Long id);

    boolean existsById(Long id);

    long countUsers();

    void deleteById(Long id);

    TaskDto save(TaskDto dto);
}
