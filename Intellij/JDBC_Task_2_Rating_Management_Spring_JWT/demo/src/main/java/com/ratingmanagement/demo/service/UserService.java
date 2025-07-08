package com.ratingmanagement.demo.service;

import java.util.Optional;
import java.util.UUID;

import com.ratingmanagement.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingmanagement.demo.model.User;
import com.ratingmanagement.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public String registerUser(String name, String email, String password) {
        if (userRepo.existsByEmail(email)) {
            return "Email already registered.";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("user");

        userRepo.save(user);
        return "User registered successfully.";
    }

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmailAndPassword(email, password);
        if (userOpt.isPresent()) {
            String token = jwtUtil.generateToken(email);
            return token;
        }
        return null;
    }

    public int getUserIdByEmail(String email) {
        return userRepo.findByEmail(email).map(User::getId).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
