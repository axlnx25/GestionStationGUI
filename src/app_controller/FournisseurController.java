/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import app_model.Carburant;
import app_model.Fournisseur;
import java.net.URL;
import java.util.ResourceBundle;

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
public class FournisseurController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Fournisseur> listeCrudFournisseur;
    @FXML
    private TableColumn<Fournisseur, String> nomFournisseur;
    @FXML
    private TableColumn<Fournisseur, String> prenomFournisseur;
    @FXML
    private TableColumn<Fournisseur, String> adresseFournisseur;
    @FXML
    private TableColumn<Fournisseur, String> telephoneFournisseur;
    @FXML
    private TableColumn<Fournisseur, Integer> idFournisseur;
    @FXML
    private TextField remplirNomFournisseur;
    @FXML
    private TextField remplirTelephone;
    @FXML
    private TextField remplirAdresse;
    @FXML
    private TextField remplirPrenomFournisseur;
    @FXML
    private Label erreurFournisseur;

    private ObservableList<Fournisseur> listFournisseur;

    CrudFournisseur f = new CrudFournisseur();
    String fichierFournisseur = "app_fichier/Fournisseur.txt";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listFournisseur = FXCollections.observableArrayList();
        listeCrudFournisseur.setItems(listFournisseur);

        idFournisseur.setCellValueFactory(data -> data.getValue().identiantProperty().asObject());
        nomFournisseur.setCellValueFactory(data -> data.getValue().nomFournisseurProperty());
        prenomFournisseur.setCellValueFactory(data -> data.getValue().prenomFournisseurProperty());
        adresseFournisseur.setCellValueFactory(data -> data.getValue().adresseFournisseurProperty());
        telephoneFournisseur.setCellValueFactory(data -> data.getValue().telephoneFournisseurProperty());

        for (Fournisseur c: f.getHistoriqueFournisseur().values()) {
            listFournisseur.add(c);
        }

        listeCrudFournisseur.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                remplirAdresse.setText(newValue.getAdresseFournisseur());
                remplirTelephone.setText(newValue.getTelephoneFournisseur());
                remplirPrenomFournisseur.setText(newValue.getPrenomFournisseur());
                remplirNomFournisseur.setText(newValue.getNomFournisseur());
            }
        });
    }    

    @FXML
    private void ajouterFournisseur(ActionEvent event) {
        boolean okNom = ValidationController.validerTexteObligatoire(remplirNomFournisseur, erreurFournisseur, "Nom Invalide !");
        boolean okPrenom = ValidationController.validerTexteObligatoire(remplirPrenomFournisseur, erreurFournisseur, "Prenom Invalide !");
        boolean okAdresse = ValidationController.validerTexteObligatoire(remplirAdresse, erreurFournisseur, "Adresse Invalide !");
        boolean okTelephone = ValidationController.validerTexteObligatoire(remplirTelephone, erreurFournisseur, "Telephone Invalide !");
         if (!(okNom && okPrenom && okAdresse && okTelephone)) {
             return;
         }


        Fournisseur fournisseur = new Fournisseur(remplirNomFournisseur.getText(), remplirPrenomFournisseur.getText(), remplirAdresse.getText(), remplirTelephone.getText());
        f.ajouterAListeFournisseur(fournisseur);
        listFournisseur.add(fournisseur);
        f.sauvegarderFournisseur(fichierFournisseur);

        remplirNomFournisseur.clear();
        remplirPrenomFournisseur.clear();
        remplirAdresse.clear();
        remplirTelephone.clear();
        erreurFournisseur.setText("Ajouter avec Succes");
    }

    @FXML
    private void supprimerFournisseur(ActionEvent event) {
        Fournisseur selection =  listeCrudFournisseur.getSelectionModel().getSelectedItem();
        if (selection != null) {
            f.supprimerFournisseur(selection.getIdentifiantFournisseur());
            f.sauvegarderFournisseur(fichierFournisseur);
            listFournisseur.remove(selection);

            remplirAdresse.clear();
            remplirTelephone.clear();
            remplirPrenomFournisseur.clear();
            remplirNomFournisseur.clear();

            erreurFournisseur.setText("Supprimer avec Succes");
        } else erreurFournisseur.setText("Echec ");
    }

    @FXML
    private void ModifierFournisseur(ActionEvent event) {
        Fournisseur selection =  listeCrudFournisseur.getSelectionModel().getSelectedItem();
        if (selection != null) {
            boolean okNom = ValidationController.validerTexteObligatoire(remplirNomFournisseur, erreurFournisseur, "Nom Invalide !");
            boolean okPrenom = ValidationController.validerTexteObligatoire(remplirPrenomFournisseur, erreurFournisseur, "Prenom Invalide !");
            boolean okAdresse = ValidationController.validerTexteObligatoire(remplirAdresse, erreurFournisseur, "Adresse Invalide !");
            boolean okTelephone = ValidationController.validerTexteObligatoire(remplirTelephone, erreurFournisseur, "Telephone Invalide !");
            if (!(okNom && okPrenom && okAdresse && okTelephone)) {
                return;
            }

            selection.setNomFournisseur(remplirNomFournisseur.getText());
            selection.setPrenomFournisseur(remplirPrenomFournisseur.getText());
            selection.setAdresseFournisseur(remplirAdresse.getText());
            selection.setTelephoneFournisseur(remplirTelephone.getText());
            f.sauvegarderFournisseur(fichierFournisseur);

            remplirAdresse.clear();
            remplirTelephone.clear();
            remplirPrenomFournisseur.clear();
            remplirNomFournisseur.clear();

            erreurFournisseur.setText("Modifier avec Succes");
        } else erreurFournisseur.setText("Echec ");
    }
    
}
