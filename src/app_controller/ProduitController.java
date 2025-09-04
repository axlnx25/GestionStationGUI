/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
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
import app_model.*;

/**
 * FXML Controller class
 *
 * @author axlnx
 */
public class ProduitController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Carburant> listeProduitCarburant;
    @FXML
    private TableColumn<Carburant, Integer> idCarburant;
    @FXML
    private TableColumn<Carburant, String> produit;
    @FXML
    private TableColumn<Carburant, Double> quantite;
    @FXML
    private TableColumn<Carburant, Double> prixUnite;
    @FXML
    private TableColumn<Carburant, Double> seuil;
    @FXML
    private TextField remplirNomProduit;
    @FXML
    private TextField remplirSeuil;
    @FXML
    private TextField remplirQuantite;
    @FXML
    private TextField remplirPrixUnitaire;
    @FXML
    private Label erreurCarburant;

    private ObservableList <Carburant> listProduit;

    Stock stockCarburant = new Stock();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listProduit = FXCollections.observableArrayList();
        listeProduitCarburant.setItems(listProduit);

        idCarburant.setCellValueFactory(data -> data.getValue().identifiantProperty().asObject());
        produit.setCellValueFactory(data -> data.getValue().nomCarburantProperty());
        quantite.setCellValueFactory(data -> data.getValue().quantiteProperty().asObject());
        prixUnite.setCellValueFactory(data -> data.getValue().prixProperty().asObject());
        seuil.setCellValueFactory(data -> data.getValue().niveauAlerteProperty().asObject());
        for (Carburant c: stockCarburant.getStockNonSt().values()) {
            listProduit.add(c);
        }
    }    

    @FXML
    private void ajouterProduit(ActionEvent event) throws IOException {
        if (!stockCarburant.estDansStock(remplirNomProduit.getText())) {
            boolean okNom = ValidationController.validerTexteObligatoire(remplirNomProduit, erreurCarburant, "Nom Invalide !");
            boolean okQuantite = ValidationController.validerNombre(remplirQuantite, erreurCarburant, "Quantite Invalide !");
            boolean okPU = ValidationController.validerNombre(remplirPrixUnitaire, erreurCarburant, "PrixUnitaire Invalide !");
            boolean okSeuil = ValidationController.validerNombre(remplirSeuil, erreurCarburant, "Seuil Invalide !");
            if (!(okNom && okQuantite && okPU && okSeuil)) {
                return;
            }


            Carburant carburant = new Carburant(remplirNomProduit.getText(), Double.parseDouble(remplirPrixUnitaire.getText()),Double.parseDouble(remplirQuantite.getText()), Double.parseDouble(remplirSeuil.getText()) );
            stockCarburant.ajouterAuStock(carburant);
            listProduit.add(carburant);
            remplirPrixUnitaire.clear();
            remplirQuantite.clear();
            remplirSeuil.clear();
            remplirNomProduit.clear();
            erreurCarburant.setText("Ajouter avec Succes ");
        } else {
              erreurCarburant.setText("Echec ");
        }
    }

    @FXML
    private void supprimerProduit(ActionEvent event) throws IOException {
        Carburant selection =  listeProduitCarburant.getSelectionModel().getSelectedItem();
        if (selection != null) {
            stockCarburant.supprimerDuStock(selection.getIdentifiant());
            listProduit.remove(selection);
            erreurCarburant.setText("Supprimer avec Succes ");
        } else erreurCarburant.setText("Echec !");

    }

    @FXML
    private void ModifierProduit(ActionEvent event) throws IOException {
        Carburant selection = listeProduitCarburant.getSelectionModel().getSelectedItem();
        if (selection != null) {
            boolean okNom = ValidationController.validerTexteObligatoire(remplirNomProduit, erreurCarburant, "Nom Invalide !");
            boolean okQuantite = ValidationController.validerNombre(remplirQuantite, erreurCarburant, "Quantite Invalide !");
            boolean okPU = ValidationController.validerNombre(remplirPrixUnitaire, erreurCarburant, "PrixUnitaire Invalide !");
            boolean okSeuil = ValidationController.validerNombre(remplirSeuil, erreurCarburant, "Seuil Invalide !");
            if (!(okNom && okQuantite && okPU && okSeuil)) {
                return;
            }

            selection.setNomCarburant(remplirNomProduit.getText());
            selection.setQuantite(Double.parseDouble(remplirQuantite.getText()));
            selection.setPrix(Double.parseDouble(remplirPrixUnitaire.getText()));
            selection.setNiveauAlerte(Double.parseDouble(remplirSeuil.getText()));
            listeProduitCarburant.refresh(); // Rafraîchir la vue
            erreurCarburant.setText("Modifier avec Succes ");
        } else erreurCarburant.setText("Echec !");
    }
    
}
