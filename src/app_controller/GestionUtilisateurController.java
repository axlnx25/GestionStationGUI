package app_controller;

import app_model.Utilisateur;
import javafx.scene.control.Label;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;


public class GestionUtilisateurController {
    private static ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void creerUtilisateur(Utilisateur u) {
        utilisateurs.add(u);
    }

    public void supprimerUtilisateur(Utilisateur u) {
        utilisateurs.remove(u);
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void sauvegarderUtilisateur (String fichier, Label erreur) {
        try (BufferedWriter ecriture = new BufferedWriter(new FileWriter(new File(fichier)))) {
            for (Utilisateur u : utilisateurs) {
                ecriture.write(u.toString());
                ecriture.newLine();
            }
        } catch (IOException e) {
            erreur.setText(e.getMessage());
        }
    }

    public void chargerUtilisateur(String fichier) {
        utilisateurs.clear();
        try (BufferedReader lecture = new BufferedReader(new FileReader(new File(fichier)))) {
            String line;
            while ((line = lecture.readLine()) != null) {
                String[] data = line.split(",");
                int id =  Integer.parseInt(data[0]);
                String username = data[1];
                String password = data[2];
                String role = data[3];

                Utilisateur u = new Utilisateur(username, password, role);
                utilisateurs.add(u);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


