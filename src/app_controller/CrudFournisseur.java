/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_controller;

import app_model.Fournisseur;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author axlnx
 */
public class CrudFournisseur {
    private static Map<Integer, Fournisseur> historiqueFournisseur =  new HashMap<>();

    public Map<Integer, Fournisseur> getHistoriqueFournisseur() {
        return historiqueFournisseur;
    }

    public void ajouterAListeFournisseur (Fournisseur f) {
        historiqueFournisseur.put(f.getIdentifiantFournisseur(), f);
    }

    public void supprimerFournisseur (int id) {
        if (historiqueFournisseur.isEmpty()) {
            System.out.println("Liste fournisseur vide ");
        } else {
            if (historiqueFournisseur.containsKey(id)) {
                historiqueFournisseur.remove(id);
                System.out.println("Supprimé avec succès ");
            } else  {
                System.out.println("Ce fournisseur n'existe pas ");
            }
        }
    }

    public void sauvegarderFournisseur (String fichier) {
        try (BufferedWriter ecriture = new BufferedWriter(new FileWriter(new File(fichier)))) {
            for (Fournisseur f : historiqueFournisseur.values()) {
                ecriture.write(f.toString());
                ecriture.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void chargerFournisseur (String fichier) {
        try (BufferedReader lecture = new BufferedReader(new FileReader(new File(fichier)))) {
            String line;
            while ((line = lecture.readLine()) != null) {
                String[] data = line.split(",");
                int id =  Integer.parseInt(data[0]);
                String nomFournisseur = data[1];
                String prenomFournisseur = data[2];
                String adresseFournisseur = data[3];
                String telephoneFournisseur = data[4];

                Fournisseur f = new Fournisseur(nomFournisseur, prenomFournisseur, adresseFournisseur, telephoneFournisseur);
                f.setIdentifiantFournisseur(id);
                historiqueFournisseur.put(id, f);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
