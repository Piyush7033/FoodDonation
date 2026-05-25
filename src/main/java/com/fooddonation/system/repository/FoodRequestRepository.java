package com.fooddonation.system.repository;

import com.fooddonation.system.entity.FoodRequest;
import com.fooddonation.system.entity.FoodStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRequestRepository extends JpaRepository<FoodRequest, Long> {

    // Donor Requests
    List<FoodRequest> findByDonorId(Long donorId);

    // Receiver Requests
    List<FoodRequest> findByReceiverId(Long receiverId);

    // Status Based Requests
    List<FoodRequest> findByStatus(FoodStatus status);

    // Food Specific Requests
    List<FoodRequest> findByFoodId(Long foodId);

    // Admin Pending Requests
    List<FoodRequest> findByStatusOrderByCreatedAtDesc(FoodStatus status);

    // Receiver + Status
    List<FoodRequest> findByReceiverIdAndStatus(Long receiverId, FoodStatus status);

    // Donor + Status
    List<FoodRequest> findByDonorIdAndStatus(Long donorId, FoodStatus status);
}