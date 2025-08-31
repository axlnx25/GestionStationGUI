/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package app_main;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.scene.*;
import app_controller.*;
import app_model.*;
import java.util.Scanner;

/**
 *
 * @author axlnx
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/app_fxml/login.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("GESTION STATION");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestionUtilisateurController defaultUser = new GestionUtilisateurController();
        defaultUser.creerUtilisateur("admin", "12345", "admin");
        launch(args);
    }
    
}
