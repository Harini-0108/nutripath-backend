package com.example.nutripath.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class NutritionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;
    private Double targetCalories;
    private Double targetProteinG;
    private Double targetCarbsG;
    private Double targetFatG;
    private String planStatus;

    public NutritionPlan(){

    }

    public NutritionPlan(Long id, Long clientId, Double targetCalories, Double targetProteinG, Double targetCarbsG,
        Double targetFatG, String planStatus) {
      this.id = id;
      this.clientId = clientId;
      this.targetCalories = targetCalories;
      this.targetProteinG = targetProteinG;
      this.targetCarbsG = targetCarbsG;
      this.targetFatG = targetFatG;
      this.planStatus = planStatus;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Long getClientId() {
      return clientId;
    }

    public void setClientId(Long clientId) {
      this.clientId = clientId;
    }

    public Double getTargetCalories() {
      return targetCalories;
    }

    public void setTargetCalories(Double targetCalories) {
      this.targetCalories = targetCalories;
    }

    public Double getTargetProteinG() {
      return targetProteinG;
    }

    public void setTargetProteinG(Double targetProteinG) {
      this.targetProteinG = targetProteinG;
    }

    public Double getTargetCarbsG() {
      return targetCarbsG;
    }

    public void setTargetCarbsG(Double targetCarbsG) {
      this.targetCarbsG = targetCarbsG;
    }

    public Double getTargetFatG() {
      return targetFatG;
    }

    public void setTargetFatG(Double targetFatG) {
      this.targetFatG = targetFatG;
    }

    public String getPlanStatus() {
      return planStatus;
    }

    public void setPlanStatus(String planStatus) {
      this.planStatus = planStatus;
    }

    
}
