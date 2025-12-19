package com.example.service.impl;

import com.example.dto.ComplaintRequest;
import com.example.entity.Complaint;
import com.example.entity.EvSakini;
import com.example.entity.Kullanici;
import com.example.exception.BusinessException;
import com.example.repository.ComplaintRepository;
import com.example.repository.EvSakiniRepository;
import com.example.repository.KullaniciRepository;
import com.example.service.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintServiceImpl implements IComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    KullaniciRepository userRepository;

    @Autowired
    EvSakiniRepository residentRepository;

    @Override
    @Transactional
    public Complaint createComplaint(ComplaintRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Kullanici user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("Kullanıcı bulunamadı"));

        EvSakini resident = residentRepository.findByKullaniciId(user.getId())
                .orElseThrow(() -> new BusinessException("Sakin profili bulunamadı"));

        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setStatus("ACIK");
        complaint.setCreatedDate(LocalDateTime.now());
        complaint.setResident(resident);

        return complaintRepository.save(complaint);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Complaint> getMyComplaints() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Kullanici user = userRepository.findByUsername(username).orElseThrow();
        EvSakini resident = residentRepository.findByKullaniciId(user.getId()).orElseThrow();
        
        return complaintRepository.findByResidentId(resident.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    @Transactional
    public void resolveComplaint(Long id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Şikayet bulunamadı"));
        
        complaint.setStatus("COZULDU");
        complaintRepository.save(complaint);
    }
}
