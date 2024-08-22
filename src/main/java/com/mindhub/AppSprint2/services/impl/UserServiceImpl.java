package com.mindhub.AppSprint2.services.impl;

import com.mindhub.AppSprint2.dtos.UserRequestDto;
import com.mindhub.AppSprint2.dtos.UserResponseDto;
import com.mindhub.AppSprint2.exeptions.NotFoundUserException;
import com.mindhub.AppSprint2.mappers.UserMapper;
import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.repositories.UserRepository;
import com.mindhub.AppSprint2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDto findById(Long id) {
        String errorMessage = String.format("El Usuario N° %d no fue encontrado, por favor verifica ID del usuario", id);
        return userRepository.findById(id)
                .map(userMapper::toUserResponseDto)
                .orElseThrow(() -> new NotFoundUserException(errorMessage));
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundUserException(String.format("El Usuario N° %d no fue encontrado, por favor verifica ID del usuario", id)));

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity updatedUser = userRepository.save(entity);

        return userMapper.toUserResponseDto(updatedUser);
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
    public UserResponseDto save(UserRequestDto dto) {

        // Validar si el correo electrónico ya está en uso
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new NotFoundUserException("El correo electrónico ya está en uso.");
        }

        UserEntity entity = userMapper.toUser(dto);
        entity = userRepository.save(entity);
        return userMapper.toUserResponseDto(entity);
    }


}
