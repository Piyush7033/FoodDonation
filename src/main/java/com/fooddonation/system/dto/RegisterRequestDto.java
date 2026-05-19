package com.fooddonation.system.dto;

import com.fooddonation.system.entity.Role;

public class RegisterRequestDto {

    private String name;
    private String email;
    private String password;
    private Role role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {   // ✅ fixed
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}