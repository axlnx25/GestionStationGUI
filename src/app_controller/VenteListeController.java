/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import app_controller.VenteGestion;
import app_model.Vente;
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
public class VenteListeController implements Initializable {

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
    private TableColumn<Vente, LocalDate> dateVente;
    @FXML
    private TableColumn<Vente, String> etatVente;
    
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
        etatVente.setCellValueFactory(data -> data.getValue().venteEtat());

        listVente.addAll(venteGestion.getVentes());
    }    
    
}
