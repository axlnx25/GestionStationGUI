/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app_controller;

import app_model.Utilisateur;

/**
 *
 * @author axlnx
 */
public class AuthentificationController {
    private GestionUtilisateurController listeUsers = new GestionUtilisateurController();

    public boolean login(String username, String password) {
        for (Utilisateur u : listeUsers.getUtilisateurs()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                System.out.println("CONNEXION REUSSIE");
                return true;
            }
        }
        System.out.println("Username ou Password incorrect");
        return false;
    }

    public void logout() {
        System.out.println("DECONNEXION REUSSIE");
    }
}
