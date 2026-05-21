package com.fooddonation.system.controller;

import com.fooddonation.system.entity.Food;
import com.fooddonation.system.entity.User;
import com.fooddonation.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return adminService.getAllUsers();
    }

    @PutMapping("/users/{id}/toggle")
    public void toggleUser(@PathVariable Long id) {
        adminService.toggleUser(id);
    }

    @GetMapping("/food")
    public List<Food> getFood() {
        return adminService.getAllFood();
    }

    @DeleteMapping("/food/{id}")
    public void deleteFood(@PathVariable Long id) {
        adminService.deleteFood(id);
    }
}