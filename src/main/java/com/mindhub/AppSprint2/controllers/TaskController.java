package com.mindhub.AppSprint2.controllers;


import com.mindhub.AppSprint2.dtos.TaskDto;
import com.mindhub.AppSprint2.dtos.UserDto;
import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.services.TaskService;
import com.mindhub.AppSprint2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getUserById(@PathVariable Long id){
        TaskDto dto = taskService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> userExists(@PathVariable Long id) {
        boolean exists = taskService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUserCount() {
        long count = taskService.countUsers();
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateUser(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        return ResponseEntity.ok(taskService.save(taskDto));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createUser(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.save(taskDto));
    }
}
