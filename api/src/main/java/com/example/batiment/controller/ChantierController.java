package com.example.batiment.controller;

import com.example.batiment.model.Chantier;
import com.example.batiment.service.ChantierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chantiers")
public class ChantierController {

    private final ChantierService chantierService;

    public ChantierController(ChantierService chantierService) {
        this.chantierService = chantierService;
    }

    @GetMapping
    public List<Chantier> getAllChantiers() {
        return chantierService.getAllChantiers();
    }

    @GetMapping("/{id}")
    public Optional<Chantier> getChantierById(@PathVariable Long id) {
        return chantierService.getChantierById(id);
    }

    @PostMapping
    public Chantier createChantier(@RequestBody Chantier chantier) {
        return chantierService.saveChantier(chantier);
    }

    @DeleteMapping("/{id}")
    public void deleteChantier(@PathVariable Long id) {
        chantierService.deleteChantier(id);
    }
}