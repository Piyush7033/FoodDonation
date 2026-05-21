package com.fooddonation.system.service;

import com.fooddonation.system.dto.FoodRequestDto;
import com.fooddonation.system.entity.*;
import com.fooddonation.system.repository.FoodRepository;
import com.fooddonation.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public Object addFood(FoodRequestDto dto, String email) {
        User donor = userRepo.findByEmail(email).orElseThrow();

        Food food = new Food();
        food.setTitle(dto.getTitle());
        food.setDescription(dto.getDescription());
        food.setQuantity(dto.getQuantity());
        food.setLocation(dto.getLocation());
        food.setExpiryTime(dto.getExpiryTime());
        food.setStatus(FoodStatus.AVAILABLE);
        food.setDonor(donor);

        return foodRepo.save(food);
    }

    @Override
    public Object getAllAvailable() {
        return foodRepo.findByStatus(FoodStatus.AVAILABLE);
    }

    @Override
    public Object getMyFood(String email) {
        User donor = userRepo.findByEmail(email).orElseThrow();
        return foodRepo.findByDonor(donor);
    }

    @Override
    public Object updateFood(Long id, FoodRequestDto dto) {
        Food food = foodRepo.findById(id).orElseThrow();

        food.setTitle(dto.getTitle());
        food.setDescription(dto.getDescription());
        food.setQuantity(String.valueOf(dto.getQuantity()));
        food.setLocation(dto.getLocation());
        food.setExpiryTime(dto.getExpiryTime());

        return foodRepo.save(food);
    }

    @Override
    public void deleteFood(Long id) {
        foodRepo.deleteById(id);
    }

    @Override
    public List<Food> getAvailableFoods() {

        List<Food> foodList = foodRepo.findByStatusAndExpiryTimeAfter(
                FoodStatus.AVAILABLE,
                LocalDateTime.now()
        );

        return foodList;
    }

}