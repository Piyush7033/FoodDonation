package com.fooddonation.system.controller;

import com.fooddonation.system.dto.FoodRequestDto;
import com.fooddonation.system.entity.Food;
//import com.fooddonation.system.entity.ReceiverFood;
import com.fooddonation.system.service.FoodService;
//import com.fooddonation.system.service.ReceiverFoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    // ADD FOOD
    @PostMapping
    public ResponseEntity<?> addFood(
            @RequestBody FoodRequestDto dto,
            Principal principal) {

        System.out.println("Add food success");

        return ResponseEntity.ok(
                foodService.addFood(dto, principal.getName())
        );
    }

    // GET ALL AVAILABLE FOODS
    @GetMapping
    public ResponseEntity<?> getAllFood() {

        return ResponseEntity.ok(
                foodService.getAllAvailable()
        );
    }

    // GET MY FOODS
    @GetMapping("/my")
    public ResponseEntity<?> myFood(Principal principal) {

        return ResponseEntity.ok(
                foodService.getMyFood(principal.getName())
        );
    }

    // UPDATE FOOD
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody FoodRequestDto dto) {

        return ResponseEntity.ok(
                foodService.updateFood(id, dto)
        );
    }

    // DELETE FOOD
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        foodService.deleteFood(id);

        return ResponseEntity.ok("Deleted Successfully");
    }

    // RECEIVER SIDE - GET AVAILABLE FOOD
    @GetMapping("/available")
    public ResponseEntity<List<Food>> getAvailableFoods() {

        return ResponseEntity.ok(
                foodService.getAvailableFoods());
    }
}