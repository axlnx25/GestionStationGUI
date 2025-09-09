/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_controller;

import app_model.Fournisseur;

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

    public void modifierFournisseur (int id) {
        if (historiqueFournisseur.isEmpty()) {
            System.out.println("Liste fournisseur vide ");
        } else {
            if (historiqueFournisseur.containsKey(id)) {
                Scanner sc = new Scanner(System.in);
                Fournisseur f = historiqueFournisseur.get(id);
//                afficher les anciennes données avant de modifier
                System.out.println("-----------------------------------");
                System.out.println("Données actuelles de " + f.getNomFournisseur());
                System.out.println(f);
                System.out.println("-----------------------------------");

                System.out.print("Modifier nom fournisseur : ");
                f.setNomFournisseur(sc.next());
                sc.nextLine();
                System.out.print("Modifier prenom fournisseur : ");
                f.setPrenomFournisseur(sc.next());
                System.out.println("Modifier adresse fournisseur : ");
                f.setAdresseFournisseur(sc.next());
                System.out.println("Modifier telephone fournisseur : ");
                f.setTelephoneFournisseur(sc.next());

            } else  {
                System.out.println("Ce fournisseur n'existe pas ");
            }
        }
    }

    public static void afficherTousLesFournisseurs () {
        if (historiqueFournisseur.isEmpty()) {
            System.out.println("Liste fournisseur vide ");
        } else {
            System.out.println("Liste fournisseurs ");
            for (Fournisseur f : historiqueFournisseur.values()) {
                System.out.println("----------------------------------------");
                System.out.println(f);
                System.out.println("----------------------------------------");
            }
        }
    }

    public static boolean estDansLaListeFournisseur (String nomFournisseur) {
        int i = 0;
        for (Fournisseur f : historiqueFournisseur.values()) {
            if (f.getNomFournisseur().equalsIgnoreCase(nomFournisseur)) {
                i++;
            }
        }
        if (i == 0) {
            return false;
        } else  {
            return true;
        }
    }
}
