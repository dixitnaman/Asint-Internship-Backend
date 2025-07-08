package com.ratingmanagement.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private int ambiance;
    private int food;
    private int service;
    private int cleanliness;
    private int drinks;

    public Rating() {}

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getAmbiance() { return ambiance; }
    public void setAmbiance(int ambiance) { this.ambiance = ambiance; }

    public int getFood() { return food; }
    public void setFood(int food) { this.food = food; }

    public int getService() { return service; }
    public void setService(int service) { this.service = service; }

    public int getCleanliness() { return cleanliness; }
    public void setCleanliness(int cleanliness) { this.cleanliness = cleanliness; }

    public int getDrinks() { return drinks; }
    public void setDrinks(int drinks) { this.drinks = drinks; }
}
