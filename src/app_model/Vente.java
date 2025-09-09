/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_model;

import app_controller.Stock;
import javafx.beans.property.*;

import java.time.LocalDate;

/**
 *
 * @author axlnx
 */
public class Vente {
    private static int compteur = 0;  // Pour générer des IDs automatiques
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty produit = new SimpleStringProperty();
    private DoubleProperty quantite = new SimpleDoubleProperty();
    private DoubleProperty prixUnitaire = new SimpleDoubleProperty();
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private BooleanProperty estAnnule = new SimpleBooleanProperty();

    public Vente(String produit, double quantite, LocalDate date) {
        Stock stock = new Stock();

        this.id.set(++compteur);
        this.produit.set(produit);
        this.quantite.set(quantite);
        for (Carburant c : stock.getStockNonSt().values()) {
            if (c.getNomCarburant().equalsIgnoreCase(produit)) {
                this.prixUnitaire.set(c.getPrix());
            }
        }
        this.date.set(date);
        this.estAnnule.set(false);
    }

    public int getId() {
        return id.get();
    }
    public double getTotal() {
        return (quantite.get() * prixUnitaire.get());
    }
    public double getPrix() {
        return prixUnitaire.get();
    }
    public String getProduit() {
        return produit.get();
    }
    public double getQuantite() {
        return quantite.get();
    }
    public boolean isAnnule() {
        return estAnnule.get();
    }
    public void annuler() {
        this.estAnnule.set(true);
    }

    public void setProduit(String produit) {
        this.produit.set(produit);
    }
    public void setQuantite(double quantite) {
        this.quantite.set(quantite);
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire.set(prixUnitaire);
    }
    public void setDate(LocalDate date) {
        this.date.set(date);
    }
    public void setEstAnnule(boolean estAnnule) {
        this.estAnnule.set(estAnnule);
    }

    public StringProperty produitProperty() {
        return produit;
    }
    public DoubleProperty quantiteProperty() {
        return quantite;
    }
    public DoubleProperty prixUnitaireProperty() {
        return prixUnitaire;
    }
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
    public BooleanProperty estAnnuleProperty() {
        return estAnnule;
    }
    public IntegerProperty idProperty() {
        return id;
    }
    public DoubleProperty prixProperty() {
        DoubleProperty resultat = new SimpleDoubleProperty(quantite.get() * prixUnitaire.get());
        return resultat;
    }

    public StringProperty venteEtat () {
        if (estAnnule.get()) {
            StringProperty result = new SimpleStringProperty("Annulé");
            return result;
        } else  {
            StringProperty result = new SimpleStringProperty("Validé");
            return result;
        }
    }

    @Override
    public String toString() {
        return       id.get() +
                "," + produit.get() +
                "," + quantite.get() +
                "," + prixUnitaire.get() +
                "," + getTotal() +
                "," + date.get() +
                "," + estAnnule.get() ;
    }

}
