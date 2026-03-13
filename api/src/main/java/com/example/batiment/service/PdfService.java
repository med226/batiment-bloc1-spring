package com.example.batiment.service;

import com.example.batiment.model.Contrat;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] genererContratPdf(Contrat contrat) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("CONTRAT D'INTERVENTION"));
            document.add(new Paragraph("Entreprise : N Guyomarch"));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Numéro : " + safe(contrat.getNumero())));
            document.add(new Paragraph(
                    "Date : " + (contrat.getDateContrat() != null ? contrat.getDateContrat().toString() : "")));
            document.add(new Paragraph("Statut : " + safe(contrat.getStatut())));
            document.add(new Paragraph(" "));

            if (contrat.getClient() != null) {
                document.add(new Paragraph("CLIENT"));
                document.add(new Paragraph("Nom : " + safe(contrat.getClient().getNom())));
                document.add(new Paragraph("Email : " + safe(contrat.getEmailClient())));
                document.add(new Paragraph(" "));
            }

            if (contrat.getChantier() != null) {
                document.add(new Paragraph("CHANTIER"));
                document.add(new Paragraph("Libellé : " + safe(contrat.getChantier().getLibelle())));
                document.add(new Paragraph("Adresse : " + safe(contrat.getChantier().getAdresse())));
                document.add(new Paragraph("Ville : " + safe(contrat.getChantier().getVille())));
                document.add(new Paragraph(" "));
            }

            document.add(new Paragraph("Le présent document confirme l'accord d'intervention."));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Signature client : __________________________"));
            document.add(new Paragraph("Signature entreprise : ______________________"));

            document.close();
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF", e);
        }
    }

    private String safe(String valeur) {
        return valeur == null ? "" : valeur;
    }
}