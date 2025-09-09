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
    ApprovisionnementListe l = new ApprovisionnementListe();
    VenteGestion g = new VenteGestion();

    public int getTotalDesEntree() {
        return g.nombresDeVentes();
    }

    public int getTotalDesSortes() {
        return l.nombreApprovisionnement();
    }

    public double recap (double valeur) {
        return ((g.valeurDesVentes()) - valeur);
    }
}
