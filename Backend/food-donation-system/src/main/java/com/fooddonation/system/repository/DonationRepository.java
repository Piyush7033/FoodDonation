package com.fooddonation.system.repository;

import com.fooddonation.system.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    // Get donations by donor ID
    List<Donation> findByDonorId(Long donorId);

    // Get donations by status
    List<Donation> findByStatus(String status);

    // Get donations by location
    List<Donation> findByLocation(String location);

    // Search food by food name
    List<Donation> findByFoodNameContainingIgnoreCase(String foodName);
}