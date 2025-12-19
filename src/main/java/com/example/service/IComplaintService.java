package com.example.service;

import com.example.dto.ComplaintRequest;
import com.example.entity.Complaint;
import java.util.List;

public interface IComplaintService {
    Complaint createComplaint(ComplaintRequest request);
    List<Complaint> getMyComplaints();
    List<Complaint> getAllComplaints(); // Admin için
    void resolveComplaint(Long id); // Admin için
}
