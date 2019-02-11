package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.model.User;
import com.repository.UserRepository;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(UserRepository userRepository) {
		return (args) -> {
			userRepository.save(new User(0,"Jack","Erin","",200));
			
			
		};
	}
}

