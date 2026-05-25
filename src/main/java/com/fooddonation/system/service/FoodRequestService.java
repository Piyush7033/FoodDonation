//package com.fooddonation.system.service;
//
//import com.fooddonation.system.entity.FoodRequest;
//import com.fooddonation.system.repository.FoodRequestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FoodRequestService {
//
//    @Autowired
//    private FoodRequestRepository repository;
//
//    // GET REQUESTS FOR DONOR
//    public List<FoodRequest> getRequestsByDonor(Long donorId) {
//        return repository.findByDonorId(donorId);
//    }
//
//    // ACCEPT REQUEST
//    public FoodRequest acceptRequest(Long id) {
//        FoodRequest req = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Request not found"));
//
//        req.setStatus("ACCEPTED");
//        return repository.save(req);
//    }
//
//    // REJECT REQUEST
//    public FoodRequest rejectRequest(Long id) {
//        FoodRequest req = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Request not found"));
//
//        req.setStatus("REJECTED");
//        return repository.save(req);
//    }
//}