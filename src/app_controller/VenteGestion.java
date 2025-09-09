/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_controller;

import app_model.Carburant;
import app_model.Vente;
import javafx.util.converter.LocalDateStringConverter;

import javafx.scene.control.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author axlnx
 */
public class VenteGestion {
    private static ArrayList<Vente> ventes = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    Stock stock = new Stock();

    public ArrayList<Vente> getVentes() {
        return ventes;
    }

    // 2. Historique des ventes
    public void historique() {
        if (ventes.isEmpty()) {
            System.out.println(" Aucune vente enregistrée.");
        } else {
            System.out.println("Historique des ventes :");
            for (Vente v : ventes) {
                System.out.println(v);
            }
        }
    }

    public void ventesValides() {
        if (ventes.isEmpty()) {
            System.out.println(" Aucune vente enregistrée.");
        } else {
            System.out.println("Liste des entrées :");
            for (Vente v : ventes) {
                if (!v.isAnnule()) {
                    System.out.println(v);
                }
            }
        }
    }


    // 3. Annuler une vente
    public void annulerVente() {
        if (!ventes.isEmpty()) {
            historique();
            System.out.print("Entrez l'ID de la vente à annuler ( choisir un ID dans la liste ): ");
            int id = sc.nextInt();
            sc.nextLine();

            for (Vente v : ventes) {
                if (v.getId() == id) {
                    if (!v.isAnnule()) {
                        stock.annulerVendreProduit(v.getProduit(), v.getTotal(), v.getQuantite());
                        v.annuler();
                        System.out.println(" Vente #" + id + " annulée avec succès.");
                    } else {
                        System.out.println(" Cette vente est déjà annulée.");
                    }
                    return;
                }
            }
            System.out.println(" Vente introuvable !");
        } else {
            System.out.println("Pas de ventes ");
        }
    }

    public void retour() {
        System.out.println(" Retour au menu principal...");
    }

    public int nombresDeVentes() {
        int nbVentes = 0;
        for (Vente v : ventes) {
            if (!v.isAnnule()) {
                nbVentes++;
            }
        }
        return nbVentes;
    }

    public double valeurDesVentes() {
        double resultat = 0.0;
        for (Vente v : getVentes()) {
            if (!v.isAnnule()) {
                resultat = v.getTotal() + resultat;
            }
        }
        return resultat;
    }

    public void sauvegarderVentes (String fichier, Label champErr) {

        try (BufferedWriter ecrire = new BufferedWriter (new FileWriter(new File (fichier)))){
            for (Vente v : ventes) {
                ecrire.write(v.toString());
                ecrire.newLine();
            }
        } catch (IOException e) {
            champErr.setText(e.getMessage());
        }
    }

    public void chargerVentes (String fichier) {
        ventes.clear();
        try (BufferedReader lecture = new BufferedReader(new FileReader(new File(fichier)))) {
            String line;
            while ((line = lecture.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String produit = data[1];
                double quantite = Double.parseDouble(data[2]);
                double prixUnitaire = Double.parseDouble(data[3]);
                LocalDate date = LocalDate.parse(data[5]);
                Boolean estAnnule = Boolean.parseBoolean(data[6]);

                Vente v = new Vente(data[1], Double.parseDouble(data[2]), LocalDate.parse(data[5]));
                v.setProduit(produit);
                v.setQuantite(quantite);
                v.setPrixUnitaire(prixUnitaire);
                v.setEstAnnule(estAnnule);
                v.setDate(date);
                
                ventes.add(v);
            }
        } catch (IOException e) {
            System.out.println("Erreur de lecture !");
        }
    }

    public ArrayList<Vente> chargerVentesIntermediaire (String fichier) {
        ventes.clear();
        try (BufferedReader lecture = new BufferedReader(new FileReader(new File(fichier)))) {
            String line;
            while ((line = lecture.readLine()) != null) {
                String[] data = line.split(",");
                ventes.add(new Vente(data[1], Double.parseDouble(data[2]), LocalDate.parse(data[5]))) ;
            }
            return ventes;
        } catch (IOException e) {
            return ventes;
        }
    }

}
