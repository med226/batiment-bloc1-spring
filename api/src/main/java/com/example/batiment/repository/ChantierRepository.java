package com.example.batiment.repository;

import com.example.batiment.model.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChantierRepository extends JpaRepository<Chantier, Long> {
}