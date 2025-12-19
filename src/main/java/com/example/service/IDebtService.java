package com.example.service;

import com.example.dto.DebtRequest;
import com.example.entity.Debt;
import java.util.List;

public interface IDebtService {
    void assignDebtToBlock(Long blockId, DebtRequest request);
    List<Debt> getMyDebts();
}
