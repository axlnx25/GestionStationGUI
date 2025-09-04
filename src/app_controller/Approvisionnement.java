/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_controller;

import app_model.Carburant;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author axlnx
 */
public class Approvisionnement {
    private static int compteur = 0;
    private IntegerProperty identifiant =  new SimpleIntegerProperty();
    private StringProperty libelle =  new SimpleStringProperty();
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private StringProperty nomFournisseur  = new SimpleStringProperty();
    private StringProperty nomProduit = new SimpleStringProperty();
    private DoubleProperty quantitApprovisionne =  new SimpleDoubleProperty();
    Stock stock = new Stock();

   public Approvisionnement(String libelle, String nom, String produit, Double quantite, LocalDate date) {
        this.identifiant.set(compteur++);
        this.libelle.set(libelle);
        this.nomFournisseur.set(nom);
        this.nomProduit.set(produit);
        this.quantitApprovisionne.set(quantite);
        this.date.set(date);
   }

    public int getIdentifiant() {
        return identifiant.get();
    }

    public double getQuantitApprovisionne() {
        return quantitApprovisionne.get();
    }

    public String getLibelle() {
        return libelle.get();
    }

    public LocalDate getDate() {
        return date.get();
    }

    public String getNomFournisseur() {
        return nomFournisseur.get();
    }

    public String getNomProduit() {
        return nomProduit.get();
    }

    public void setQuantitApprovisionne(double quantitApprovisionne) {
        this.quantitApprovisionne.set(quantitApprovisionne);
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant.set(identifiant);
    }

    public void setLibelle(String libelle) {
        this.libelle.set(libelle);
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur.set(nomFournisseur);
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit.set(nomProduit);
    }

    public IntegerProperty identifiantProperty() {
        return identifiant;
    }

    public StringProperty libelleProperty() {
        return libelle;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public StringProperty nomFournisseurProperty() {
        return nomFournisseur;
    }

    public StringProperty nomProduitProperty() {
        return nomProduit;
    }

    public DoubleProperty quantitApprovisionneProperty() {
        return quantitApprovisionne;
    }

    //    methode qui calcule la valeur prix de l'approvisionnement en cours et la retourne
//    pour incremanter la valeur prix du stock
    public double valeurApprovisionnementEncours () {
        return stock.getItemPrice(this.nomProduit.get()) * this.quantitApprovisionne.get();
    }

    public DoubleProperty valeurApprovisionnementEncoursProperty () {
       DoubleProperty price = new SimpleDoubleProperty(stock.getItemPrice(this.nomProduit.get()) * this.quantitApprovisionne.get());
        return price;
    }

    //    methode a appeler dans le menu apres nouvelle approvisionnement
    public void miseAJourStock() {
//        trouver l'id du produit par son nom pour modifier son stock (valeur quantite) dans stock general
        for (Carburant carburant : Stock.getStock().values()) {
            if (carburant.getNomCarburant().equalsIgnoreCase(this.nomProduit.get())) {
//                augmenter quantite apres approvisionnement
                carburant.setQuantite(carburant.getQuantite() + this.quantitApprovisionne.get());
                double quantite = stock.gettotalQuantiteStock() +
                        this.getQuantitApprovisionne();
                stock.setTotalQuantiteStock(quantite);

//                augmenter valeur prix apres approvisionnement
                double valeur = stock.gettotalValeurStock() +
                        this.valeurApprovisionnementEncours();
                stock.setTotalValeurStock(valeur);
            }
        }
    }

    //    methode appeler lors de la suppression d'un approvisionnement
    public void annulerApprovisionnement() {
        for (Carburant carburant : Stock.getStock().values()) {
            if (carburant.getNomCarburant().equalsIgnoreCase(this.nomProduit.get())) {
//                soustraire quantite apres annuler approvisionnement
                double quantite = stock.gettotalQuantiteStock() -
                        this.getQuantitApprovisionne();
                stock.setTotalQuantiteStock(quantite);
                carburant.setQuantite(carburant.getQuantite() - this.quantitApprovisionne.get());

//                soustraire valeur prix apres annuler approvisionnement
                double valeur = stock.gettotalValeurStock() -
                        this.valeurApprovisionnementEncours();
                stock.setTotalValeurStock(valeur);
            }
        }
    }

    public String toString () {
        return "Identifiant d'approvisionnement: " + this.identifiant.get() + "\nLibelle: " + this.libelle.get()
                + "\nDate: " + this.date.get() + "\nNom du fournisseur: " + this.nomFournisseur.get()
                + "\nNom du produit approvisionné: " + this.nomProduit.get()
                + "\nValeur (prix) approvisionnée: " + this.valeurApprovisionnementEncours() +
                "\nQuantite approvisionnée: " + this.quantitApprovisionne.get();
    }


}
