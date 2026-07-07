
package com.example.nutripath.ServiceImpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.nutripath.Dto.FoodDto;
import com.example.nutripath.Entity.FoodDictionary;
import com.example.nutripath.Exception.BusinessValidationException;
import com.example.nutripath.Repository.FoodDictionaryRepo;
import com.example.nutripath.Service.FoodCatalogService;

@Service
public class FoodCatalogServiceImpl implements FoodCatalogService {
    private final FoodDictionaryRepo foodRepository;
    public FoodCatalogServiceImpl(FoodDictionaryRepo foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public FoodDictionary addCustomFoodItem(FoodDto dto) {
        FoodDictionary food = new FoodDictionary();
        food.setItemName(dto.getItemName());
        food.setBaseServingG(dto.getBaseServingG());
        food.setCaloriesPerServing(dto.getCaloriesPerServing());
        food.setProteinPerServing(dto.getProteinPerServing());
        food.setCarbsPerServing(dto.getCarbsPerServing());
        food.setFatPerServing(dto.getFatPerServing());
        return foodRepository.save(food);
    }

    @Override
    public List<FoodDictionary> searchFoods(String query) {
        return foodRepository.findByItemNameContainingIgnoreCase(query);
    }

    @Override
    public List<FoodDictionary> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public FoodDictionary updateFood(Long id, FoodDto dto) {
        FoodDictionary food = foodRepository.findById(id).orElseThrow(() -> new BusinessValidationException("Food name already exist"));
        food.setItemName(dto.getItemName());
        food.setBaseServingG(dto.getBaseServingG());
        food.setCaloriesPerServing(dto.getCaloriesPerServing());
        food.setProteinPerServing(dto.getProteinPerServing());
        food.setCarbsPerServing(dto.getCarbsPerServing());
        food.setFatPerServing(dto.getFatPerServing());
        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public FoodDictionary getFoodById(Long id) {
        return foodRepository.findById(id).orElseThrow(() -> new BusinessValidationException("Food not found"));
    }

}