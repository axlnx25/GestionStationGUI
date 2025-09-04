package app_controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;

public class ValidationController {

    // Vérifie que le champ texte n’est pas vide
    public static boolean validerTexteObligatoire(TextField champ, Label messageErreur, String msg) {
        String valeur = champ.getText().trim();
        if (valeur.isEmpty()) {
            afficherErreur(champ, messageErreur, msg);
            return false;
        }
        clearErreur(champ, messageErreur);
        return true;
    }

    // Vérifie que le champ est un nombre (double)
    public static boolean validerNombre(TextField champ, Label messageErreur, String msg) {
        try {
            double val = Double.parseDouble(champ.getText().trim());
            if (val < 0) {
                afficherErreur(champ, messageErreur, msg);
                return false;
            }
            clearErreur(champ, messageErreur);
            return true;
        } catch (NumberFormatException e) {
            afficherErreur(champ, messageErreur, msg);
            return false;
        }
    }

    // Vérifie que le champ est un nombre positif
    public static boolean validerNombrePositif(TextField champ, Label messageErreur, String msg) {
        try {
            double val = Double.parseDouble(champ.getText().trim());
            if (val <= 0) {
                afficherErreur(champ, messageErreur, msg);
                return false;
            }
            clearErreur(champ, messageErreur);
            return true;
        } catch (NumberFormatException e) {
            afficherErreur(champ, messageErreur, msg);
            return false;
        }
    }

    // Marque le champ invalide
    private static void afficherErreur(TextField champ, Label messageErreur, String msg) {
        champ.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        if (messageErreur != null) messageErreur.setText(msg);
    }

    // Réinitialise le style
    private static void clearErreur(TextField champ, Label messageErreur) {
        champ.setStyle("");
        if (messageErreur != null) messageErreur.setText("");
    }

    public static boolean validerDateObligatoire(DatePicker picker, Label messageErreur, String msg) {
        LocalDate date = picker.getValue();
        if (date == null) {
            afficherErreurDate(picker, messageErreur, msg);
            return false;
        }
        clearErreurDate(picker, messageErreur);
        return true;
    }

    private static void afficherErreurDate(DatePicker picker, Label messageErreur, String msg) {
        picker.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        if (messageErreur != null) messageErreur.setText(msg);
    }

    private static void clearErreurDate(DatePicker picker, Label messageErreur) {
        picker.setStyle("");
        if (messageErreur != null) messageErreur.setText("");
    }

}
