package com.fooddonation.system.service;

import com.fooddonation.system.entity.*;
import com.fooddonation.system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepo;

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public Request createRequest(Long foodId, String email) {
        User receiver = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Food food = foodRepo.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        Request request = new Request();
        request.setFood(food);
        request.setReceiver(receiver);
        request.setStatus(RequestStatus.REQUESTED); // ✅ fixed
        request.setRequestTime(LocalDateTime.now());

        food.setStatus(FoodStatus.REQUESTED);
        foodRepo.save(food);

        return requestRepo.save(request);
    }

    @Override
    public List<Request> getMyRequests(String email) {
        User receiver = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return requestRepo.findByReceiver(receiver);
    }

    @Override
    public Request acceptRequest(Long requestId) {
        Request request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(RequestStatus.APPROVED);

        Food food = request.getFood();
        food.setStatus(FoodStatus.ACCEPTED);

        foodRepo.save(food);

        return requestRepo.save(request);
    }
}