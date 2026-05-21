package com.fooddonation.system.service;

import com.fooddonation.system.dto.FoodRequestDto;
import com.fooddonation.system.entity.Food;

import java.util.List;

public interface FoodService {


     List<Food> getAvailableFoods();

    Object addFood(FoodRequestDto dto, String email);
    Object getAllAvailable();
    Object getMyFood(String email);
    Object updateFood(Long id, FoodRequestDto dto);
    void deleteFood(Long id);

//    List<Food> getAvailableFoods();
}