package com.fooddonation.system.service;

import com.fooddonation.system.entity.User;
import com.fooddonation.system.entity.Food;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();

    void toggleUser(Long id);

    List<Food> getAllFood();

    void deleteFood(Long id);
}