package com.example.nutripath.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.nutripath.Dto.MealConsumptionDto;
import com.example.nutripath.Entity.DailyTracker;
import com.example.nutripath.Entity.MealConsumption;
import com.example.nutripath.Entity.FoodDictionary;
import com.example.nutripath.Entity.SystemAccount;
import com.example.nutripath.Entity.ClientProfile;
import com.example.nutripath.Repository.DailyTrackerRepo;
import com.example.nutripath.Repository.MealConsumptionRepo;
import com.example.nutripath.Repository.FoodDictionaryRepo;
import com.example.nutripath.Repository.ClientProfileRepo;
import com.example.nutripath.Repository.SystemAccountRepo;
import com.example.nutripath.Service.DailyTrackingService;
import com.example.nutripath.Exception.ResourceNotFoundException;

@Service
public class DailyTrackingServiceImpl implements DailyTrackingService {
    private final DailyTrackerRepo trackerRepository;
    private final MealConsumptionRepo mealConsumptionRepository;
    private final FoodDictionaryRepo foodRepository;
    private final ClientProfileRepo profileRepository;
    private final SystemAccountRepo accountRepository;

    public DailyTrackingServiceImpl(DailyTrackerRepo trackerRepository,
                                    MealConsumptionRepo mealConsumptionRepository,
                                    FoodDictionaryRepo foodRepository,
                                    ClientProfileRepo profileRepository,
                                    SystemAccountRepo accountRepository) {
      this.trackerRepository = trackerRepository;
      this.mealConsumptionRepository = mealConsumptionRepository;
      this.foodRepository = foodRepository;
      this.profileRepository = profileRepository;
      this.accountRepository = accountRepository;
    }

    private Long getClientIdFromEmail(String email) {
        SystemAccount account = accountRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + email));
        ClientProfile profile = profileRepository.findByAccountId(account.getId())
            .orElseGet(() -> {
                ClientProfile newProfile = new ClientProfile();
                newProfile.setAccountId(account.getId());
                return profileRepository.save(newProfile);
            });
        return profile.getId();
    }

    @Override
    public DailyTracker getToday(Authentication auth) {
      return getOrCreateCurrentTracker(auth.getName());
    }

    @Override
    public DailyTracker getOrCreateCurrentTracker(String email) {
      Long clientId = getClientIdFromEmail(email);
      return trackerRepository
        .findByClientIdAndDate(clientId, LocalDate.now())
        .orElseGet(() -> {
          DailyTracker tracker = new DailyTracker();
          tracker.setClientId(clientId);
          tracker.setDate(LocalDate.now());
          tracker.setTotalCalories(0.0);
          tracker.setTotalProtein(0.0);
          tracker.setTotalCarbs(0.0);
          tracker.setTotalFat(0.0);
          return trackerRepository.save(tracker);
        });
    }

    @Override
    public List<DailyTracker> getHistory(String email) {
      Long clientId = getClientIdFromEmail(email);
      return trackerRepository.findByClientId(clientId);
    }

    @Override
    public MealConsumption logMeal(Authentication auth, MealConsumptionDto dto) {
      // Stub wrapper, delegating to logMealConsumption
      logMealConsumption(auth.getName(), dto);
      return null;
    }

    @Override
    public void deleteMeal(Long id) {
        MealConsumption meal = mealConsumptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Meal consumption not found: " + id));

        DailyTracker tracker = trackerRepository.findById(meal.getTrackerId())
            .orElseThrow(() -> new ResourceNotFoundException("Daily tracker not found for id: " + meal.getTrackerId()));

        FoodDictionary food = foodRepository.findById(meal.getFoodId())
            .orElseThrow(() -> new ResourceNotFoundException("Food not found: " + meal.getFoodId()));

        double ratio = meal.getConsumedServingG() / food.getBaseServingG();

        tracker.setTotalCalories(Math.max(0.0, tracker.getTotalCalories() - (food.getCaloriesPerServing() * ratio)));
        tracker.setTotalProtein(Math.max(0.0, tracker.getTotalProtein() - (food.getProteinPerServing() * ratio)));
        tracker.setTotalCarbs(Math.max(0.0, tracker.getTotalCarbs() - (food.getCarbsPerServing() * ratio)));
        tracker.setTotalFat(Math.max(0.0, tracker.getTotalFat() - (food.getFatPerServing() * ratio)));

        trackerRepository.save(tracker);
        mealConsumptionRepository.delete(meal);
    }

    @Override
    public DailyTracker logMealConsumption(String email, MealConsumptionDto dto) {
        DailyTracker tracker = getOrCreateCurrentTracker(email);

        FoodDictionary food = foodRepository.findById(dto.getFoodId())
            .orElseThrow(() -> new ResourceNotFoundException("Food not found: " + dto.getFoodId()));

        double ratio = dto.getConsumedServingG() / food.getBaseServingG();

        tracker.setTotalCalories(tracker.getTotalCalories() + (food.getCaloriesPerServing() * ratio));
        tracker.setTotalProtein(tracker.getTotalProtein() + (food.getProteinPerServing() * ratio));
        tracker.setTotalCarbs(tracker.getTotalCarbs() + (food.getCarbsPerServing() * ratio));
        tracker.setTotalFat(tracker.getTotalFat() + (food.getFatPerServing() * ratio));

        MealConsumption meal = new MealConsumption();
        meal.setTrackerId(tracker.getId());
        meal.setFoodId(food.getId());
        meal.setConsumedServingG(dto.getConsumedServingG());
        meal.setMealType(dto.getMealType());
        meal.setLoggedAt(LocalDateTime.now());

        mealConsumptionRepository.save(meal);
        return trackerRepository.save(tracker);
    }

    @Override
    public List<MealConsumption> getTodayMeals(String email) {
        DailyTracker tracker = getOrCreateCurrentTracker(email);
        return mealConsumptionRepository.findByTrackerId(tracker.getId());
    }

    @Override
    public List<MealConsumption> getAllMeals() {
        return mealConsumptionRepository.findAll();
    }

    @Override
    public MealConsumption getMealById(Long id) {
        return mealConsumptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Meal consumption not found: " + id));
    }

    @Override
    public MealConsumption updateMeal(Long id, MealConsumptionDto dto) {
        MealConsumption meal = mealConsumptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Meal consumption not found: " + id));

        DailyTracker tracker = trackerRepository.findById(meal.getTrackerId())
            .orElseThrow(() -> new ResourceNotFoundException("Daily tracker not found for id: " + meal.getTrackerId()));

        FoodDictionary oldFood = foodRepository.findById(meal.getFoodId())
            .orElseThrow(() -> new ResourceNotFoundException("Food not found: " + meal.getFoodId()));

        // reverse old contribution
        double oldRatio = meal.getConsumedServingG() / oldFood.getBaseServingG();
        tracker.setTotalCalories(Math.max(0.0, tracker.getTotalCalories() - (oldFood.getCaloriesPerServing() * oldRatio)));
        tracker.setTotalProtein(Math.max(0.0, tracker.getTotalProtein() - (oldFood.getProteinPerServing() * oldRatio)));
        tracker.setTotalCarbs(Math.max(0.0, tracker.getTotalCarbs() - (oldFood.getCarbsPerServing() * oldRatio)));
        tracker.setTotalFat(Math.max(0.0, tracker.getTotalFat() - (oldFood.getFatPerServing() * oldRatio)));

        // apply new contribution
        FoodDictionary newFood = foodRepository.findById(dto.getFoodId())
            .orElseThrow(() -> new ResourceNotFoundException("Food not found: " + dto.getFoodId()));
        double newRatio = dto.getConsumedServingG() / newFood.getBaseServingG();
        tracker.setTotalCalories(tracker.getTotalCalories() + (newFood.getCaloriesPerServing() * newRatio));
        tracker.setTotalProtein(tracker.getTotalProtein() + (newFood.getProteinPerServing() * newRatio));
        tracker.setTotalCarbs(tracker.getTotalCarbs() + (newFood.getCarbsPerServing() * newRatio));
        tracker.setTotalFat(tracker.getTotalFat() + (newFood.getFatPerServing() * newRatio));

        meal.setFoodId(dto.getFoodId());
        meal.setConsumedServingG(dto.getConsumedServingG());
        meal.setMealType(dto.getMealType());

        trackerRepository.save(tracker);
        return mealConsumptionRepository.save(meal);
    }

}