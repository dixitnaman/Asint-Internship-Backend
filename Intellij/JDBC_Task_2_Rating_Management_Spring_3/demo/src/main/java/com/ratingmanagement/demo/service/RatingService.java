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

    public List<Rating> filterByCustom(Map<String, String> filters) {
        List<Rating> allRatings = ratingRepo.findAll();

        return allRatings.stream().filter(rating -> {
            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String key = entry.getKey().toLowerCase();
                int value;
                try {
                    value = Integer.parseInt(entry.getValue());
                } catch (NumberFormatException e) {
                    continue;
                }

                if (value < 1 || value > 5) continue;

                switch (key) {
                    case "ambiance":
                        if (rating.getAmbiance() == value) return true;
                        break;
                    case "food":
                        if (rating.getFood() == value) return true;
                        break;
                    case "service":
                        if (rating.getService() == value) return true;
                        break;
                    case "cleanliness":
                        if (rating.getCleanliness() == value) return true;
                        break;
                    case "drinks":
                        if (rating.getDrinks() == value) return true;
                        break;
                    default:
                        break;
                }
            }
            return false;
        }).toList();
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
