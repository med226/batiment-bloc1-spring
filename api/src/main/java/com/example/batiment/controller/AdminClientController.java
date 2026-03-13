package com.example.batiment.controller;

import com.example.batiment.model.Client;
import com.example.batiment.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/clients")
public class AdminClientController {

    private final ClientService clientService;

    public AdminClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String listeClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("client", new Client());
        return "clients";
    }

    @PostMapping("/save")
    public String enregistrerClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/admin/clients";
    }

    @GetMapping("/new")
    public String afficherFormulaireClient(Model model) {
        model.addAttribute("client", new Client());
        return "client-form";
    }

    @GetMapping("/edit/{id}")
    public String modifierClient(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable : " + id));
        model.addAttribute("client", client);
        return "client-form";
    }

    @GetMapping("/delete/{id}")
    public String supprimerClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/admin/clients";
    }
}