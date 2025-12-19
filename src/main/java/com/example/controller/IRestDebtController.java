package com.example.controller;

import com.example.dto.DebtRequest;
import com.example.entity.Debt;
import java.util.List;

public interface IRestDebtController {
    void assignDebtToBlock(Long blockId, DebtRequest request);
    List<Debt> getMyDebts();
}
