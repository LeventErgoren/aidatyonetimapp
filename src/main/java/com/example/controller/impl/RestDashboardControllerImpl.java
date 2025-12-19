package com.example.controller.impl;

import com.example.controller.IRestDashboardController;
import com.example.entity.ExpenseType;
import com.example.service.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
public class RestDashboardControllerImpl implements IRestDashboardController {

    @Autowired
    IDashboardService dashboardService;

    @GetMapping("/expense-types")
    @Override
    public List<ExpenseType> getAllExpenseTypes() {
        return dashboardService.getAllExpenseTypes();
    }
}
