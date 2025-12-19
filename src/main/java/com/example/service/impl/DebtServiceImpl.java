package com.example.service.impl;

import com.example.dto.DebtRequest;
import com.example.entity.*;
import com.example.exception.BusinessException;
import com.example.repository.*;
import com.example.service.IDebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DebtServiceImpl implements IDebtService {

    @Autowired
    DebtRepository debtRepository;

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    ExpenseTypeRepository expenseTypeRepository;
    
    @Autowired
    EvSakiniRepository residentRepository;
    
    @Autowired
    KullaniciRepository userRepository;

    @Override
    @Transactional
    public void assignDebtToBlock(Long blockId, DebtRequest request) {
        Block block = blockRepository.findById(blockId)
                .orElseThrow(() -> new BusinessException("Blok bulunamadı"));

        ExpenseType type = expenseTypeRepository.findById(request.getExpenseTypeId())
                .orElseThrow(() -> new BusinessException("Gider türü bulunamadı"));

        List<Debt> debtList = new ArrayList<>();

        for (Apartment flat : block.getApartments()) {
            if (flat.getResident() != null) {
                Debt debt = new Debt();
                debt.setAmount(request.getAmount());
                debt.setTermDate(request.getTermDate());
                debt.setCreatedDate(LocalDate.now());
                debt.setPaid(false);
                debt.setExpenseType(type);
                debt.setResident(flat.getResident());

                debtList.add(debt);
            }
        }
        
        if (debtList.isEmpty()) {
             throw new BusinessException("Bu blokta borçlandırılacak dolu daire yok.");
        }

        debtRepository.saveAll(debtList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Debt> getMyDebts() {
        // Giriş yapan kullanıcıyı bul
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Kullanici user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("Kullanıcı bulunamadı"));

        EvSakini resident = residentRepository.findByKullaniciId(user.getId())
                .orElseThrow(() -> new BusinessException("Sakin profili bulunamadı"));

        return debtRepository.findByResidentId(resident.getId());
    }
}
