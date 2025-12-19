package com.example.controller.impl;

import com.example.controller.IRestComplaintController;
import com.example.dto.ComplaintRequest;
import com.example.entity.Complaint;
import com.example.service.IComplaintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/complaints")
public class RestComplaintControllerImpl implements IRestComplaintController {

    @Autowired
    IComplaintService complaintService;

    @PostMapping("/create")
    @Override
    public Complaint createComplaint(@Valid @RequestBody ComplaintRequest request) {
        return complaintService.createComplaint(request);
    }

    @GetMapping("/my-complaints")
    @Override
    public List<Complaint> getMyComplaints() {
        return complaintService.getMyComplaints();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @PutMapping("/resolve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void resolveComplaint(@PathVariable Long id) {
        complaintService.resolveComplaint(id);
    }
}
