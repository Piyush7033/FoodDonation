//package com.fooddonation.system.service;
//
//import com.fooddonation.system.entity.ReceiverFood;
//import com.fooddonation.system.repository.ReceiverFoodRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ReceiverFoodService {
//
//    @Autowired
//    private ReceiverFoodRepository receiverFoodRepository;
//
//    // GET AVAILABLE FOODS
//    public List<ReceiverFood> getAvailableFoods() {
//
//        List<ReceiverFood> foods =
//                receiverFoodRepository.findByStatus("AVAILABLE");
//
//        // DEBUG
//        System.out.println("AVAILABLE FOODS: " + foods);
//
//        return foods;
//    }
//}