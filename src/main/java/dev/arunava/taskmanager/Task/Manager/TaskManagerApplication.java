package dev.arunava.taskmanager.Task.Manager;

import dev.arunava.taskmanager.Task.Manager.model.User;
import dev.arunava.taskmanager.Task.Manager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			userRepository.save(new User("Test User", "testuser","test@user.com",passwordEncoder.encode("password"),"ROLE_USER"));
		};
	}
}
