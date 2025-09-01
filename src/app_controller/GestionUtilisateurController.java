package app_controller;

import app_model.Utilisateur;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;


public class GestionUtilisateurController {
    private static ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // 1. Créer utilisateur
    public void creerUtilisateur() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Role: ");
        String role = sc.nextLine();

        Utilisateur u = new Utilisateur(username, password, role);
        utilisateurs.add(u);
        System.out.println(" Utilisateur créé : " + u);
    }

    public void creerUtilisateur(String username, String password, String role) {
        Utilisateur u = new Utilisateur(username, password, role);
        utilisateurs.add(u);
    }

    public void creerUtilisateur(Utilisateur u) {
        utilisateurs.add(u);
    }

    // 2. Supprimer utilisateur
    public void supprimerUtilisateur() {
        System.out.print("Entrez l'ID de l'utilisateur à supprimer: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Utilisateur u : utilisateurs) {
            if (u.getId() == id) {
                utilisateurs.remove(u);
                System.out.println("Utilisateur supprimé !");
                return;
            }
        }
        System.out.println(" Utilisateur introuvable !");
    }

    public void supprimerUtilisateur(Utilisateur u) {
        utilisateurs.remove(u);
    }

    // 3. Modifier utilisateur
    public void modifierUtilisateur() {
        System.out.print("Entrez l'ID de l'utilisateur à modifier: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Utilisateur u : utilisateurs) {
            if (u.getId() == id) {
                System.out.print("Nouveau username (" + u.getUsername() + "): ");
                String nom = sc.nextLine();
                if (!nom.isEmpty()) u.setUsername(nom);

                System.out.print("Nouveau password (" + u.getPassword() + "): ");
                String prenom = sc.nextLine();
                if (!prenom.isEmpty()) u.setPassword(prenom);

                System.out.print("Nouveau rôle (" + u.getRole() + "): ");
                String role = sc.nextLine();
                if (!role.isEmpty()) u.setRole(role);

                System.out.println("Utilisateur modifié : " + u);
                return;
            }
        }
        System.out.println(" Utilisateur introuvable !");
    }

    // 4. Liste utilisateurs
    public void listeUtilisateurs() {
        if (utilisateurs.isEmpty()) {
            System.out.println(" Aucun utilisateur enregistré.");
        } else {
            System.out.println(" Liste des utilisateurs :");
            for (Utilisateur u : utilisateurs) {
                System.out.println(u);
            }
        }
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

        // 5. Retour
        public void retour() {
            System.out.println("Retour au menu principal...");
        }
    }


