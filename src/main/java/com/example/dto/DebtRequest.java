package com.example.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDate;

@Data
public class DebtRequest {
    
    @NotNull(message = "Tutar boş olamaz")
    @Positive(message = "Tutar 0'dan büyük olmalı")
    private Double amount;

    @NotNull(message = "Gider türü seçilmelidir")
    private Long expenseTypeId; // Örn: 1 (Aidat), 2 (Yakıt)

    @NotNull(message = "Son ödeme tarihi boş olamaz")
    private LocalDate termDate;
}
