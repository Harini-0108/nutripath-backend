package com.example.nutripath.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nutripath.Entity.MealConsumption;

public interface MealConsumptionRepo extends JpaRepository<MealConsumption, Long> {

    List<MealConsumption> findByTrackerId(Long trackerId);

}