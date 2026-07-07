package com.example.nutripath.Dto;


public class FoodDto {
  private String itemName;
    private Double baseServingG;
    private Double caloriesPerServing;
    private Double proteinPerServing;
    private Double carbsPerServing;
    private Double fatPerServing;
    
    public String getItemName() {
      return itemName;
    }
    public void setItemName(String itemName) {
      this.itemName = itemName;
    }
    public Double getBaseServingG() {
      return baseServingG;
    }
    public void setBaseServingG(Double baseServingG) {
      this.baseServingG = baseServingG;
    }
    public Double getCaloriesPerServing() {
      return caloriesPerServing;
    }
    public void setCaloriesPerServing(Double caloriesPerServing) {
      this.caloriesPerServing = caloriesPerServing;
    }
    public Double getProteinPerServing() {
      return proteinPerServing;
    }
    public void setProteinPerServing(Double proteinPerServing) {
      this.proteinPerServing = proteinPerServing;
    }
    public Double getCarbsPerServing() {
      return carbsPerServing;
    }
    public void setCarbsPerServing(Double carbsPerServing) {
      this.carbsPerServing = carbsPerServing;
    }
    public Double getFatPerServing() {
      return fatPerServing;
    }
    public void setFatPerServing(Double fatPerServing) {
      this.fatPerServing = fatPerServing;
    }
}
