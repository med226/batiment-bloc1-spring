package com.example.batiment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String accueil() {
        return "login";
    }

    @GetMapping("/clients")
    public String clientsPage() {
        return "clients";
    }

    @GetMapping("/interventions")
    public String interventionsPage() {
        return "interventions";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "admin-dashboard";
    }
}