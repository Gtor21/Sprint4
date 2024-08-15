package com.mindhub.AppSprint2.services.impl;

import com.mindhub.AppSprint2.dtos.UserDto;
import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.repositories.UserRepository;
import com.mindhub.AppSprint2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
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
        userRepository.deleteById(id);
    }

    @Override
    public UserDto save(UserDto userDTO) {
        UserEntity userEntity = convertToEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return convertToDTO(userEntity);
    }

    // Métodos auxiliares para conversiones
    private UserDto convertToDTO(UserEntity userEntity) {
        UserDto userDTO = new UserDto();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setEmail(userEntity.getEmail());
        return userDTO;
    }
    private UserEntity convertToEntity(UserDto userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setId(userDTO.getId());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        return userEntity;
    }
}
