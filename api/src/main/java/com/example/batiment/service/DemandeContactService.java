package com.example.batiment.service;

import com.example.batiment.model.DemandeContact;
import com.example.batiment.repository.DemandeContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeContactService {

    private final DemandeContactRepository demandeContactRepository;

    public DemandeContactService(DemandeContactRepository demandeContactRepository) {
        this.demandeContactRepository = demandeContactRepository;
    }

    public List<DemandeContact> getAllDemandesContact() {
        return demandeContactRepository.findAll();
    }

    public Optional<DemandeContact> getDemandeContactById(Long id) {
        return demandeContactRepository.findById(id);
    }

    public DemandeContact saveDemandeContact(DemandeContact demandeContact) {
        return demandeContactRepository.save(demandeContact);
    }

    public void deleteDemandeContact(Long id) {
        demandeContactRepository.deleteById(id);
    }
}
