package com.example.batiment.controller;

import com.example.batiment.model.DemandeContact;
import com.example.batiment.service.DemandeContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demandes-contact")
@CrossOrigin(origins = "*")
public class DemandeContactController {

    private final DemandeContactService demandeContactService;

    public DemandeContactController(DemandeContactService demandeContactService) {
        this.demandeContactService = demandeContactService;
    }

    @GetMapping
    public List<DemandeContact> getAllDemandesContact() {
        return demandeContactService.getAllDemandesContact();
    }

    @GetMapping("/{id}")
    public Optional<DemandeContact> getDemandeContactById(@PathVariable Long id) {
        return demandeContactService.getDemandeContactById(id);
    }

    @PostMapping
    public DemandeContact createDemandeContact(@RequestBody DemandeContact demandeContact) {
        return demandeContactService.saveDemandeContact(demandeContact);
    }

    @DeleteMapping("/{id}")
    public void deleteDemandeContact(@PathVariable Long id) {
        demandeContactService.deleteDemandeContact(id);
    }
}