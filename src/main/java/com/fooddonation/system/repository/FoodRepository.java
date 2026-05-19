package com.fooddonation.system.repository;

import com.fooddonation.system.entity.Food;
import com.fooddonation.system.entity.FoodStatus;
import com.fooddonation.system.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    // GET ALL AVAILABLE FOOD
    List<Food> findByStatus(FoodStatus status);

    // GET DONOR FOOD
    List<Food> findByDonor(User donor);

    // FILTER FOOD BY LOCATION
    List<Food> findByLocationContainingIgnoreCase(String location);

    // FIND EXPIRED FOOD
    List<Food> findByExpiryTimeBefore(LocalDateTime time);

    // RECEIVER SIDE - AVAILABLE FOOD ONLY
    List<Food> findByStatusAndExpiryTimeAfter(
            FoodStatus status,
            LocalDateTime time
    );
}