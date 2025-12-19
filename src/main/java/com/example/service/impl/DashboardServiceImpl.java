package com.example.service.impl;

import com.example.entity.ExpenseType;
import com.example.repository.ExpenseTypeRepository;
import com.example.service.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DashboardServiceImpl implements IDashboardService {

    @Autowired
    ExpenseTypeRepository expenseTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseType> getAllExpenseTypes() {
        return expenseTypeRepository.findAll();
    }
}
