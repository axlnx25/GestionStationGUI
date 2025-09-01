/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.net.URL;
import java.util.ResourceBundle;

import app_model.Fournisseur;
import app_model.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TextField remplirRole;
    @FXML
    private TextField remplirPassword;
    @FXML
    private Label erreurUtilisateur;

    private ObservableList<Utilisateur> listUsers;

    GestionUtilisateurController g = new GestionUtilisateurController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listUsers = FXCollections.observableArrayList();
        listeCrudUtilisateur.setItems(listUsers);

        idUtilisateur.setCellValueFactory(data -> data.getValue().identifiantProperty().asObject());
        nomUtilisateur.setCellValueFactory(data -> data.getValue().usernameProperty());
        passwordUtilisateur.setCellValueFactory(data -> data.getValue().passwordProperty());
        roleUtilisateur.setCellValueFactory(data -> data.getValue().roleProperty());

        for (Utilisateur c: g.getUtilisateurs()) {
            listUsers.add(c);
        }
    }    

    @FXML
    private void ajouterUtilisateur(ActionEvent event) {
        Utilisateur u = new Utilisateur(remplirUsername.getText(), remplirPassword.getText(), remplirRole.getText());
        g.creerUtilisateur(u);
        listUsers.add(u);
        remplirUsername.clear();
        remplirPassword.clear();
        remplirRole.clear();
        erreurUtilisateur.setText("Ajouter avec Succes ");
    }

    @FXML
    private void supprimerUtilisateur(ActionEvent event) {
        Utilisateur u =  listeCrudUtilisateur.getSelectionModel().getSelectedItem();
        if (u != null) {
            g.supprimerUtilisateur(u);
            listUsers.remove(u);
            erreurUtilisateur.setText("Supprimer avec Succes ");
        } else erreurUtilisateur.setText("Echec ");
    }

    @FXML
    private void ModifierUtilisateur(ActionEvent event) {
        Utilisateur selectedUtilisateur = listeCrudUtilisateur.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur != null) {
            selectedUtilisateur.setUsername(remplirUsername.getText());
            selectedUtilisateur.setPassword(remplirPassword.getText());
            selectedUtilisateur.setRole(remplirRole.getText());
            erreurUtilisateur.setText("Modifier avec Succes ");
        } else erreurUtilisateur.setText("Echec ");
    }
    
}
