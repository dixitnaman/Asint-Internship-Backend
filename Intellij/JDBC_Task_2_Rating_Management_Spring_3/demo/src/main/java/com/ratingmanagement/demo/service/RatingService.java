package com.ratingmanagement.demo.service;

import com.ratingmanagement.demo.model.Rating;
import com.ratingmanagement.demo.repository.RatingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public String addRating(Rating rating) {
        ratingRepo.save(rating);
        return "Rating added successfully.";
    }

    public List<Rating> filterByCustom(Map<String, String> filters) {
        String conditions = filters.entrySet().stream()
                .filter(e -> {
                    try {
                        int val = Integer.parseInt(e.getValue());
                        return val >= 1 && val <= 5;
                    } catch (NumberFormatException ex) {
                        return false;
                    }
                })
                .map(e -> e.getKey().toLowerCase() + " = " + Integer.parseInt(e.getValue()))
                .collect(Collectors.joining(" OR "));

        String sql = "SELECT * FROM ratings" + (conditions.isEmpty() ? "" : " WHERE " + conditions);
        Query query = entityManager.createNativeQuery(sql, Rating.class);
        return query.getResultList();
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
