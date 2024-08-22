package com.mindhub.AppSprint2;

import com.mindhub.AppSprint2.dtos.UserRequestDto;
import com.mindhub.AppSprint2.dtos.UserResponseDto;
import com.mindhub.AppSprint2.exeptions.NotFoundUserException;
import com.mindhub.AppSprint2.mappers.UserMapper;
import com.mindhub.AppSprint2.models.RolEnum;
import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.repositories.UserRepository;
import com.mindhub.AppSprint2.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void save_ShouldReturnUserResponseDto_WhenValidDataIsProvided() {
        // Arrange
        UserRequestDto requestDto = new UserRequestDto("test@example.com", "password");
        UserEntity userEntity = new UserEntity("test@example.com", "prueba" ,"2312312" ,RolEnum.USER);
        UserResponseDto responseDto = new UserResponseDto(1L, "prueba","test@example.com");

        when(userRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toUser(requestDto)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toUserResponseDto(userEntity)).thenReturn(responseDto);

        // Act
        UserResponseDto result = userService.save(requestDto);

        // Assert
        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(userRepository, times(1)).findByEmail(requestDto.getEmail());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void save_ShouldThrowNotFoundUserException_WhenEmailIsAlreadyInUse() {
        // Arrange
        UserRequestDto requestDto = new UserRequestDto("test@example.com", "password");

        when(userRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.of(new UserEntity()));

        // Act & Assert
        assertThrows(NotFoundUserException.class, () -> userService.save(requestDto));
        verify(userRepository, times(1)).findByEmail(requestDto.getEmail());
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    public void findById_ShouldReturnUserResponseDto_WhenUserExists() {
        // Arrange
        Long userId = 1L;
        UserEntity userEntity = new UserEntity("prueba","123455","test@example.com", RolEnum.USER);
        UserResponseDto responseDto = new UserResponseDto(userId, "prueba", "test@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userMapper.toUserResponseDto(userEntity)).thenReturn(responseDto);

        // Act
        UserResponseDto result = userService.findById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void findById_ShouldThrowNotFoundUserException_WhenUserDoesNotExist() {
        // Arrange
        Long userId = 1L;
        String errorMessage = String.format("El Usuario N° %d no fue encontrado, por favor verifica ID del usuario", userId);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundUserException thrown = assertThrows(NotFoundUserException.class, () -> userService.findById(userId));
        assertEquals(errorMessage, thrown.getMessage());
        verify(userRepository, times(1)).findById(userId);
    }
}
