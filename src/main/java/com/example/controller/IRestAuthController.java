package com.example.controller;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;
import com.example.dto.RegisterRequest;

public interface IRestAuthController {

    AuthResponse authenticate(AuthRequest request);

    AuthResponse register(RegisterRequest request);
}
