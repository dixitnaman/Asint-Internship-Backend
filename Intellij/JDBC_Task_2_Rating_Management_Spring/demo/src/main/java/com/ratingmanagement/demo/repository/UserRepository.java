package com.ratingmanagement.demo.repository;

import com.ratingmanagement.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
