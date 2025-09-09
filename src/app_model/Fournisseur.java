/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_model;

import javafx.beans.property.*;

import java.util.Scanner;

/**
 *
 * @author axlnx
 */
public class Fournisseur {
    private static int compteur = 0;
    private IntegerProperty identifiant = new SimpleIntegerProperty();
    private StringProperty nomFournisseur = new SimpleStringProperty();
    private StringProperty prenomFournisseur = new SimpleStringProperty();
    private StringProperty adresseFournisseur = new SimpleStringProperty();
    private StringProperty telephoneFournisseur = new SimpleStringProperty();

    public Fournisseur(String nom, String prenom, String adresse, String telephone) {
        this.identifiant.set(compteur++);
        this.nomFournisseur.set(nom);
        this.prenomFournisseur.set(prenom);
        this.adresseFournisseur.set(adresse);
        this.telephoneFournisseur.set(telephone);
    }

    public int getIdentifiantFournisseur () {
        return this.identifiant.get();
    }

    public String getNomFournisseur () {
        return this.nomFournisseur.get();
    }

    public String getPrenomFournisseur () {
        return this.prenomFournisseur.get();
    }

    public String getAdresseFournisseur () {
        return this.adresseFournisseur.get();
    }

    public String getTelephoneFournisseur () {
        return this.telephoneFournisseur.get();
    }

    public void setIdentifiantFournisseur (int identifiant) {
        this.identifiant.set(identifiant);
    }

    public void setNomFournisseur (String nom) {
        this.nomFournisseur.set(nom);
    }

    public void setPrenomFournisseur (String prenom) {
        this.prenomFournisseur.set(prenom);
    }

    public void setAdresseFournisseur (String adresse) {
        this.adresseFournisseur.set(adresse);
    }

    public void setTelephoneFournisseur (String telephone) {
        this.telephoneFournisseur.set(telephone);
    }

    public IntegerProperty identiantProperty () {
        return this.identifiant;
    }

    public StringProperty nomFournisseurProperty () {
        return this.nomFournisseur;
    }

    public StringProperty prenomFournisseurProperty () {
        return this.prenomFournisseur;
    }

    public StringProperty adresseFournisseurProperty () {
        return this.adresseFournisseur;
    }

    public StringProperty telephoneFournisseurProperty () {
        return this.telephoneFournisseur;
    }

    public String toString() {
        return "Identifiant Fournisseur: " + this.identifiant.get() +
                "\nNom Fournisseur: " + this.nomFournisseur.get() + "\nPreom Fournisseur: " +
                this.prenomFournisseur.get() + "\nAdresse Fournisseur: " + this.adresseFournisseur.get() + "\nTelephone Fournisseur"
                + this.telephoneFournisseur.get() ;
    }


}
