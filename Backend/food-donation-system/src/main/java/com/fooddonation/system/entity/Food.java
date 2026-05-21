package com.fooddonation.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private String quantity;
    private String location;
    private LocalDateTime expiryTime;

    @Enumerated(EnumType.STRING)
    private FoodStatus status;

    @ManyToOne
    private User donor;


}