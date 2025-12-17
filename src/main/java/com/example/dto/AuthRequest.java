package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotEmpty(message = "Kullanıcı Adı Boş Olamaz")
    private String username;

    @NotEmpty
    private String password;

}
