package com.mindhub.AppSprint2;

import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.models.TaskStatus;
import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.repositories.TaskRepository;
import com.mindhub.AppSprint2.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, TaskRepository taskRepository){
        return args -> {

            UserEntity user = new UserEntity("Gabriel", "1234","gabrielturizo13@gmail.com");
            userRepository.save(user);

            Task task = new Task("23133","Mejora modulo usuarios", TaskStatus.PENDING);
            user.addTask(task);
            taskRepository.save(task);
        };
    }
}
