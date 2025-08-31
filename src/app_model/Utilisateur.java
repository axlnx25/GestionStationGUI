package app_model;

public class Utilisateur extends Personne {
    private static int compteur = 0; // ID automatique
    private String role;
    private String username;
    private String password;

        public Utilisateur(String username, String password, String role) {
            this.identifiant = ++compteur;
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public int getId() {
            return identifiant;
        }
        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }
        public String getRole() {
            return role;
        }

        public void setUsername(String nom) {
            this.username = nom;
        }
        public void setPassword(String motDePasse) {
            this.password = motDePasse;
        }
        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "ID: " + identifiant +
                    " | Username: " + username +
                    " | Password: " + password +
                    " | Role: " + role;
        }
    }

