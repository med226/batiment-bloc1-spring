package com.example.batiment.service;

import com.example.batiment.model.Chantier;
import com.example.batiment.model.Client;
import com.example.batiment.model.Intervention;
import com.example.batiment.model.InterventionRequest;
import com.example.batiment.model.Technicien;
import com.example.batiment.repository.ChantierRepository;
import com.example.batiment.repository.ClientRepository;
import com.example.batiment.repository.InterventionRepository;
import com.example.batiment.repository.TechnicienRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InterventionService {

    private final InterventionRepository interventionRepository;
    private final ClientRepository clientRepository;
    private final TechnicienRepository technicienRepository;
    private final ChantierRepository chantierRepository;

    public InterventionService(
            InterventionRepository interventionRepository,
            ClientRepository clientRepository,
            TechnicienRepository technicienRepository,
            ChantierRepository chantierRepository) {
        this.interventionRepository = interventionRepository;
        this.clientRepository = clientRepository;
        this.technicienRepository = technicienRepository;
        this.chantierRepository = chantierRepository;
    }

    public List<Intervention> getAllInterventions() {
        return interventionRepository.findAll();
    }

    public Optional<Intervention> getInterventionById(Long id) {
        return interventionRepository.findById(id);
    }

    public Intervention saveIntervention(Intervention intervention) {
        return interventionRepository.save(intervention);
    }

    public Intervention saveInterventionFromRequest(InterventionRequest request) {
        Intervention intervention = new Intervention();

        intervention.setUrgence(request.getUrgence());
        intervention.setStatut(request.getStatut());
        intervention.setDescription(request.getDescription());
        intervention.setCategorie(request.getCategorie());
        intervention.setTypeIntervention(request.getTypeIntervention());
        intervention.setMontant(request.getMontant());
        intervention.setDateIntervention(LocalDateTime.now());

        if (request.getClientId() != null) {
            Client client = clientRepository.findById(request.getClientId()).orElse(null);
            intervention.setClient(client);
        }

        if (request.getTechnicienId() != null) {
            Technicien technicien = technicienRepository.findById(request.getTechnicienId()).orElse(null);
            intervention.setTechnicien(technicien);
        }

        if (request.getChantierId() != null) {
            Chantier chantier = chantierRepository.findById(request.getChantierId()).orElse(null);
            intervention.setChantier(chantier);
        }

        return interventionRepository.save(intervention);
    }

    public void deleteIntervention(Long id) {
        interventionRepository.deleteById(id);
    }
}