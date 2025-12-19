package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "debts")
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private LocalDate termDate;
    private LocalDate createdDate;
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private EvSakini resident;

    @ManyToOne
    @JoinColumn(name = "expense_type_id")
    private ExpenseType expenseType;
}
