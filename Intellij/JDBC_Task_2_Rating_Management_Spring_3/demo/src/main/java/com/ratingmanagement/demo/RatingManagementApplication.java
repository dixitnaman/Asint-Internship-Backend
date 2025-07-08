package com.ratingmanagement.demo;

import com.ratingmanagement.demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RatingManagementApplication {
	//static user is defined to keep track of user login
	public static User user = new User();
	public static void main(String[] args) {
		SpringApplication.run(RatingManagementApplication.class, args);
	}
}
