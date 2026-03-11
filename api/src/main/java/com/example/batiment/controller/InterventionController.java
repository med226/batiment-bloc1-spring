package com.example.batiment.controller;

import com.example.batiment.model.Intervention;
import com.example.batiment.model.InterventionRequest;
import com.example.batiment.service.InterventionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interventions")
public class InterventionController {

    private final InterventionService interventionService;

    public InterventionController(InterventionService interventionService) {
        this.interventionService = interventionService;
    }

    @GetMapping
    public List<Intervention> getAllInterventions() {
        return interventionService.getAllInterventions();
    }

    @GetMapping("/{id}")
    public Optional<Intervention> getInterventionById(@PathVariable Long id) {
        return interventionService.getInterventionById(id);
    }

    @PostMapping
    public Intervention createIntervention(@RequestBody InterventionRequest request) {
        return interventionService.saveInterventionFromRequest(request);
    }

    @DeleteMapping("/{id}")
    public void deleteIntervention(@PathVariable Long id) {
        interventionService.deleteIntervention(id);
    }
}