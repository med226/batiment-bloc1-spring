package com.example.batiment.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public void envoyerContratPdf(String destinataire, byte[] pdfBytes, String nomFichier) {

        // Si le service mail n'est pas configuré
        if (mailSender == null) {
            System.out.println("Service email non configuré.");
            System.out.println("Email non envoyé à : " + destinataire);
            return;
        }

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
            e.printStackTrace();
        }
    }
}
