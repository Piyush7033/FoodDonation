package com.fooddonation.system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FoodRequestDto {
    private String title;
    private String description;
    private String quantity;
    private String location;
    private LocalDateTime expiryTime;

    // getters & setters

}

