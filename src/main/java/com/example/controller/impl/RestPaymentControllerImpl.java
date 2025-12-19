package com.example.controller.impl;

import com.example.controller.IRestPaymentController;
import com.example.dto.PaymentRequest;
import com.example.service.IPaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class RestPaymentControllerImpl implements IRestPaymentController {

    @Autowired
    IPaymentService paymentService;

    @PostMapping("/pay")
    @Override
    public void payDebt(@Valid @RequestBody PaymentRequest request) {
        paymentService.payDebt(request);
    }
}
