package com.ratingmanagement.demo.controller;

import com.ratingmanagement.demo.model.Rating;
import com.ratingmanagement.demo.service.RatingService;
import com.ratingmanagement.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addRating(@RequestBody Rating rating, HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        if (email == null) return "Unauthorized access.";

        int userId = userService.getUserIdByEmail(email);
        rating.setUserId(userId);

        return ratingService.addRating(rating);
    }
}
