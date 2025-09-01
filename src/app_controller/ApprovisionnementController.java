/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.net.URL;
import java.util.ResourceBundle;

import app_model.Carburant;
import app_model.Fournisseur;
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
public class ApprovisionnementController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Approvisionnement> crudApprovisionnement;
    @FXML
    private TableColumn<Approvisionnement, String> libelleApprovisionnement;
    @FXML
    private TableColumn<Approvisionnement, String> nomFournisseur;
    @FXML
    private TableColumn<Approvisionnement, String> produitApprovisionne;
    @FXML
    private TableColumn<Approvisionnement, Double> quantiteApprovisionne;
    @FXML
    private TableColumn<Approvisionnement, String> dateApprovisionnement;
    @FXML
    private TableColumn<Approvisionnement, Integer> idFournisseur;
    @FXML
    private TextField remplirQuantite;
    @FXML
    private TextField remplirProduit;
    @FXML
    private TextField remplirNomFournisseur;
    @FXML
    private TextField remplirDate;
    @FXML
    private Label erreurApprovisionnement;
    @FXML
    private TextField Approvisionnement;

    ObservableList<Approvisionnement> listApprovisionnements;
    ApprovisionnementListe cellApprovisionnement = new ApprovisionnementListe();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listApprovisionnements = FXCollections.observableArrayList();
        crudApprovisionnement.setItems(listApprovisionnements);

        libelleApprovisionnement.setCellValueFactory(data -> data.getValue().libelleProperty());
        nomFournisseur.setCellValueFactory(data -> data.getValue().nomFournisseurProperty());
        produitApprovisionne.setCellValueFactory(data -> data.getValue().nomProduitProperty());
        quantiteApprovisionne.setCellValueFactory(data -> data.getValue().quantitApprovisionneProperty().asObject());
        dateApprovisionnement.setCellValueFactory(data -> data.getValue().dateProperty());
        idFournisseur.setCellValueFactory(data -> data.getValue().identifiantProperty().asObject());

        for (app_controller.Approvisionnement a : cellApprovisionnement.getListeApprovisionnements().values()) {
            listApprovisionnements.add(a);
        }
    }    

    @FXML
    private void ajouterApprovisionnement(ActionEvent event) {
        CrudFournisseur crud = new CrudFournisseur();
        Stock stock = new Stock();
        if ((crud.getHistoriqueFournisseur().isEmpty()) || (stock.getStockNonSt().isEmpty())) {
            erreurApprovisionnement.setText("Echec Ce Fournisseur ou Produit n'existe pas");
        } else {
            for (Fournisseur f : crud.getHistoriqueFournisseur().values()) {
                if (f.getNomFournisseur().equalsIgnoreCase(remplirNomFournisseur.getText())) {

                    for (Carburant c : stock.getStockNonSt().values()) {
                        if (c.getNomCarburant().equalsIgnoreCase(remplirProduit.getText())) {
                            app_controller.Approvisionnement a = new Approvisionnement(Approvisionnement.getText(), remplirNomFournisseur.getText(), remplirProduit.getText(), Double.parseDouble(remplirQuantite.getText()), remplirDate.getText());
                            listApprovisionnements.add(a);
                            c.setQuantite(Double.parseDouble(remplirQuantite.getText()) + c.getQuantite());
                            cellApprovisionnement.ajouterApprovisionnement(a);
                            erreurApprovisionnement.setText("Ajouter avec Succes");
                            break;
                        } erreurApprovisionnement.setText("Echec Ce Produit n'existe pas");
                    }

                    break;
                }
                erreurApprovisionnement.setText("Echec Ce Fournisseur n'existe pas");
            }
        }
    }

    @FXML
    private void supprimerApprovisionnement(ActionEvent event) {

    }
    
}
