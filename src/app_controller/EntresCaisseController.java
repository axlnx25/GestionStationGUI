/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.net.URL;
import java.util.ResourceBundle;

import app_model.Vente;
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
public class EntresCaisseController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Vente> historiqueVente;
    @FXML
    private TableColumn<Vente, String> nomProduit;
    @FXML
    private TableColumn<Vente, Double> quantiteVente;
    @FXML
    private TableColumn<Vente, Double> prixVente;
    @FXML
    private TableColumn<Vente, String> dateVente;
    @FXML
    private TableColumn<Vente, Boolean> etatVente;

    ObservableList<Vente> listVente;
    VenteGestion venteGestion = new VenteGestion();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listVente = FXCollections.observableArrayList();
        historiqueVente.setItems(listVente);

        nomProduit.setCellValueFactory(data -> data.getValue().produitProperty());
        quantiteVente.setCellValueFactory(data -> data.getValue().quantiteProperty().asObject());
        prixVente.setCellValueFactory(data -> data.getValue().prixProperty().asObject());
        dateVente.setCellValueFactory(data -> data.getValue().dateProperty());
        etatVente.setCellValueFactory(data -> data.getValue().estAnnuleProperty().asObject());

        listVente.addAll(venteGestion.getVentes());
    }    
    
}
