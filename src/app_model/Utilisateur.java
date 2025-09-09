package app_model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilisateur {
    private static int compteur = 0; // ID automatique
    private IntegerProperty identifiant = new SimpleIntegerProperty();
    private StringProperty role = new SimpleStringProperty();
    private StringProperty username =  new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

        public Utilisateur(String username, String password, String role) {
            this.identifiant.set(++compteur);
            this.username.set(username);
            this.password.set(password);
            this.role.set(role);
        }

        public int getId() {
            return identifiant.get();
        }
        public String getUsername() {
            return username.get();
        }
        public String getPassword() {
            return password.get();
        }
        public String getRole() {
            return role.get();
        }

        public void setUsername(String nom) {
            this.username.set(nom);
        }
        public void setPassword(String motDePasse) {
            this.password.set(motDePasse);
        }
        public void setRole(String role) {
            this.role.set(role);
        }

        public IntegerProperty identifiantProperty() {
            return identifiant;
        }

        public StringProperty usernameProperty() {
            return username;
        }

        public StringProperty passwordProperty() {
            return password;
        }

        public StringProperty roleProperty() {
            return role;
        }

        @Override
        public String toString() {
            return "ID: " + identifiant.get() +
                    " | Username: " + username.get() +
                    " | Password: " + password.get() +
                    " | Role: " + role.get();
        }
    }

