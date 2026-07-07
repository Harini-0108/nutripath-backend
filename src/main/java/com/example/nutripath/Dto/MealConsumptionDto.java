package com.example.nutripath.Dto;

public class MealConsumptionDto {

    private Long foodId;
    private Double consumedServingG;
    private String mealType;

    public MealConsumptionDto() {
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
}