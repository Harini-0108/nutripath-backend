package com.example.nutripath.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.nutripath.Dto.PlanDto;
import com.example.nutripath.Entity.NutritionPlan;
import com.example.nutripath.Exception.ResourceNotFoundException;
import com.example.nutripath.Repository.NutritionPlanRepo;
import com.example.nutripath.Service.NutritionPlanService;

@Service
public class NutritionPlanServiceImpl implements NutritionPlanService {

    private final NutritionPlanRepo planRepository;

    public NutritionPlanServiceImpl(NutritionPlanRepo planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public PlanDto createPlan(PlanDto dto) {
        NutritionPlan plan = createAndActivatePlan(dto);
        PlanDto response = new PlanDto();
        response.setClientId(plan.getClientId());
        response.setTargetCalories(plan.getTargetCalories());
        response.setTargetProteinG(plan.getTargetProteinG());
        response.setTargetCarbsG(plan.getTargetCarbsG());
        response.setTargetFatG(plan.getTargetFatG());
        response.setPlanStatus(plan.getPlanStatus());
        return response;
    }

    @Override
    public NutritionPlan createAndActivatePlan(PlanDto dto) {
        NutritionPlan plan = new NutritionPlan();
        plan.setClientId(dto.getClientId());
        plan.setTargetCalories(dto.getTargetCalories());
        plan.setTargetProteinG(dto.getTargetProteinG());
        plan.setTargetCarbsG(dto.getTargetCarbsG());
        plan.setTargetFatG(dto.getTargetFatG());
        plan.setPlanStatus("ACTIVE");
        return planRepository.save(plan);
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    public List<PlanDto> getPlans() {
        List<PlanDto> list = new ArrayList<>();
        for (NutritionPlan plan : planRepository.findAll()) {
            PlanDto dto = new PlanDto();
            dto.setClientId(plan.getClientId());
            dto.setTargetCalories(plan.getTargetCalories());
            dto.setTargetProteinG(plan.getTargetProteinG());
            dto.setTargetCarbsG(plan.getTargetCarbsG());
            dto.setTargetFatG(plan.getTargetFatG());
            dto.setPlanStatus(plan.getPlanStatus());
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<NutritionPlan> getPlansByClientId(Long clientId) {
        return planRepository.findByClientId(clientId);
    }

    @Override
    public NutritionPlan getCurrentPlan(Long clientId) {
        return planRepository.findByClientIdAndPlanStatus(clientId, "ACTIVE").orElseThrow(() -> new ResourceNotFoundException("No Active Plan Found"));
    }
}