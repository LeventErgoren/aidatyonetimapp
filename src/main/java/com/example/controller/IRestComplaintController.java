package com.example.controller;

import com.example.dto.ComplaintRequest;
import com.example.entity.Complaint;
import java.util.List;

public interface IRestComplaintController {
    Complaint createComplaint(ComplaintRequest request);
    List<Complaint> getMyComplaints();
    List<Complaint> getAllComplaints();
    void resolveComplaint(Long id);
}
