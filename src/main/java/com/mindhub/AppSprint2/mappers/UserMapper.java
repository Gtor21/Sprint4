package com.mindhub.AppSprint2.mappers;

import com.mindhub.AppSprint2.dtos.UserRequestDto;
import com.mindhub.AppSprint2.dtos.UserResponseDto;
import com.mindhub.AppSprint2.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRequestDto toUserDto(UserEntity user) {
        if (user == null) {
            return null;
        }
        UserRequestDto dto = new UserRequestDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRol(user.getRol());
        return dto;
    }

    public UserResponseDto toUserResponseDto(UserEntity user) {
        if (user == null) {
            return null;
        }

        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRol(user.getRol());
        return dto;
    }

    public UserEntity toUser(UserRequestDto userDto ) {
        if (userDto == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(userDto.getId());
        entity.setUsername(userDto.getUsername());
        entity.setEmail(userDto.getEmail());
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        entity.setRol(userDto.getRol());
        return entity;
    }

}
