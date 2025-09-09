/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import app_model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author axlnx
 */
public class StockController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Carburant> stockTableView;
    @FXML
    private TableColumn<Carburant, String> produit;
    @FXML
    private TableColumn<Carburant, Double> prixUnite;
    @FXML
    private TableColumn<Carburant, Double> quantite;
    @FXML
    private TableColumn<Carburant, Double> seuil;
    @FXML
    private TableColumn<Carburant, Integer> idCarburant;
    
    private ObservableList <Carburant> listProduit;

    Stock stockCarburant = new Stock();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listProduit = FXCollections.observableArrayList();
        stockTableView.setItems(listProduit);

        idCarburant.setCellValueFactory(data -> data.getValue().identifiantProperty().asObject());
        produit.setCellValueFactory(data -> data.getValue().nomCarburantProperty());
        quantite.setCellValueFactory(data -> data.getValue().quantiteProperty().asObject());
        prixUnite.setCellValueFactory(data -> data.getValue().prixProperty().asObject());
        seuil.setCellValueFactory(data -> data.getValue().niveauAlerteProperty().asObject());

        for (Carburant c: stockCarburant.getStockNonSt().values()) {
            listProduit.add(c);
        }
        
        
    }    
    
}
