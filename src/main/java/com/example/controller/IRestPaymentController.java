package com.example.controller;

import com.example.dto.PaymentRequest;

public interface IRestPaymentController {
    void payDebt(PaymentRequest request);
}
