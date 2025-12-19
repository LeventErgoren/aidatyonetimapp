package com.example.service.impl;

import com.example.dto.PaymentRequest;
import com.example.entity.Debt;
import com.example.entity.Payment;
import com.example.exception.BusinessException;
import com.example.repository.DebtRepository;
import com.example.repository.PaymentRepository;
import com.example.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    DebtRepository debtRepository;

    @Override
    @Transactional
    public void payDebt(PaymentRequest request) {
        Debt debt = debtRepository.findById(request.getDebtId())
                .orElseThrow(() -> new BusinessException("Borç bulunamadı"));

        if (debt.isPaid()) {
            throw new BusinessException("Bu borç zaten ödenmiş!");
        }

        Payment payment = new Payment();
        payment.setAmount(debt.getAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setCreditCardNumber(request.getCardNumber()); // Maskeleme yapılabilir
        payment.setDebt(debt);

        debt.setPaid(true);

        paymentRepository.save(payment);
        debtRepository.save(debt);
    }
}
