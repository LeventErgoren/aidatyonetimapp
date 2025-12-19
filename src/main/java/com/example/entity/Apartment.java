package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int apartmentNo;
    private int floor;
    private String type; // 3+1
    private boolean isEmpty;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private Block block;

    @OneToOne(mappedBy = "apartment")
    @JsonIgnore
    private EvSakini resident;
}
