package com.fooddonation.system.service;

import com.fooddonation.system.entity.User;

import com.fooddonation.system.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String email
    ) throws UsernameNotFoundException {

        // ==========================================
        // FIND USER BY EMAIL
        // ==========================================
        User user =
                userRepository.findByEmail(email)

                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User not found with email: "
                                                + email
                                )
                        );

        // ==========================================
        // SAFE ROLE HANDLING
        // ==========================================
        String role = "ROLE_USER";

        if (
                user.getRole() != null
        ) {

            role =
                    "ROLE_" +
                            user.getRole()
                                    .name()
                                    .toUpperCase();
        }

        // ==========================================
        // RETURN USER DETAILS
        // ==========================================
        return new org.springframework.security.core.userdetails.User(

                // EMAIL
                user.getEmail(),

                // PASSWORD
                user.getPassword(),

                // ACCOUNT ENABLED
                user.isActive(),

                // ACCOUNT NON EXPIRED
                true,

                // CREDENTIALS NON EXPIRED
                true,

                // ACCOUNT NON LOCKED
                true,

                // AUTHORITIES / ROLES
                List.of(
                        new SimpleGrantedAuthority(role)
                )
        );
    }
}