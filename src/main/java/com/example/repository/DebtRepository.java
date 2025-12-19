package com.example.repository;

import com.example.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    // Sakin'in ID'sine göre borçları getir
    List<Debt> findByResidentId(Long residentId);

    // Sakin'in ödenmemiş borçlarını getir (MyDebts için)
    List<Debt> findAllByResidentIdAndIsPaidFalse(Long residentId);
}
