package com.mindhub.AppSprint2.controllers;


import com.mindhub.AppSprint2.dtos.TaskRequestDto;
import com.mindhub.AppSprint2.dtos.TaskResponseDto;
import com.mindhub.AppSprint2.services.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@Tag(name = "Task Controller", description = "Operaciones con la gestión de tareas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Obtiene tarea por ID", description = "Retorna tarea del ID especificado")
    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id){
        TaskResponseDto dto = taskService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Valida tarea por ID", description = "Retorna True si existe de los contrario False")
    @GetMapping("/exists/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Boolean> TaskExists(@PathVariable Long id) {
        boolean exists = taskService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @Operation(summary = "Contar total tareas", description = "Retorna True si existe de los contrario False")
    @GetMapping("/count")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Long> getTaskCount() {
        long count = taskService.countTask();
        return ResponseEntity.ok(count);
    }


    @Operation(summary = "Elimina tarea por ID", description = "Elimina una tarea de la Base Datos por ID")
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar tarea por ID", description = "Actualiza una tarea existente con los detalles proporcionados y devuelve la tarea actualizada.")
    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto taskDto) {
        TaskResponseDto updatedTask = taskService.update(id, taskDto);  // Usar un método update en el servicio
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Crear tarea por ID", description = "Crea una nueva tarea.")
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskDto) {
        return ResponseEntity.ok(taskService.save(taskDto));
    }
}
