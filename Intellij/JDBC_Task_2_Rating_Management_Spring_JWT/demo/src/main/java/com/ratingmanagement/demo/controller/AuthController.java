package com.ratingmanagement.demo.controller;

import com.ratingmanagement.demo.RatingManagementApplication;
import com.ratingmanagement.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ratingmanagement.demo.model.User;
import com.ratingmanagement.demo.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired // Spring yahan automatically UserService ka object inject karega
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password) {
        return userService.registerUser(name, email, password);
    }

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email,
                                   @RequestParam String password) {
        String token = userService.login(email, password);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        RatingManagementApplication.user = new User(); //logout ke liye static user object ko empty kr diya hai
        return ResponseEntity.ok("Logout Succesfully");
    }
}
