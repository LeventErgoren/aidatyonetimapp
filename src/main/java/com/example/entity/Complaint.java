package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status; // ACIK, KAPALI
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private EvSakini resident;
}
