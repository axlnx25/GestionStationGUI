/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app_model.Fournisseur;
import app_model.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author axlnx
 */
public class UtilisateurController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Utilisateur> listeCrudUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> nomUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> passwordUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> roleUtilisateur;
    @FXML
    private TableColumn<Utilisateur, Integer> idUtilisateur;
    @FXML
    private TextField remplirUsername;
    @FXML
    private ComboBox<String> comboRole;
    @FXML
    private TextField remplirPassword;
    @FXML
    private Label erreurUtilisateur;

    private ObservableList<Utilisateur> listUsers;
    String fichierUtilisateur = "app_fichier/Utilisateur.txt";

    GestionUtilisateurController g = new GestionUtilisateurController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listUsers = FXCollections.observableArrayList();
        listeCrudUtilisateur.setItems(listUsers);

        ObservableList<String> rolesExistants = FXCollections.observableArrayList(
                "admin", "secretaire", "archiviste", "vendeur", "comptable"
        );
        comboRole.setItems(rolesExistants);

        idUtilisateur.setCellValueFactory(data -> data.getValue().identifiantProperty().asObject());
        nomUtilisateur.setCellValueFactory(data -> data.getValue().usernameProperty());
        passwordUtilisateur.setCellValueFactory(data -> data.getValue().passwordProperty());
        roleUtilisateur.setCellValueFactory(data -> data.getValue().roleProperty());

        for (Utilisateur c: g.getUtilisateurs()) {
            listUsers.add(c);
        }
        listeCrudUtilisateur.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                remplirUsername.setText(newValue.usernameProperty().get());
                remplirPassword.setText(newValue.passwordProperty().get());
                comboRole.setValue(newValue.roleProperty().get());
            }
        });
    }    

    @FXML
    private void ajouterUtilisateur(ActionEvent event) {
        boolean okUsername = ValidationController.validerTexteObligatoire(remplirUsername, erreurUtilisateur, "Username invalide");
        boolean okPassword = ValidationController.validerTexteObligatoire(remplirPassword, erreurUtilisateur, "Password invalide");
        if (!(okUsername && okPassword)) {
            return;
        }
        if (comboRole.getValue() == null) {
            erreurUtilisateur.setText("Selectionner un role");
            return;
        }


        Utilisateur u = new Utilisateur(remplirUsername.getText(), remplirPassword.getText(), comboRole.getValue());
        g.creerUtilisateur(u);
        listUsers.add(u);
        g.sauvegarderUtilisateur(fichierUtilisateur, erreurUtilisateur);
        remplirUsername.clear();
        remplirPassword.clear();
        comboRole.setValue(null);
        erreurUtilisateur.setText("Ajouter avec Succes ");
    }

    @FXML
    private void supprimerUtilisateur(ActionEvent event) {
        Utilisateur u =  listeCrudUtilisateur.getSelectionModel().getSelectedItem();
        if (u != null) {
            g.supprimerUtilisateur(u);
            g.sauvegarderUtilisateur(fichierUtilisateur, erreurUtilisateur);
            listUsers.remove(u);

            remplirUsername.clear();
            remplirPassword.clear();
            comboRole.setValue(null);

            erreurUtilisateur.setText("Supprimer avec Succes ");
        } else erreurUtilisateur.setText("Echec ");
    }

    @FXML
    private void ModifierUtilisateur(ActionEvent event) {
        Utilisateur selectedUtilisateur = listeCrudUtilisateur.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur != null) {
            boolean okUsername = ValidationController.validerTexteObligatoire(remplirUsername, erreurUtilisateur, "Username invalide");
            boolean okPassword = ValidationController.validerTexteObligatoire(remplirPassword, erreurUtilisateur, "Password invalide");
            if (!(okUsername && okPassword)) {
                return;
            }
            if (comboRole.getValue() == null) {
                erreurUtilisateur.setText("Selectionner un role");
                return;
            }

            selectedUtilisateur.setUsername(remplirUsername.getText());
            selectedUtilisateur.setPassword(remplirPassword.getText());
            selectedUtilisateur.setRole(comboRole.getValue());
            g.sauvegarderUtilisateur(fichierUtilisateur, erreurUtilisateur);

            remplirUsername.clear();
            remplirPassword.clear();
            comboRole.setValue(null);

            erreurUtilisateur.setText("Modifier avec Succes ");
        } else erreurUtilisateur.setText("Echec ");
    }
    
}
