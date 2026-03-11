package com.example.batiment.service;

import com.example.batiment.model.LoginRequest;
import com.example.batiment.model.Technicien;
import com.example.batiment.repository.TechnicienRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicienService {

    private final TechnicienRepository technicienRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public TechnicienService(TechnicienRepository technicienRepository) {
        this.technicienRepository = technicienRepository;
    }

    public List<Technicien> getAllTechniciens() {
        return technicienRepository.findAll();
    }

    public Optional<Technicien> getTechnicienById(Long id) {
        return technicienRepository.findById(id);
    }

    public Technicien saveTechnicien(Technicien technicien) {

        // chiffrement du mot de passe
        technicien.setMotDePasse(
                encoder.encode(technicien.getMotDePasse()));

        return technicienRepository.save(technicien);
    }

    public void deleteTechnicien(Long id) {
        technicienRepository.deleteById(id);
    }

    public boolean login(LoginRequest request) {

        Optional<Technicien> technicienOpt = technicienRepository.findByEmail(request.getEmail());

        if (technicienOpt.isEmpty()) {
            return false;
        }

        Technicien technicien = technicienOpt.get();

        boolean passwordOk = encoder.matches(
                request.getMotDePasse(),
                technicien.getMotDePasse());

        boolean roleOk = "TECHNICIEN".equalsIgnoreCase(
                technicien.getRole());

        return passwordOk && roleOk;
    }
}
