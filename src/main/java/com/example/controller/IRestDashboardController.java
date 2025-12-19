package com.example.controller;

import com.example.entity.ExpenseType;
import java.util.List;

public interface IRestDashboardController {
    List<ExpenseType> getAllExpenseTypes();
}
