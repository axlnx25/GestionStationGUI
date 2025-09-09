/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import app_model.Fournisseur;
import java.net.URL;
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
public class ListeFournisseurController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Fournisseur> historiqueFournisseur;
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

    private ObservableList<Fournisseur> listFournisseur;

    CrudFournisseur f = new CrudFournisseur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listFournisseur = FXCollections.observableArrayList();
        historiqueFournisseur.setItems(listFournisseur);

        idFournisseur.setCellValueFactory(data -> data.getValue().identiantProperty().asObject());
        nomFournisseur.setCellValueFactory(data -> data.getValue().nomFournisseurProperty());
        prenomFournisseur.setCellValueFactory(data -> data.getValue().prenomFournisseurProperty());
        adresseFournisseur.setCellValueFactory(data -> data.getValue().adresseFournisseurProperty());
        telephoneFournisseur.setCellValueFactory(data -> data.getValue().telephoneFournisseurProperty());

        for (Fournisseur c: f.getHistoriqueFournisseur().values()) {
            listFournisseur.add(c);
        }
    }    
    
}
