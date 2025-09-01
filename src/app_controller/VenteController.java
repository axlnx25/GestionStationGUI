/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.net.URL;
import java.util.ResourceBundle;

import app_model.Carburant;
import app_model.Vente;
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
    private TableColumn<Vente, String> dateVente;
    @FXML
    private TableColumn<Vente, Integer> idVente;
    @FXML
    private TextField remplirProduit;
    @FXML
    private TextField remplirDate;
    @FXML
    private TextField remplirQuantite;
    @FXML
    private Label erreurVente;

    ObservableList<Vente> listVente;
    VenteGestion venteGestion = new VenteGestion();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listVente = FXCollections.observableArrayList();
        crudVente.setItems(listVente);

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
            if (c.getNomCarburant().equalsIgnoreCase(remplirProduit.getText())) {
                if ((c.getQuantite() > 0.0) && (c.getQuantite() >= Double.parseDouble(remplirQuantite.getText()) )) {
                    Vente v = new Vente(remplirProduit.getText(), Double.parseDouble(remplirQuantite.getText()), remplirDate.getText());
                    c.setQuantite( c.getQuantite() - Double.parseDouble(remplirQuantite.getText()));
                    listVente.add(v);
                    venteGestion.getVentes().add(v);
                    erreurVente.setText("Vente Reussi");

                    remplirProduit.clear();
                    remplirQuantite.clear();
                    remplirDate.clear();

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
