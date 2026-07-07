package com.example.nutripath.Dto;

public class PlanDto {

    private Long clientId;
    private Double targetCalories;
    private Double targetProteinG;
    private Double targetCarbsG;
    private Double targetFatG;
    private String planStatus;

    public PlanDto() {
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