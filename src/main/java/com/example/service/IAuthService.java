package com.example.service;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;

public interface IAuthService {

    AuthResponse authenticate(AuthRequest request);
}
