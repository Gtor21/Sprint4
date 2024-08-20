package com.mindhub.AppSprint2.services.impl;

import com.mindhub.AppSprint2.dtos.TaskResponseDto;
import com.mindhub.AppSprint2.dtos.UserDto;
import com.mindhub.AppSprint2.exeptions.NotFoundUserException;
import com.mindhub.AppSprint2.mappers.UserMapper;
import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.repositories.UserRepository;
import com.mindhub.AppSprint2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto findById(Long id) {
        String errorMessage = String.format("El Usuario N° %d no fue encontrado, por favor verifica ID del usuario", id);
        return userRepository.findById(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new NotFoundUserException(errorMessage));
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundUserException(String.format("El Usuario N° %d no fue encontrado, por favor verifica ID del usuario", id)));

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        UserEntity updatedUser = userRepository.save(entity);

        return userMapper.toUserDto(updatedUser);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new NotFoundUserException("No se pudo eliminar el usuario porque está asociado a una o más tareas.");
        }
    }

    @Override
    public UserDto save(UserDto dto) {
        UserEntity entity = userMapper.toUser(dto);
        entity = userRepository.save(entity);
        return userMapper.toUserDto(entity);
    }

}
