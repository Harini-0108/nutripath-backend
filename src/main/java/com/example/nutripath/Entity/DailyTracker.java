package com.example.nutripath.Entity;

import java.time.LocalDate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DailyTracker {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;
    private LocalDate date;
    private Double totalCalories;
    private Double totalProtein;
    private Double totalCarbs;
    private Double totalFat;

    public DailyTracker(){

    }

    public DailyTracker(Long id, Long clientId, LocalDate date, Double totalCalories, Double totalProtein,
        Double totalCarbs, Double totalFat) {
      this.id = id;
      this.clientId = clientId;
      this.date = date;
      this.totalCalories = totalCalories;
      this.totalProtein = totalProtein;
      this.totalCarbs = totalCarbs;
      this.totalFat = totalFat;
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

    public LocalDate getDate() {
      return date;
    }

    public void setDate(LocalDate date) {
      this.date = date;
    }

    public Double getTotalCalories() {
      return totalCalories;
    }

    public void setTotalCalories(Double totalCalories) {
      this.totalCalories = totalCalories;
    }

    public Double getTotalProtein() {
      return totalProtein;
    }

    public void setTotalProtein(Double totalProtein) {
      this.totalProtein = totalProtein;
    }

    public Double getTotalCarbs() {
      return totalCarbs;
    }

    public void setTotalCarbs(Double totalCarbs) {
      this.totalCarbs = totalCarbs;
    }

    public Double getTotalFat() {
      return totalFat;
    }

    public void setTotalFat(Double totalFat) {
      this.totalFat = totalFat;
    }

    
}
