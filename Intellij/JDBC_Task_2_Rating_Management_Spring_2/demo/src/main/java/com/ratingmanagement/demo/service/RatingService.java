package com.ratingmanagement.demo.service;

import com.ratingmanagement.demo.model.Rating;
import com.ratingmanagement.demo.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepo;

    public String addRating(Rating rating) {
        ratingRepo.save(rating);
        return "Rating added successfully.";
    }

    public List<Rating> filterBy(String category, int value) {
        switch (category.toLowerCase()) {
            case "ambiance": return ratingRepo.findByAmbiance(value);
            case "food": return ratingRepo.findByFood(value);
            case "service": return ratingRepo.findByService(value);
            case "cleanliness": return ratingRepo.findByCleanliness(value);
            case "drinks": return ratingRepo.findByDrinks(value);
            default: return List.of();
        }
    }

    public Map<String, Double> getAverageRatings() {
        Object[] result = (Object[]) ratingRepo.getAverageRatings();

        Map<String, Double> map = new LinkedHashMap<>();
        map.put("ambiance",(Double) result[0]);
        map.put("food",(Double) result[1]);
        map.put("service",(Double) result[2]);
        map.put("cleanliness",(Double) result[3]);
        map.put("drinks",(Double) result[4]);
        map.put("overall",(Double) result[5]);

        return map;
    }
}
