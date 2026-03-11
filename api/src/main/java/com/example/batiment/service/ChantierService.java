package com.example.batiment.service;

import com.example.batiment.model.Chantier;
import com.example.batiment.repository.ChantierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChantierService {

    private final ChantierRepository chantierRepository;

    public ChantierService(ChantierRepository chantierRepository) {
        this.chantierRepository = chantierRepository;
    }

    public List<Chantier> getAllChantiers() {
        return chantierRepository.findAll();
    }

    public Optional<Chantier> getChantierById(Long id) {
        return chantierRepository.findById(id);
    }

    public Chantier saveChantier(Chantier chantier) {
        return chantierRepository.save(chantier);
    }

    public void deleteChantier(Long id) {
        chantierRepository.deleteById(id);
    }
}