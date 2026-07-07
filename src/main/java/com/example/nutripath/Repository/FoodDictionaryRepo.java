package com.example.nutripath.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nutripath.Entity.FoodDictionary;

public interface FoodDictionaryRepo extends JpaRepository<FoodDictionary, Long>{
  List<FoodDictionary> findByItemNameContainingIgnoreCase(String query);
  
}
