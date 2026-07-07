package com.example.nutripath.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.nutripath.Dto.FoodDto;
import com.example.nutripath.Entity.FoodDictionary;
import com.example.nutripath.Service.FoodCatalogService;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodCatalogService foodService;
    public FoodController(FoodCatalogService foodService) {
        this.foodService = foodService;
    }

    // CREATE - Admin only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FoodDictionary> addFood(@RequestBody FoodDto dto) {
        return new ResponseEntity<>(foodService.addCustomFoodItem(dto), HttpStatus.OK);
    }

    // GET ALL - Admin and User
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<FoodDictionary>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    // GET BY ID - Admin and User
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<FoodDictionary> getFoodById(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.getFoodById(id));
    }

    // DELETE - Admin only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.ok("Food item deleted successfully.");
    }
}