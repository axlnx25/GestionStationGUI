/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package app_controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author axlnx
 */
public class RecapitulatifController implements Initializable {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private Label erreurCaisse;
    @FXML
    private Label entreCaisse;
    @FXML
    private Label sortieCaisse;

    Caisse c = new Caisse();
    ApprovisionnementListe liste = new ApprovisionnementListe();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        entreCaisse.setText(String.valueOf(c.getTotalDesEntree()));
        sortieCaisse.setText(String.valueOf(c.getTotalDesSortes()));

        if (c.recap(liste.valeurHistoriqueApprovisionnement()) >= 0.0) {
            erreurCaisse.setText("Vous avez un benefice de " + c.recap(liste.valeurHistoriqueApprovisionnement()));
        } else erreurCaisse.setText("Vous avez une perte de " + c.recap(liste.valeurHistoriqueApprovisionnement()));
    }    
    
}
