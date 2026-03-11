package com.example.batiment.repository;

import com.example.batiment.model.DemandeContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeContactRepository extends JpaRepository<DemandeContact, Long> {
}
