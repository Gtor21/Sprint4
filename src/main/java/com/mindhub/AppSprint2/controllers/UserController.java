package com.mindhub.AppSprint2.controllers;

import com.mindhub.AppSprint2.dtos.TaskRequestDto;
import com.mindhub.AppSprint2.dtos.TaskResponseDto;
import com.mindhub.AppSprint2.dtos.UserDto;
import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "Operaciones con la gestión de usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Obtiene Usuario por ID", description = "Retorna Usuario del ID especificado")
    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserDto> getUsuerById(@PathVariable Long id){
        UserDto dto = userService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Valida usuario por ID", description = "Retorna True si existe de los contrario False")
    @GetMapping("/exists/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Boolean> UserExists(@PathVariable Long id) {
        boolean exists = userService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @Operation(summary = "Contar todos los usuarios", description = "Retorna True si existe de los contrario False")
    @GetMapping("/count")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Long> getUsersCount() {
        long count = userService.countUsers();
        return ResponseEntity.ok(count);
    }


    @Operation(summary = "Elimina usuario por ID", description = "Elimina un usuario de la Base Datos por ID")
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar usuario por ID", description = "Actualiza un usuario existente con los detalles proporcionados y devuelve usuario actualizado.")
    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        UserDto updatedUser = userService.update(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Crear tarea por ID", description = "Crea una nueva tarea.")
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto taskDto) {
        return ResponseEntity.ok(userService.save(taskDto));
    }

}
