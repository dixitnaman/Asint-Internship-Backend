package com.ratingmanagement.demo.controller;

import java.util.List;

import com.ratingmanagement.demo.RatingManagementApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ratingmanagement.demo.model.Rating;
import com.ratingmanagement.demo.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/add")
    public String addRating(@RequestBody Rating rating) {
        rating.setUserId(RatingManagementApplication.user.getId());
        return ratingService.addRating(rating);
    }
}
