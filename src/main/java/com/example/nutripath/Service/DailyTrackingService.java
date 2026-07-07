package com.example.nutripath.Service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.example.nutripath.Dto.MealConsumptionDto;
import com.example.nutripath.Entity.DailyTracker;
import com.example.nutripath.Entity.MealConsumption;

public interface DailyTrackingService {
  DailyTracker getToday(Authentication auth);
  MealConsumption logMeal(Authentication auth, MealConsumptionDto dto);
  void deleteMeal(Long id);
  DailyTracker getOrCreateCurrentTracker(String email);
  DailyTracker logMealConsumption(String email,MealConsumptionDto dto);
  List<DailyTracker> getHistory(String email);
  List<MealConsumption> getTodayMeals(String email);
  List<MealConsumption> getAllMeals();
  MealConsumption getMealById(Long id);
  MealConsumption updateMeal(Long id, MealConsumptionDto dto);
}