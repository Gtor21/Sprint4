package com.mindhub.AppSprint2.services;

import com.mindhub.AppSprint2.dtos.UserDto;
import com.mindhub.AppSprint2.models.UserEntity;

public interface UserService {

    UserDto findById(Long id);

    boolean existsById(Long id);

    long countUsers();

    void deleteById(Long id);

    UserDto save(UserDto userDTO);
}
