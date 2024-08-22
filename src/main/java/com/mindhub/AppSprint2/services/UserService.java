package com.mindhub.AppSprint2.services;

import com.mindhub.AppSprint2.dtos.UserRequestDto;
import com.mindhub.AppSprint2.dtos.UserResponseDto;

import java.util.Optional;

public interface UserService {

    UserResponseDto findById(Long id);

    UserResponseDto update(Long id, UserRequestDto dto);

    boolean existsById(Long id);

    long countUsers();

    void deleteById(Long id);

    UserResponseDto save(UserRequestDto userDTO);
}
