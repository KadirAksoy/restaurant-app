package com.kadiraksoy.restaurantapp;

import com.kadiraksoy.restaurantapp.model.Role;
import com.kadiraksoy.restaurantapp.model.AppUser;
import com.kadiraksoy.restaurantapp.repository.UserRepository;
import com.kadiraksoy.restaurantapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RestaurantAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantAppApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(PasswordEncoder passwordEncoder, UserService userService){
//		return args -> {
//			AppUser admin = AppUser
//					.builder()
//					.username("admin")
//					.email("admin@admin.com")
//					.password(passwordEncoder.encode("password"))
//					.role(Role.ROLE_ADMIN)
//					.build();
//
//			userService.save(admin);
//			System.out.println("Admin created.");
//
//		};
//	}

}
