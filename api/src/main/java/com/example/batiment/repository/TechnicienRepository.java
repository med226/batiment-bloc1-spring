package com.example.batiment.repository;

import com.example.batiment.model.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
    Optional<Technicien> findByEmail(String email);
}