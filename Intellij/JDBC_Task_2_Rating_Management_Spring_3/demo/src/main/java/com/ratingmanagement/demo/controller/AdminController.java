package com.ratingmanagement.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ratingmanagement.demo.model.Rating;
import com.ratingmanagement.demo.service.RatingService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RatingService ratingService;

    //Average Report
    @GetMapping("/report")
    public Object viewAverageRatings() {
        return ratingService.getAverageRatings();
    }

    //Filter Users by rating
    @GetMapping("/filter")
    public List<Rating> filterUsers(@RequestParam String category,
                                    @RequestParam int value) {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        return ratingService.filterBy(category, value);
    }
}
