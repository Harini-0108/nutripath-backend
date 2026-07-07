package com.example.nutripath.Service;

import java.util.List;

import com.example.nutripath.Dto.PlanDto;
import com.example.nutripath.Entity.NutritionPlan;

public interface NutritionPlanService {

    PlanDto createPlan(PlanDto dto);

    NutritionPlan createAndActivatePlan(PlanDto dto);

    void deletePlan(Long id);

    List<PlanDto> getPlans();

    List<NutritionPlan> getPlansByClientId(Long clientId);

    NutritionPlan getCurrentPlan(Long clientId);

}
