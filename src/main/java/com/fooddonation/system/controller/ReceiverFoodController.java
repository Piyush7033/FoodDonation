//package com.fooddonation.system.controller;
//
//import com.fooddonation.system.entity.ReceiverFood;
//import com.fooddonation.system.service.ReceiverFoodService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/food")
//@CrossOrigin(
//        origins = "http://localhost:5173",
//        allowedHeaders = "*",
//        methods = {
//                RequestMethod.GET,
//                RequestMethod.POST,
//                RequestMethod.PUT,
//                RequestMethod.DELETE,
//                RequestMethod.OPTIONS
//        }
//)
//public class ReceiverFoodController {
//
//    @Autowired
//    private ReceiverFoodService receiverFoodService;
//
//    // GET AVAILABLE FOODS
//    @GetMapping("/available")
//    public ResponseEntity<List<ReceiverFood>> getAvailableFoods() {
//
//        List<ReceiverFood> foods =
//                receiverFoodService.getAvailableFoods();
//
//        return ResponseEntity.ok(foods);
//    }
//
//    // TEST API
//    @GetMapping("/test")
//    public String testApi() {
//
//        return "Food API Working";
//    }
//}