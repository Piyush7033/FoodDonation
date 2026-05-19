package com.fooddonation.system.service;

import com.fooddonation.system.entity.User;
import com.fooddonation.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email)
                );

        // ✅ SAFE ROLE HANDLING (NULL + EMPTY SAFE)
        String role = "ROLE_USER";

        String dbRole = String.valueOf(user.getRole());

        if (dbRole != null && !dbRole.isBlank()) {
            role = "ROLE_" + dbRole.toUpperCase();
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(role))
        );
    }
}