package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    // Login Bilgileri
    @NotEmpty(message = "Kullanıcı adı boş olamaz")
    private String username;

    @NotEmpty(message = "Şifre boş olamaz")
    private String password;

    // Profil (EvSakini) Bilgileri
    @NotEmpty(message = "Ad alanı boş olamaz")
    private String ad;

    @NotEmpty(message = "Soyad alanı boş olamaz")
    private String soyad;

    private String telefon; // Opsiyonel olabilir

}
