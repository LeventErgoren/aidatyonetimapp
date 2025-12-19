package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "residents")
public class EvSakini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String soyad;
    private String telefon;
    private String email;
    private boolean isOwner; // Ev sahibi mi?

    // Kullanıcı ile ilişki (Cascade ALL: Sakin silinirse User da silinsin/eklensin)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Kullanici kullanici;

    @OneToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
