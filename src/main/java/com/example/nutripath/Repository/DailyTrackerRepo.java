package com.example.nutripath.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nutripath.Entity.DailyTracker;

public interface DailyTrackerRepo extends JpaRepository<DailyTracker, Long> {

    Optional<DailyTracker> findByClientIdAndDate(Long clientId, LocalDate date);

    List<DailyTracker> findByClientId(Long clientId);

}