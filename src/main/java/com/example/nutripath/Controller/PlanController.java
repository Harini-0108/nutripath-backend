package com.example.nutripath.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.nutripath.Dto.PlanDto;
import com.example.nutripath.Entity.NutritionPlan;
import com.example.nutripath.Service.NutritionPlanService;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final NutritionPlanService planService;

    public PlanController(NutritionPlanService planService) {
        this.planService = planService;
    }

    // CREATE - Admin only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NutritionPlan> createPlan(@RequestBody PlanDto dto) {
        return new ResponseEntity<>(planService.createAndActivatePlan(dto), HttpStatus.OK);
    }

    // GET ALL - Admin and User
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<PlanDto> getAllPlans() {
        return planService.getPlans();
    }

    // GET BY CLIENT ID - Admin and User
    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<NutritionPlan> getPlansByClientId(@PathVariable Long clientId) {
        return planService.getPlansByClientId(clientId);
    }

    // DELETE - Admin only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.ok("NutritionPlan deleted successfully.");
    }
}