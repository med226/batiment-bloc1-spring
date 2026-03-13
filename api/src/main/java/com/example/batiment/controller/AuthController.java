package com.example.batiment.controller;

import com.example.batiment.entity.Utilisateur;
import com.example.batiment.repository.UtilisateurRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/login")
    public String afficherLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String traiterLogin(@RequestParam String email,
            @RequestParam String motDePasse,
            HttpSession session,
            Model model) {

        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(email);

        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();

            if (utilisateur.getMotDePasse().equals(motDePasse)
                    && "ADMIN".equalsIgnoreCase(utilisateur.getRole())) {

                session.setAttribute("utilisateurConnecte", utilisateur);
                return "redirect:/admin/dashboard";
            }
        }

        model.addAttribute("erreur", "Email ou mot de passe incorrect");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}