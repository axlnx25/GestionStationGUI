/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_controller;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author axlnx
 */
public class ApprovisionnementListe {
    private static Map<Integer, Approvisionnement> ListeApprovisionnements = new HashMap<>();

    public void ajouterApprovisionnement(Approvisionnement approvisionnement) {
        ListeApprovisionnements.put(approvisionnement.getIdentifiant(), approvisionnement);
        System.out.println("Ajouté avec succes ");
    }

    public Map<Integer, Approvisionnement> getListeApprovisionnements() {
        return ListeApprovisionnements;
    }

    public double valeurHistoriqueApprovisionnement() {
        double resultat = 0;
        for (Approvisionnement approvisionnement: ListeApprovisionnements.values()) {
            resultat = resultat + approvisionnement.valeurApprovisionnementEncours();
        }
        return resultat;
    }

    public int nombreApprovisionnement() {
        return ListeApprovisionnements.size();
    }

    public void sauvegarderApprovisionnement (String fichier) {
        try (BufferedWriter ecriture = new BufferedWriter(new FileWriter(new File(fichier)))) {
            for (Approvisionnement approvisionnement: ListeApprovisionnements.values()) {
                ecriture.write(approvisionnement.toString());
                ecriture.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void chargerApprovisionnement (String fichier) {
        try (BufferedReader lecture = new BufferedReader(new FileReader(new File(fichier)))) {
            Stock stock = new Stock();
            String line;
            while ((line = lecture.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String libelle = data[1];
                LocalDate date = LocalDate.parse(data[2]);
                String prenomFournisseur = data[3];
                String nomProduit =  data[4];
                double valeur = Double.parseDouble(data[5]);
                double quantite = Double.parseDouble(data[6]);

                Approvisionnement approvisionnement = new Approvisionnement(libelle, prenomFournisseur,nomProduit, quantite, date);
                approvisionnement.setIdentifiant(id);
                ListeApprovisionnements.put(id, approvisionnement);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
