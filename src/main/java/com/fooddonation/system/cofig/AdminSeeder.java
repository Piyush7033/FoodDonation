package com.fooddonation.system.cofig;

import com.fooddonation.system.entity.Role;
import com.fooddonation.system.entity.User;
import com.fooddonation.system.repository.UserRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdmin() {


        boolean exists = userRepository.existsByEmail("admin@gmail.com");

        if (!exists) {

            User admin = new User();
            admin.setName("System Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setActive(true);

            userRepository.save(admin);

            System.out.println("✅ Default Admin Created Successfully");

        } else {

            System.out.println("✅ Admin Already Exists");
        }
    }
}