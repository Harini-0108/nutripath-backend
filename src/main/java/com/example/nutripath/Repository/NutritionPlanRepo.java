package com.example.nutripath.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nutripath.Entity.NutritionPlan;

public interface NutritionPlanRepo extends JpaRepository<NutritionPlan, Long> {

    List<NutritionPlan> findByClientId(Long clientId);

    Optional<NutritionPlan> findByClientIdAndPlanStatus(Long clientId, String planStatus);

}