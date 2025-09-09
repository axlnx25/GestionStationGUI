package app_model;

import app_controller.Stock;
import javafx.beans.property.*;

public class Carburant {
    private static int compteur = 0;
    private IntegerProperty identifiant = new SimpleIntegerProperty();
    private StringProperty nomCarburant = new SimpleStringProperty();
    private DoubleProperty prix = new SimpleDoubleProperty();
    private DoubleProperty quantite = new SimpleDoubleProperty();
    private DoubleProperty niveauAlerte = new SimpleDoubleProperty();

    public Carburant(String nom, double prix, double quantite, double niveauAlerte) {
        this.identifiant.set(compteur++);
        this.nomCarburant.set(nom);
        this.prix.set(prix);
        this.quantite.set(quantite);
        this.niveauAlerte.set(niveauAlerte);
    }

    public int getIdentifiant() {
        return identifiant.get();
    }

    public IntegerProperty identifiantProperty() { return identifiant; }

    public void setIdentifiant(int identifiant) {
        this.identifiant.set(identifiant);
    }

    public String getNomCarburant() {
        return nomCarburant.get();
    }

    public void setNomCarburant(String nomCarburant) {
        this.nomCarburant.set(nomCarburant);
    }

    public StringProperty nomCarburantProperty() { return nomCarburant; }

    public double getPrix() {
        return prix.get();
    }

    public void setPrix(double prix) {
        this.prix.set(prix);
    }

    public DoubleProperty prixProperty() { return prix; }

    public double getQuantite() {
        return quantite.get();
    }

    public void setQuantite(double quantite) {
        this.quantite.set(quantite);
    }

    public DoubleProperty quantiteProperty() { return quantite; }

    public double getNiveauAlerte() {
        return niveauAlerte.get();
    }

    public void setNiveauAlerte(double niveauAlerte) {
        this.niveauAlerte.set(niveauAlerte);
    }
    
    public DoubleProperty niveauAlerteProperty() { return niveauAlerte; }

    @Override
    public String toString() {
        return "Identifiant: " + this.identifiant.get() +
                "\nNomCarburant: " + this.nomCarburant.get() + "\nPrix: " +
                this.prix.get() + "\nNiveau d'alerte: " + this.niveauAlerte.get() + "\nQuantite: " + this.quantite.get() ;
    }


}
