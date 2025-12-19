package com.example.service;

import com.example.dto.PaymentRequest;

public interface IPaymentService {
    void payDebt(PaymentRequest request);
}
