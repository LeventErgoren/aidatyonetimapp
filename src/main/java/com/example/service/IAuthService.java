package com.example.service;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.dto.RegisterRequest;

public interface IAuthService {

    AuthResponse authenticate(AuthRequest request);

    AuthResponse register(RegisterRequest request);
}
