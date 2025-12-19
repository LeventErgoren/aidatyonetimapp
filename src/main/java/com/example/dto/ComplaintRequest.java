package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ComplaintRequest {

    @NotEmpty(message = "Başlık boş olamaz")
    private String title;

    @NotEmpty(message = "Açıklama boş olamaz")
    private String description;
}
