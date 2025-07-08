package com.ratingmanagement.demo.repository;

import com.ratingmanagement.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //optional ka use kr re, taaki agar null pointer excpetion aaye to usse manage krle
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
