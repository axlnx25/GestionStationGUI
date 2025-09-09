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

    public void afficherListeApprovisionnement() {
        if (ListeApprovisionnements.isEmpty()) {
            System.out.println("Liste d'approvisionnement vide !!!!");
        } else {
            System.out.println("Historique des approvisionnements: ");
            for (Approvisionnement approvisionnement: ListeApprovisionnements.values()) {
                System.out.println(approvisionnement);
                System.out.println("----------------------------------------------");
            }
        }
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

    public void annulerApprovisionnement() {
        if (ListeApprovisionnements.isEmpty()) {
            System.out.println("Liste d'approvisionnement vide, pas " +
                    "d'approvisionnement à annuler!!!!");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Historique des approvisionnements: ");
            for (Approvisionnement approvisionnement: ListeApprovisionnements.values()) {
                System.out.println(approvisionnement);
                System.out.println("----------------------------------------------");
            }
            System.out.print("Entrez identifiant de l'approvisionnement que vous souhaitez supprimer: ");
            int idDeSuppression = sc.nextInt();
            sc.nextLine();
            int i = 0;
            for (Approvisionnement approvisionnement: ListeApprovisionnements.values()) {
                if (idDeSuppression == approvisionnement.getIdentifiant()) {
//                    pour enlever du stock l'approvisionnement qui vient d'etre annuler
                    Approvisionnement a = ListeApprovisionnements.get(approvisionnement.getIdentifiant());
                    a.annulerApprovisionnement();
                    ListeApprovisionnements.remove(approvisionnement.getIdentifiant());
                    i++;
                    break;
                }
            }
            if (i == 0) {
                System.out.println("Cet approvisionnement que vous souhaitez supprimer " +
                        "n'est pas dans la liste des approvisionnement ou une vente a déjà été \n réalisée avec ce stock " +
                        "approvisionné !" +
                        "\nRecommencez l'operation");
            } else System.out.println("Approvisionnement annuler avec succès !!!!");
        }
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
