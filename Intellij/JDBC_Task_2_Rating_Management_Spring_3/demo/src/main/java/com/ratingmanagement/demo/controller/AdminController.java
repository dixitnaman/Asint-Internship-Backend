package com.ratingmanagement.demo.controller;

import java.util.List;
import java.util.Map;

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
    public List<Rating> filterUsers(@RequestParam Map<String, String> filters) {
        return ratingService.filterByCustom(filters);
    }
}
