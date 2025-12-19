package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // A Blok

    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Apartment> apartments;
}
