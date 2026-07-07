package com.example.nutripath.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class MealConsumption {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trackerId;
    private Long foodId;
    private Double consumedServingG;
    private String mealType;
    private LocalDateTime loggedAt;

    public MealConsumption(){

    }

    public MealConsumption(Long id, Long trackerId, Long foodId, Double consumedServingG, String mealType,
        LocalDateTime loggedAt) {
      this.id = id;
      this.trackerId = trackerId;
      this.foodId = foodId;
      this.consumedServingG = consumedServingG;
      this.mealType = mealType;
      this.loggedAt = loggedAt;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Long getTrackerId() {
      return trackerId;
    }

    public void setTrackerId(Long trackerId) {
      this.trackerId = trackerId;
    }

    public Long getFoodId() {
      return foodId;
    }

    public void setFoodId(Long foodId) {
      this.foodId = foodId;
    }

    public Double getConsumedServingG() {
      return consumedServingG;
    }

    public void setConsumedServingG(Double consumedServingG) {
      this.consumedServingG = consumedServingG;
    }

    public String getMealType() {
      return mealType;
    }

    public void setMealType(String mealType) {
      this.mealType = mealType;
    }

    public LocalDateTime getLoggedAt() {
      return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
      this.loggedAt = loggedAt;
    }

    
}
