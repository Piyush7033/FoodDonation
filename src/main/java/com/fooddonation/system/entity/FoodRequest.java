package com.fooddonation.system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "food_requests")
public class FoodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Food Details
    private Long foodId;

    private String foodTitle;

    private Integer quantity;

    private String location;

    // Donor Details
    private Long donorId;

    private String donorName;

    private String donorEmail;

    // Receiver Details
    private Long receiverId;

    private String receiverName;

    private String receiverEmail;


    // Admin Approval Status
    @Enumerated(EnumType.STRING)
    private FoodStatus status;

    // Admin Response
    private String adminMessage;

    private Long approvedByAdminId;

    private String approvedByAdminName;

    // Time Management
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    private LocalDateTime approvedAt;

    // Automatically update time when status changes
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}