package com.example.batiment.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void envoyerContratPdf(String destinataire, byte[] pdfBytes, String nomFichier) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(destinataire);
            helper.setSubject("Contrat PDF - N Guyomarch");
            helper.setText(
                    "Bonjour,\n\n" +
                            "Veuillez trouver en pièce jointe votre contrat au format PDF.\n\n" +
                            "Cordialement,\n" +
                            "N Guyomarch");

            helper.addAttachment(nomFichier, new ByteArrayResource(pdfBytes));

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email", e);
        }
    }
}