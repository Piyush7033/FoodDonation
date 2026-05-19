package com.fooddonation.system.dto;

import com.fooddonation.system.entity.FoodStatus;

import java.time.LocalDateTime;

public class FoodResponseDto {

    private Long id;
    private String title;
    private String description;
    private String quantity;
    private String location;
    private LocalDateTime expiryTime;
    private FoodStatus status;
    private String donorName;

    public FoodResponseDto(Long id, String title, String description, String quantity,
                           String location, LocalDateTime expiryTime,
                           FoodStatus status, String donorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.location = location;
        this.expiryTime = expiryTime;
        this.status = status;
        this.donorName = donorName;
    }

    // getters
}