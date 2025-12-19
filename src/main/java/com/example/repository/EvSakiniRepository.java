package com.example.repository;

import com.example.entity.EvSakini;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvSakiniRepository extends JpaRepository<EvSakini, Long> {
    Optional<EvSakini> findByKullaniciId(Long kullaniciId);
}
