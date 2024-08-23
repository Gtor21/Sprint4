package com.mindhub.AppSprint2.services;

import com.mindhub.AppSprint2.dtos.TaskRequestDto;
import com.mindhub.AppSprint2.dtos.TaskResponseDto;
import com.mindhub.AppSprint2.exeptions.NotFoundTaskException;
import com.mindhub.AppSprint2.mappers.TaskMapper;
import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.repositories.TaskRepository;
import com.mindhub.AppSprint2.services.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_ShouldThrowException_WhenTaskRequestDtoIsNull() {
        assertThrows(NotFoundTaskException.class, () -> {
            taskService.save(null);
        });

        verify(taskRepository, never()).save(any(Task.class));
        verify(taskMapper, never()).toTask(any(TaskRequestDto.class));
    }

    @Test
    public void testSave_ShouldSaveTask_WhenTaskRequestDtoIsValid() {
        // Arrange
        TaskRequestDto dto = new TaskRequestDto();
        Task taskEntity = new Task();
        TaskResponseDto taskResponseDto = new TaskResponseDto();

        when(taskMapper.toTask(dto)).thenReturn(taskEntity);
        when(taskRepository.save(taskEntity)).thenReturn(taskEntity);
        when(taskMapper.toTaskResponseDto(taskEntity)).thenReturn(taskResponseDto);

        // Act
        TaskResponseDto result = taskService.save(dto);

        // Assert
        assertNotNull(result);
        assertEquals(taskResponseDto, result);

        // Verifica que los métodos se llamaron una vez con los valores correctos
        verify(taskMapper, times(1)).toTask(dto);
    }
}