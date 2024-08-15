package com.mindhub.AppSprint2.repositories;

import com.mindhub.AppSprint2.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findById(Long id);
    boolean existsById(Long id);
    long countBy();

}
