package com.example.SecurityExample;

import com.example.SecurityExample.domain.Role;
import com.example.SecurityExample.domain.User;
import com.example.SecurityExample.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityExampleApplication.class, args);
	}


		@Bean
		CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "John Trabolta","john","{noop}1234",new ArrayList<>()));
			userService.saveUser(new User(null, "Will  Smith","will","{noop}1234",new ArrayList<>()));
			userService.saveUser(new User(null, "Jim Cerry Trabolta","jim","{noop}1234",new ArrayList<>()));
			userService.saveUser(new User(null, "Arnold Schwarzeneger","arnold","{noop}1234",new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("john", "ROLE_MANAGER");
			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_USER");


		};
	}
}
