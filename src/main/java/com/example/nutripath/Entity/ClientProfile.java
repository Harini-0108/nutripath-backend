package com.example.nutripath.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "client_profile")
public class ClientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Integer age;
    private Double weightKg;
    private Double heightCm;
    private String activityLevel;
    private Double calculatedBmr;

    public ClientProfile(){

    }

    public ClientProfile(Long id, Long accountId, Integer age, Double weightKg, Double heightCm, String activityLevel,
        Double calculatedBmr) {
      this.id = id;
      this.accountId = accountId;
      this.age = age;
      this.weightKg = weightKg;
      this.heightCm = heightCm;
      this.activityLevel = activityLevel;
      this.calculatedBmr = calculatedBmr;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Long getAccountId() {
      return accountId;
    }

    public void setAccountId(Long accountId) {
      this.accountId = accountId;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }

    public Double getWeightKg() {
      return weightKg;
    }

    public void setWeightKg(Double weightKg) {
      this.weightKg = weightKg;
    }

    public Double getHeightCm() {
      return heightCm;
    }

    public void setHeightCm(Double heightCm) {
      this.heightCm = heightCm;
    }

    public String getActivityLevel() {
      return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
      this.activityLevel = activityLevel;
    }

    public Double getCalculatedBmr() {
      return calculatedBmr;
    }

    public void setCalculatedBmr(Double calculatedBmr) {
      this.calculatedBmr = calculatedBmr;
    }

    

    
}