/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app_model.Carburant;
import app_model.Vente;
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
public class VenteController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private TableView<Vente> crudVente;
    @FXML
    private TableColumn<Vente, String> produitVente;
    @FXML
    private TableColumn<Vente, Double> quantiteVente;
    @FXML
    private TableColumn<Vente, Double> prixUniteVente;
    @FXML
    private TableColumn<Vente, LocalDate> dateVente;
    @FXML
    private TableColumn<Vente, Integer> idVente;
    @FXML
    private ComboBox<String> myComboBoxProduit;
    @FXML
    private DatePicker myDate;
    @FXML
    private TextField remplirQuantite;
    @FXML
    private Label erreurVente;

    ObservableList<Vente> listVente;
    VenteGestion venteGestion = new VenteGestion();
    String fichier = "app_fichier/VenteFichier.txt";
    String fichierProduit ="app_fichier/Stock.txt";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listVente = FXCollections.observableArrayList();
        crudVente.setItems(listVente);

        ObservableList<String> observableList = FXCollections.observableArrayList();

        for (Carburant c: Stock.getStock().values()) {
            observableList.add(c.getNomCarburant());
        }
        myComboBoxProduit.setItems(observableList);

        produitVente.setCellValueFactory(data -> data.getValue().produitProperty());
        quantiteVente.setCellValueFactory(data -> data.getValue().quantiteProperty().asObject());
        prixUniteVente.setCellValueFactory(data -> data.getValue().prixUnitaireProperty().asObject());
        dateVente.setCellValueFactory(data -> data.getValue().dateProperty());
        idVente.setCellValueFactory(data -> data.getValue().idProperty().asObject());


        for (Vente v : venteGestion.getVentes()) {
            if (!v.isAnnule()) {
                listVente.add(v);
            }
        }
    }    

    @FXML
    private void vendre(ActionEvent event) {
        Stock stock = new Stock();
        for (Carburant c: stock.getStockNonSt().values()) {
            if (c.getNomCarburant().equalsIgnoreCase(myComboBoxProduit.getValue())) {
                if ((c.getQuantite() > 0.0) && (c.getQuantite() >= Double.parseDouble(remplirQuantite.getText()) )) {
                    boolean okquantite = ValidationController.validerNombre(remplirQuantite, erreurVente, "Quantite Invalide");
                    boolean okdate = ValidationController.validerDateObligatoire(myDate, erreurVente, "Date Invalide");
                    if (!(okquantite && okdate)) {
                        return;
                    }

                    Vente v = new Vente(myComboBoxProduit.getValue(), Double.parseDouble(remplirQuantite.getText()), myDate.getValue());
                    c.setQuantite( c.getQuantite() - Double.parseDouble(remplirQuantite.getText()));
                    listVente.add(v);
                    venteGestion.getVentes().add(v);
                    erreurVente.setText("Vente Reussi");
                    venteGestion.sauvegarderVentes(fichier, erreurVente);
                    Stock miseAJour = new Stock();
                    miseAJour.sauvegarderStock(fichierProduit);

                    myComboBoxProduit.setValue(null);
                    remplirQuantite.clear();
                    myDate.setValue(null);

                    break;
                }
            }
            erreurVente.setText("Echec ");
        }
    }

    @FXML
    private void annulerVente(ActionEvent event) {
        Stock stock = new Stock();
        Vente selectedVente = crudVente.getSelectionModel().getSelectedItem();
        selectedVente.setEstAnnule(true);
        venteGestion.sauvegarderVentes(fichier, erreurVente);
        for (Carburant c: stock.getStockNonSt().values()) {
            if (c.getNomCarburant().equalsIgnoreCase(selectedVente.getProduit())) {
                c.setQuantite(selectedVente.getQuantite() + c.getQuantite());
                erreurVente.setText("Annulé avec Succes ");
                listVente.remove(selectedVente);
                break;
            }
            erreurVente.setText("Echec ");
        }

    }
    
}
