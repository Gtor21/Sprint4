package com.mindhub.AppSprint2.services;

import com.mindhub.AppSprint2.dtos.TaskRequestDto;
import com.mindhub.AppSprint2.dtos.TaskResponseDto;

public interface TaskService {

    TaskResponseDto findById(Long id);

    TaskResponseDto update(Long id, TaskRequestDto taskDto);

    boolean existsById(Long id);

    long countTask();

    void deleteById(Long id);

    TaskResponseDto save(TaskRequestDto dto);
}
