package com.fooddonation.system.controller;

import com.fooddonation.system.entity.Role;
import com.fooddonation.system.entity.User;

import com.fooddonation.system.repository.UserRepository;
import com.fooddonation.system.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "http://localhost:3000"
        }
)
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {

        return ResponseEntity.ok(
                Map.of(
                        "totalUsers", adminService.totalUsers(),
                        "activeUsers", adminService.totalActiveUsers(),
                        "disabledUsers", adminService.totalDisabledUsers(),
                        "totalFood", adminService.totalFoodItems()
                )
        );
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {

        List<User> users =
                adminService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/donors")
    public ResponseEntity<?> getDonors() {

        return ResponseEntity.ok(
                userRepository.findByRole(Role.DONOR)
        );
    }

    @GetMapping("/receivers")
    public ResponseEntity<?> getReceivers() {

        return ResponseEntity.ok(
                userRepository.findByRole(Role.RECEIVER)
        );
    }

    @PutMapping("/users/{id}/toggle")
    public ResponseEntity<?> toggleUser(
            @PathVariable Long id
    ) {

        adminService.toggleUser(id);

        return ResponseEntity.ok(
                "User status updated successfully"
        );
    }

    @PutMapping("/users/{id}/enable")
    public ResponseEntity<?> enableUser(
            @PathVariable Long id
    ) {

        adminService.enableUser(id);

        return ResponseEntity.ok(
                "User enabled successfully"
        );
    }

    @PutMapping("/users/{id}/disable")
    public ResponseEntity<?> disableUser(
            @PathVariable Long id
    ) {

        adminService.disableUser(id);

        return ResponseEntity.ok(
                "User disabled successfully"
        );
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id
    ) {

        adminService.deleteUser(id);

        return ResponseEntity.ok(
                "User deleted successfully"
        );
    }

    @GetMapping("/food")
    public ResponseEntity<?> getFood() {

        return ResponseEntity.ok(
                adminService.getAllFood()
        );
    }

    @PutMapping("/food/{id}/accept")
    public ResponseEntity<?> acceptFood(
            @PathVariable Long id
    ) {

        adminService.acceptFood(id);

        return ResponseEntity.ok(
                "Food accepted successfully"
        );
    }

    @PutMapping("/food/{id}/reject")
    public ResponseEntity<?> rejectFood(
            @PathVariable Long id
    ) {

        adminService.rejectFood(id);

        return ResponseEntity.ok(
                "Food rejected successfully"
        );
    }

    @DeleteMapping("/food/{id}")
    public ResponseEntity<?> deleteFood(
            @PathVariable Long id
    ) {

        adminService.deleteFood(id);

        return ResponseEntity.ok(
                "Food deleted successfully"
        );
    }
}