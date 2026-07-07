package com.example.nutripath.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.nutripath.Dto.MealConsumptionDto;
import com.example.nutripath.Entity.MealConsumption;
import com.example.nutripath.Service.DailyTrackingService;

@RestController
@RequestMapping("/trackers")
public class TrackerController {

    private final DailyTrackingService trackingService;

    public TrackerController(DailyTrackingService trackingService) {
        this.trackingService = trackingService;
    }

    // CREATE (log a meal) - Admin only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MealConsumption> logMeal(Authentication auth, @RequestBody MealConsumptionDto dto) {
        trackingService.logMealConsumption(auth.getName(), dto);
        List<MealConsumption> meals = trackingService.getTodayMeals(auth.getName());
        MealConsumption latest = meals.get(meals.size() - 1);
        return ResponseEntity.ok(latest);
    }

    // GET ALL - Admin and User
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<MealConsumption>> getAllMeals() {
        return ResponseEntity.ok(trackingService.getAllMeals());
    }

    // GET BY ID - Admin and User
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<MealConsumption> getMealById(@PathVariable Long id) {
        return ResponseEntity.ok(trackingService.getMealById(id));
    }

    // DELETE - Admin only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMeal(@PathVariable Long id) {
        trackingService.deleteMeal(id);
        return ResponseEntity.ok("Meal consumption deleted successfully.");
    }
}