package com.ratingmanagement.demo.controller;

import com.ratingmanagement.demo.model.Rating;
import com.ratingmanagement.demo.service.RatingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RatingService ratingService;

    // View average ratings across all users
    @GetMapping("/report")
    public Object viewAverageRatings(HttpServletRequest request) {
        return ratingService.getAverageRatings();
    }

    // Filter users by a specific rating category and value
    @GetMapping("/filter")
    public List<Rating> filterUsers(@RequestParam String category,
                                    @RequestParam int value,
                                    HttpServletRequest request) {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        return ratingService.filterBy(category, value);
    }
}
