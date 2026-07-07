package com.example.nutripath.Service;

import java.util.List;

import com.example.nutripath.Dto.FoodDto;
import com.example.nutripath.Entity.FoodDictionary;

public interface FoodCatalogService {
    FoodDictionary addCustomFoodItem(FoodDto dto);
    List<FoodDictionary> searchFoods(String query);
    List<FoodDictionary> getAllFoods();
    FoodDictionary updateFood(Long id, FoodDto dto);
    void deleteFood(Long id);
    FoodDictionary getFoodById(Long id);
}
