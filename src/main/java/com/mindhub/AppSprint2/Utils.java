package com.mindhub.AppSprint2;

import com.mindhub.AppSprint2.models.RolEnum;
import com.mindhub.AppSprint2.models.Task;
import com.mindhub.AppSprint2.models.TaskStatus;
import com.mindhub.AppSprint2.models.UserEntity;
import com.mindhub.AppSprint2.repositories.TaskRepository;
import com.mindhub.AppSprint2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Utils {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public CommandLineRunner initData(UserRepository userRepository, TaskRepository taskRepository){
        return args -> {

            UserEntity user = new UserEntity("Gabriel", passwordEncoder.encode("12345678"),"gabrielturizo13@gmail.com", RolEnum.ADMIN);
            userRepository.save(user);

            Task task = new Task("23133","Mejora modulo usuarios", TaskStatus.PENDING);
            user.addTask(task);
            taskRepository.save(task);
        };
    }
}
