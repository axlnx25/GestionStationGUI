/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_controller;

import app_controller.ApprovisionnementListe;
import app_controller.VenteGestion;

/**
 *
 * @author axlnx
 */
public class Caisse {
    private static int totalDesEntree;
    private static int totalDesSortes;
    ApprovisionnementListe l = new ApprovisionnementListe();
    VenteGestion g = new VenteGestion();

    public int getTotalDesEntree() {
        return g.nombresDeVentes();
    }

    public int getTotalDesSortes() {
        return l.nombreApprovisionnement();
    }

//    public void afficherListeDesEntree() {
//        g.ventesValides();
//    }

//    public void afficherListeDesSortes () {
//        l.afficherListeApprovisionnement();
//    }

    public void recapitulatif (double valeur) {
        System.out.println("Entrées ( nombre ): " + totalDesEntree);
        System.out.println("Sorties ( nombre ): " + totalDesSortes);
        double resultat = (g.valeurDesVentes()) - valeur;
        if (resultat < 0) {
            System.out.println("Vous avez une perte de " + resultat);
        } else  {
            System.out.println("Vous avez un benefice de " + resultat);
        }
    }

    public double recap (double valeur) {
        return ((g.valeurDesVentes()) - valeur);
    }
}
