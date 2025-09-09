/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author axlnx
 */
public class SortieCaisseController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Approvisionnement> sortieCaisse;
    @FXML
    private TableColumn<Approvisionnement, String> nomFournisseur;
    @FXML
    private TableColumn<Approvisionnement, String> nomProduit;
    @FXML
    private TableColumn<Approvisionnement, Double> quantiteApprovisionne;
    @FXML
    private TableColumn<Approvisionnement, LocalDate> dateApprovisionnement;
    @FXML
    private TableColumn<Approvisionnement, Double> prixApprovisionnement;

    ApprovisionnementListe listVente = new ApprovisionnementListe();
    ObservableList<Approvisionnement> listVentesObservable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listVentesObservable = FXCollections.observableArrayList();
        sortieCaisse.setItems(listVentesObservable);

        nomFournisseur.setCellValueFactory(data -> data.getValue().nomFournisseurProperty());
        nomProduit.setCellValueFactory(data -> data.getValue().nomProduitProperty());
        quantiteApprovisionne.setCellValueFactory(data -> data.getValue().quantitApprovisionneProperty().asObject());
        dateApprovisionnement.setCellValueFactory(data -> data.getValue().dateProperty());
        prixApprovisionnement.setCellValueFactory(data -> data.getValue().valeurApprovisionnementEncoursProperty().asObject());

        listVentesObservable.addAll(listVente.getListeApprovisionnements().values());
    }    
    
}
