package com.ratingmanagement.demo.repository;

import com.ratingmanagement.demo.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    List<Rating> findByAmbiance(int ambiance);
    List<Rating> findByFood(int food);
    List<Rating> findByService(int service);
    List<Rating> findByCleanliness(int cleanliness);
    List<Rating> findByDrinks(int drinks);

    @Query("SELECT AVG(r.ambiance), AVG(r.food), AVG(r.service), AVG(r.cleanliness), AVG(r.drinks), " +
           "AVG((r.ambiance + r.food + r.service + r.cleanliness + r.drinks)/5.0) " +
           "FROM Rating r")
    Object getAverageRatings();
}
