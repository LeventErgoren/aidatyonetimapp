package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotEmpty
@AllArgsConstructor
public class AuthResponse {

    private String accessToken;

}
