package com.mindhub.AppSprint2.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindhub.AppSprint2.dtos.UserRequestDto;
import com.mindhub.AppSprint2.dtos.UserResponseDto;
import com.mindhub.AppSprint2.models.RolEnum;
import com.mindhub.AppSprint2.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testCreateUser() throws Exception {
        // Datos de prueba para UserRequestDto
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("eric");
        userRequestDto.setPassword("securePass123");
        userRequestDto.setEmail("eric@example.com");
        userRequestDto.setRol(RolEnum.ADMIN);

        // Datos de prueba para UserResponseDto (sin password por motivos de seguridad)
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setUsername("eric");
        userResponseDto.setEmail("eric@example.com");
        userResponseDto.setRol(RolEnum.ADMIN);

        // Configuramos el mock para que devuelva el responseDto cuando se llame al método save
        when(userService.save(any(UserRequestDto.class))).thenReturn(userResponseDto);

        // Realizamos la petición y verificamos la respuesta
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("eric"))
                .andExpect(jsonPath("$.email").value("eric@example.com"))
                .andExpect(jsonPath("$.rol").value("ADMIN"))
                .andDo(print());

        // Verificamos que el método save del servicio fue llamado una vez
        verify(userService, times(1)).save(any(UserRequestDto.class));
    }
}
