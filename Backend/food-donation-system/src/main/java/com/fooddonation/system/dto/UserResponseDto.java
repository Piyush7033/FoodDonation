package com.fooddonation.system.dto;


import com.fooddonation.system.entity.Role;

public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private boolean active;

    public UserResponseDto(Long id, String name, String email,
                           Role role, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    // getters
}
