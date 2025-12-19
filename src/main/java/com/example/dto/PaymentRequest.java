package com.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotNull(message = "Hangi borcun ödeneceği seçilmelidir")
    private Long debtId;

    @NotEmpty(message = "Kart numarası girilmelidir")
    private String cardNumber;
}
