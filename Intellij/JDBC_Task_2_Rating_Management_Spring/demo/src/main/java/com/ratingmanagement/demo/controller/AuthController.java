package com.ratingmanagement.demo.controller;

import com.ratingmanagement.demo.RatingManagementApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ratingmanagement.demo.model.User;
import com.ratingmanagement.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password) {
        return userService.registerUser(name, email, password);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email,
                                @RequestParam String password) {
        RatingManagementApplication.user = userService.login(email, password);

        if (RatingManagementApplication.user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }

        return ResponseEntity.ok(RatingManagementApplication.user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        RatingManagementApplication.user = new User();
        return ResponseEntity.ok("Logout Succesfully");
    }
}
