/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

/**
 * FXML Controller class
 *
 * @author axlnx
 */
public class MenuController implements Initializable {

    @FXML
    private AnchorPane contentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void crudVente(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/vente.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void retourVente(ActionEvent event) throws IOException {
        Stage stage;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/menu.fxml"));
        stage = (Stage) contentArea.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }

    @FXML
    private void historiqueVente(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/venteListe.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void listeEntres(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/entresCaisse.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void listeSorties(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/sortieCaisse.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void recapitulatif(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/recapitulatif.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void retourCaisse(ActionEvent event) throws IOException {
        Stage stage;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/menu.fxml"));
        stage = (Stage) contentArea.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }

    @FXML
    private void crudProduit(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/produit.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void retourCarburant(ActionEvent event) throws IOException {
        Stage stage;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/menu.fxml"));
        stage = (Stage) contentArea.getScene().getWindow();
        stage.setScene(new Scene(pane));

    }

    @FXML
    private void crudApprovisionnement(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/approvisionnement.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void stockProduit(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/stock.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void crudFournisseur(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/fournisseur.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void retourFournisseur(ActionEvent event) throws IOException {
        Stage stage;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/menu.fxml"));
        stage = (Stage) contentArea.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }

    @FXML
    private void listeFournisseur(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/listeFournisseur.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void crudUtilisateur(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/utilisateur.fxml"));
        contentArea.getChildren().setAll(pane);
    }

    @FXML
    private void retourUtilisateur(ActionEvent event) throws IOException {
        Stage stage;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/menu.fxml"));
        stage = (Stage) contentArea.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }

    @FXML
    private void deconnexion(ActionEvent event) throws IOException {
        Stage stage;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app_fxml/login.fxml"));
        stage = (Stage) contentArea.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }
    
}
