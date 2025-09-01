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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;      
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.fxml.*;
import javafx.scene.*;

/**
 * FXML Controller class
 *
 * @author axlnx
 */
public class LoginController implements Initializable {

    @FXML
    private Button myLoginButton;
    @FXML
    private TextField myUsername;
    @FXML
    private TextField myPassword;
    @FXML
    private Label myLabel;
    @FXML
    private Button quitter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connexion (ActionEvent event) throws IOException {
        AuthentificationController auth = new AuthentificationController();
        if (auth.login(myUsername.getText(), myPassword.getText())) {
            myLabel.setText("CONNEXION REUSSIE");
          Stage stage;
          Parent root = FXMLLoader.load(getClass().getResource("/app_fxml/menu.fxml"));
          stage = (Stage) myLoginButton.getScene().getWindow();
          stage.setScene(new Scene(root));
            
        } else  {
            myLabel.setText("INCORRECT USERNAME OU PASSWORD");
        }
    }
    
    @FXML
    private void quitter (ActionEvent event) throws IOException {
        System.exit(0);
    }
    
}
