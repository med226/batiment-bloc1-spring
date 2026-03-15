package com.example.batiment.controller;

import com.example.batiment.model.Chantier;
import com.example.batiment.model.Client;
import com.example.batiment.model.Intervention;
import com.example.batiment.model.Technicien;
import com.example.batiment.repository.ChantierRepository;
import com.example.batiment.repository.ClientRepository;
import com.example.batiment.repository.InterventionRepository;
import com.example.batiment.repository.TechnicienRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/interventions")
public class InterventionController {

    private final InterventionRepository interventionRepository;
    private final ClientRepository clientRepository;
    private final TechnicienRepository technicienRepository;
    private final ChantierRepository chantierRepository;

    public InterventionController(
            InterventionRepository interventionRepository,
            ClientRepository clientRepository,
            TechnicienRepository technicienRepository,
            ChantierRepository chantierRepository) {
        this.interventionRepository = interventionRepository;
        this.clientRepository = clientRepository;
        this.technicienRepository = technicienRepository;
        this.chantierRepository = chantierRepository;
    }

    @GetMapping
    public String afficherInterventions(Model model) {
        model.addAttribute("interventions", interventionRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("techniciens", technicienRepository.findAll());
        model.addAttribute("chantiers", chantierRepository.findAll());
        return "admin/interventions";
    }

    @PostMapping("/save")
    public String enregistrerIntervention(
            @RequestParam String typeIntervention,
            @RequestParam String categorie,
            @RequestParam String urgence,
            @RequestParam String statut,
            @RequestParam(required = false) String description,
            @RequestParam String dateIntervention,
            @RequestParam(required = false) String montant,
            @RequestParam Long clientId,
            @RequestParam(required = false) Long technicienId,
            @RequestParam(required = false) Long chantierId) {

        Intervention intervention = new Intervention();

        intervention.setTypeIntervention(typeIntervention);
        intervention.setCategorie(categorie);
        intervention.setUrgence(urgence);
        intervention.setStatut(statut);
        intervention.setDescription(description);

        if (dateIntervention != null && !dateIntervention.isBlank()) {
            intervention.setDateIntervention(LocalDateTime.parse(dateIntervention));
        }

        if (montant != null && !montant.isBlank()) {
            intervention.setMontant(new BigDecimal(montant));
        }

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable : " + clientId));
        intervention.setClient(client);

        if (technicienId != null) {
            Technicien technicien = technicienRepository.findById(technicienId)
                    .orElseThrow(() -> new IllegalArgumentException("Technicien introuvable : " + technicienId));
            intervention.setTechnicien(technicien);
        }

        if (chantierId != null) {
            Chantier chantier = chantierRepository.findById(chantierId)
                    .orElseThrow(() -> new IllegalArgumentException("Chantier introuvable : " + chantierId));
            intervention.setChantier(chantier);
        }

        interventionRepository.save(intervention);

        return "redirect:/admin/interventions";
    }

    @GetMapping("/delete/{id}")
    public String supprimerIntervention(@PathVariable Long id) {
        interventionRepository.deleteById(id);
        return "redirect:/admin/interventions";
    }
}