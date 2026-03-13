package com.example.batiment.controller;

import com.example.batiment.model.Chantier;
import com.example.batiment.service.ChantierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/chantiers")
public class AdminChantierController {

    private final ChantierService chantierService;

    public AdminChantierController(ChantierService chantierService) {
        this.chantierService = chantierService;
    }

    @GetMapping
    public String listeChantiers(Model model) {
        model.addAttribute("chantiers", chantierService.getAllChantiers());
        model.addAttribute("chantier", new Chantier());
        return "chantiers";
    }

    @PostMapping("/save")
    public String enregistrerChantier(@ModelAttribute Chantier chantier) {
        chantierService.saveChantier(chantier);
        return "redirect:/admin/chantiers";
    }

    @GetMapping("/edit/{id}")
    public String modifierChantier(@PathVariable Long id, Model model) {
        Chantier chantier = chantierService.getChantierById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chantier introuvable : " + id));
        model.addAttribute("chantier", chantier);
        return "chantier-form";
    }

    @GetMapping("/delete/{id}")
    public String supprimerChantier(@PathVariable Long id) {
        chantierService.deleteChantier(id);
        return "redirect:/admin/chantiers";
    }
}