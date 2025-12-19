package com.example.controller.impl;

import com.example.controller.IRestDebtController;
import com.example.dto.DebtRequest;
import com.example.entity.Debt;
import com.example.service.IDebtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
public class RestDebtControllerImpl implements IRestDebtController {

    @Autowired
    IDebtService debtService;

    @PostMapping("/assign/block/{blockId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void assignDebtToBlock(@PathVariable Long blockId, @Valid @RequestBody DebtRequest request) {
        debtService.assignDebtToBlock(blockId, request);
    }

    @GetMapping("/my-debts")
    @Override
    public List<Debt> getMyDebts() {
        return debtService.getMyDebts();
    }
}
