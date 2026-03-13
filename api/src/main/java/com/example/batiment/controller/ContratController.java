package com.example.batiment.controller;

import com.example.batiment.model.Client;
import com.example.batiment.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/contrats")
public class ContratController {

    private final ClientRepository clientRepository;

    public ContratController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String afficherFormulaire(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "contrats/form";
    }

    @PostMapping("/generer")
    public String genererContrat(@RequestParam Long clientId,
            @RequestParam String libelle,
            @RequestParam String montant,
            Model model) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));

        model.addAttribute("client", client);
        model.addAttribute("libelle", libelle);
        model.addAttribute("montant", montant);

        return "contrats/view";
    }

    @GetMapping("/liste")
    public String listeContrats(Model model) {
        model.addAttribute("contrats", java.util.Collections.emptyList());
        return "contrats/list";
    }
}