package com.fooddonation.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Food name
    @Column(nullable = false)
    private String foodName;

    // Quantity
    @Column(nullable = false)
    private String quantity;

    // Donation location
    @Column(nullable = false)
    private String location;

    // Available / Pending / Completed
    @Column(nullable = false)
    private String status;

    // Donation Time
    @Column(name = "donated_at")
    private LocalDateTime donatedAt;

    // Donor ID
    @Column(name = "donor_id")
    private Long donorId;

    // Automatically set current time before saving
    @PrePersist
    public void prePersist() {
        donatedAt = LocalDateTime.now();
    }
}