package com.fooddonation.system.controller;
import com.fooddonation.system.entity.FoodRequest;
import com.fooddonation.system.entity.FoodStatus;
import com.fooddonation.system.repository.FoodRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin("*")
public class FoodRequestController {

    @Autowired
    private FoodRequestRepository foodRequestRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createRequest(@RequestBody FoodRequest request) {

        request.setStatus(FoodStatus.PENDING);

        request.setCreatedAt(LocalDateTime.now());

        FoodRequest savedRequest = foodRequestRepository.save(request);

        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<FoodRequest>> getAllRequests() {

        return ResponseEntity.ok(foodRequestRepository.findAll());
    }

    @GetMapping("/admin/pending")
    public ResponseEntity<List<FoodRequest>> getPendingRequests() {

        return ResponseEntity.ok(
                foodRequestRepository.findByStatus(FoodStatus.PENDING)
        );
    }

    @PutMapping("/admin/approve/{id}")
    public ResponseEntity<?> approveRequest(@PathVariable Long id) {

        FoodRequest request = foodRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(FoodStatus.APPROVED);

        request.setApprovedAt(LocalDateTime.now());

        foodRequestRepository.save(request);

        return ResponseEntity.ok("Request Approved Successfully");
    }

    @PutMapping("/admin/reject/{id}")
    public ResponseEntity<?> rejectRequest(@PathVariable Long id) {

        FoodRequest request = foodRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(FoodStatus.REJECTED);

        foodRequestRepository.save(request);

        return ResponseEntity.ok("Request Rejected");
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<FoodRequest>> getDonorRequests(
            @PathVariable Long donorId) {

        return ResponseEntity.ok(
                foodRequestRepository.findByDonorId(donorId)
        );
    }

    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<FoodRequest>> getReceiverRequests(
            @PathVariable Long receiverId) {

        return ResponseEntity.ok(
                foodRequestRepository.findByReceiverId(receiverId)
        );
    }
}