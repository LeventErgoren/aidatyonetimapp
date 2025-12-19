package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MessageRequest {

    @NotEmpty(message = "Başlık boş olamaz")
    private String title;

    @NotEmpty(message = "İçerik boş olamaz")
    private String content;

    @NotEmpty(message = "Hedef kitle seçilmelidir")
    private String targetAudience; // ALL, BLOCK_A, BLOCK_B
}
