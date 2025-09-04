/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import app_model.Carburant;
import app_model.Fournisseur;
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
    private TableColumn<Approvisionnement, LocalDate> dateApprovisionnement;
    @FXML
    private TableColumn<Approvisionnement, Integer> idFournisseur;
    @FXML
    private TextField remplirQuantite;
    @FXML
    private ComboBox<String> comboProduit;
    @FXML
    private ComboBox<String> comboFournisseur;
    @FXML
    private DatePicker dateAppro;
    @FXML
    private Label erreurApprovisionnement;
    @FXML
    private TextField Approvisionnement;

    ObservableList<Approvisionnement> listApprovisionnements;
    ApprovisionnementListe cellApprovisionnement = new ApprovisionnementListe();
    CrudFournisseur fournisseur = new CrudFournisseur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listApprovisionnements = FXCollections.observableArrayList();
        crudApprovisionnement.setItems(listApprovisionnements);

        ObservableList<String> observableListFrn = FXCollections.observableArrayList();
        ObservableList<String> observableListProd = FXCollections.observableArrayList();


        for(Fournisseur f : fournisseur.getHistoriqueFournisseur().values()) {
            observableListFrn.add(f.getPrenomFournisseur());
        }
        comboFournisseur.setItems(observableListFrn);

        for (Carburant c: Stock.stock.values()) {
            observableListProd.add(c.getNomCarburant());
        }
        comboProduit.setItems(observableListProd);


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
                if (f.getPrenomFournisseur().equalsIgnoreCase(comboFournisseur.getValue())) {

                    for (Carburant c : stock.getStockNonSt().values()) {
                        if (c.getNomCarburant().equalsIgnoreCase(comboProduit.getValue())) {

                            boolean oklibelle = ValidationController.validerTexteObligatoire(Approvisionnement, erreurApprovisionnement, "libelle obligatoire !");
                            boolean okQuantite = ValidationController.validerNombre(remplirQuantite, erreurApprovisionnement, "Quantite invalide!");
                            boolean okDate = ValidationController.validerDateObligatoire(dateAppro, erreurApprovisionnement, "Date invalide!");
                            if (!(oklibelle && okQuantite && okDate)) {
                                return; //quitter si probleme avec les entres utilisateurs
                            }


                            app_controller.Approvisionnement a = new Approvisionnement(Approvisionnement.getText(), comboFournisseur.getValue(), comboProduit.getValue(), Double.parseDouble(remplirQuantite.getText()), dateAppro.getValue());
                            listApprovisionnements.add(a);
                            c.setQuantite(Double.parseDouble(remplirQuantite.getText()) + c.getQuantite());
                            cellApprovisionnement.ajouterApprovisionnement(a);
                            erreurApprovisionnement.setText("Ajouter avec Succes");

                            comboProduit.setValue(null);
                            remplirQuantite.clear();
                            comboFournisseur.setValue(null);
                            dateAppro.setValue(null);
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
        VenteGestion venteGestion = new VenteGestion();
        Stock stock = new Stock();
        ApprovisionnementListe l = new ApprovisionnementListe();
        app_controller.Approvisionnement selection = crudApprovisionnement.getSelectionModel().getSelectedItem();
        if (venteGestion.getVentes().isEmpty()) {
            for (Carburant c: stock.getStockNonSt().values()) {
                if (c.getNomCarburant().equalsIgnoreCase(selection.getNomProduit())) {
                    c.setQuantite(c.getQuantite() - selection.getQuantitApprovisionne());
                    for (app_controller.Approvisionnement a : l.getListeApprovisionnements().values()) {
                        if (a.getNomProduit().equalsIgnoreCase(selection.getNomProduit())) {
                            l.getListeApprovisionnements().remove(a.getIdentifiant());
                        }
                    }
                    listApprovisionnements.remove(selection);
                    erreurApprovisionnement.setText("Supprimer avec Succes");
                    break;
                }
            }
        } else erreurApprovisionnement.setText("Impossible car il y'a deja une vente !");
    }
    
}
