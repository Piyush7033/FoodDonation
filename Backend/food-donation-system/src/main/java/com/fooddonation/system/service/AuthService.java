package com.fooddonation.system.service;


import com.fooddonation.system.dto.*;

public interface AuthService {
    Object register(RegisterRequestDto dto);
    Object login(LoginRequestDto dto);
}
