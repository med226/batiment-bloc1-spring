package com.example.batiment.controller;

import com.example.batiment.model.LoginRequest;
import com.example.batiment.model.Technicien;
import com.example.batiment.service.TechnicienService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/techniciens")
public class TechnicienController {

    private final TechnicienService technicienService;

    public TechnicienController(TechnicienService technicienService) {
        this.technicienService = technicienService;
    }

    @GetMapping
    public List<Technicien> getAllTechniciens() {
        return technicienService.getAllTechniciens();
    }

    @GetMapping("/{id}")
    public Optional<Technicien> getTechnicienById(@PathVariable Long id) {
        return technicienService.getTechnicienById(id);
    }

    @PostMapping
    public Technicien createTechnicien(@RequestBody Technicien technicien) {
        return technicienService.saveTechnicien(technicien);
    }

    @DeleteMapping("/{id}")
    public void deleteTechnicien(@PathVariable Long id) {
        technicienService.deleteTechnicien(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        boolean ok = technicienService.login(request);

        if (ok) {
            return "Connexion réussie";
        }

        return "Email ou mot de passe incorrect";
    }
}