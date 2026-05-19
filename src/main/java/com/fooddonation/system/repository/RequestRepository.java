package com.fooddonation.system.repository;

import com.fooddonation.system.entity.Request;
import com.fooddonation.system.entity.User;
import com.fooddonation.system.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByReceiver(User receiver);

    List<Request> findByFood(Food food);
}