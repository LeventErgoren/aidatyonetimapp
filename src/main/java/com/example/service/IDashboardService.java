package com.example.service;

import com.example.entity.ExpenseType;
import java.util.List;

public interface IDashboardService {
    List<ExpenseType> getAllExpenseTypes();
}
