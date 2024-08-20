package com.mindhub.AppSprint2.mappers;

import com.mindhub.AppSprint2.dtos.UserDto;
import com.mindhub.AppSprint2.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto toUserDto(UserEntity user) {
        if (user == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRol(user.getRol());
        return dto;
    }

    public UserEntity toUser(UserDto userDto ) {
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
