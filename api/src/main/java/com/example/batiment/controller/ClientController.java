package com.example.batiment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @GetMapping("/api/clients")
    public String getClients() {
        return "API Clients fonctionne";
    }
}