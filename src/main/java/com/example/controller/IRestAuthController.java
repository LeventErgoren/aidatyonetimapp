package com.example.controller;

import com.example.dto.AuthRequest;
import com.example.dto.AuthResponse;

public interface IRestAuthController {

    AuthResponse authenticate(AuthRequest request);

}
