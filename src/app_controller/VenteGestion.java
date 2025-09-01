/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_controller;

import app_model.Carburant;
import app_model.Vente;

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

    // 1. Nouvelle vente
//    public void nouvelleVente() {
//        if (stock.getStockNonSt().isEmpty()) {
//            System.out.println("Le stock vide pas veuillez enregistrez un produit ");
//        } else {
//            stock.afficherProduitPourVente();
//            String produit;
//
//            while (true) {
//                System.out.print("Nom du produit ( choisir dans la liste ): ");
//                produit = sc.nextLine();
//                if (stock.estDansStock(produit)) {
//                    break;
//                }
//            }
//            System.out.print("Quantité: ");
//            double qte = sc.nextDouble();
//            sc.nextLine();
//
////            verifier que la vente est possible par la quantite dans le stock
//            if (stock.ventePossible(produit, qte)) {
//                System.out.println("Prix unitaire: " + stock.getItemPrice(produit));
//                double prix = stock.getItemPrice(produit);
////                vendre
//                Vente v = new Vente(produit, qte, prix);
//                ventes.add(v);
//
////                ajuster le stock finacierement et en quantité après la vente
//                stock.vendreProduit(produit, v.getTotal(), qte);
////                dimunier le stock du dit produit
//                for (Carburant c : stock.getStockNonSt().values()) {
//                    if (c.getNomCarburant().equalsIgnoreCase(produit)) {
//                        c.setQuantite(c.getQuantite() - qte);
//                    }
//                }
//
//                System.out.println("Vente enregistrée avec succès : " + v);
//            } else {
//                System.out.println("Vente impossible ! ( stock insuffisant )");
//            }
//        }
//
//    }

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
        double resultat = 0;
        for (Vente v : ventes) {
            if (!v.isAnnule()) {
                resultat += v.getTotal();
            }
        }
        return resultat;
    }
}
