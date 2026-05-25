package com.fooddonation.system.controller;

import com.fooddonation.system.dto.LoginRequestDto;
import com.fooddonation.system.dto.RegisterRequestDto;
import com.fooddonation.system.entity.Role;
import com.fooddonation.system.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = {
                "http://localhost:3000",
                "http://localhost:5173"
        },
        allowCredentials = "true"
)
public class AuthController {

    @Autowired
    private AuthService authService;

    // ==========================================
    // REGISTER
    // ==========================================
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequestDto dto
    ) {

        try {

            // BLOCK ADMIN REGISTRATION
            if (dto.getRole() == Role.ADMIN) {

                Map<String, Object> error =
                        new HashMap<>();

                error.put(
                        "success",
                        false
                );

                error.put(
                        "message",
                        "Admin registration is not allowed"
                );

                return ResponseEntity
                        .badRequest()
                        .body(error);
            }

            Object response =
                    authService.register(dto);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            Map<String, Object> error =
                    new HashMap<>();

            error.put(
                    "success",
                    false
            );

            error.put(
                    "message",
                    e.getMessage()
            );

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    // ==========================================
    // LOGIN
    // ==========================================
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequestDto dto
    ) {

        try {

            // LOGIN RESPONSE FROM SERVICE
            Object response =
                    authService.login(dto);

            // DEBUG
            System.out.println(
                    "✅ LOGIN SUCCESS RESPONSE: "
                            + response
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            e.printStackTrace();

            Map<String, Object> error =
                    new HashMap<>();

            error.put(
                    "success",
                    false
            );

            error.put(
                    "message",
                    e.getMessage()
            );

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(error);
        }
    }

    // ==========================================
    // TEST API
    // ==========================================
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> test() {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "success",
                true
        );

        response.put(
                "message",
                "Auth API Working Successfully"
        );

        return ResponseEntity.ok(response);
    }
}