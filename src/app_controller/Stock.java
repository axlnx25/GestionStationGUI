package app_controller;

import app_model.Carburant;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Stock {
    public static HashMap<Integer, Carburant> stock =  new HashMap<>();
    private static double totalValeurStock = 0.0;
    private static double totalQuantiteStock = 0.0;

    public static HashMap<Integer, Carburant> getStock() {
        return stock;
    }

    public HashMap<Integer, Carburant> getStockNonSt () {
        return stock;
    }

    private Integer creerCle (Carburant carburant) {
        return carburant.getIdentifiant();

    }

    //    notation camel case a prendre en compte en bas
    public double gettotalValeurStock ()  {
        return totalValeurStock;
    }

    public double gettotalQuantiteStock () {
        return totalQuantiteStock;
    }

    public void setTotalValeurStock(double totalValeurStock) {
        Stock.totalValeurStock = totalValeurStock;
    }

    public void setTotalQuantiteStock(double totalQuantiteStock) {
        Stock.totalQuantiteStock = totalQuantiteStock;
    }

    public void ajouterAuStock(Carburant carburant) {
        stock.put(carburant.getIdentifiant(), carburant);
    }

    public void supprimerDuStock(int idASupprimer) {
        if (stock.containsKey(idASupprimer)) {
            Carburant carburant = stock.get(idASupprimer);

//            ajustement du stock
            totalQuantiteStock = totalQuantiteStock - carburant.getQuantite();
            totalValeurStock = totalQuantiteStock - carburant.getPrix();

            stock.remove(carburant.getIdentifiant());
        } else  {
            System.out.println("Ce produit n'existe pas !!!!");
        }
    }

    //    obtenir prixUnitaire d'un produit du stock
    public double getItemPrice (String itemName) {
        for (Carburant carburant : stock.values()) {
            if (carburant.getNomCarburant().equalsIgnoreCase(itemName)) {
                return carburant.getPrix();
            }
        }
        return 0.0;
    }

    public boolean estDansStock (String itemName) {
        int i = 0;
        for (Carburant carburant : stock.values()) {
            if (carburant.getNomCarburant().equalsIgnoreCase(itemName)) {
                i++;
            }
        }
        if (i == 0) {
            return false;
        } else  {
            return true;
        }
    }

    public void sauvegarderStock (String fichier) {
        try (BufferedWriter ecriture = new BufferedWriter(new FileWriter(new File(fichier)))) {
            for (Carburant carburant : stock.values()) {
                ecriture.write(carburant.toString());
                ecriture.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void chargerStock (String fichier) {
        try (BufferedReader lecture = new BufferedReader(new FileReader(new File(fichier)))) {
            String line;
            while ((line = lecture.readLine()) != null) {
                String[] data = line.split(",");
                int id =  Integer.parseInt(data[0]);
                String nomCarburant = data[1];
                double prix = Double.parseDouble(data[2]);
                double seuil = Double.parseDouble(data[3]);
                double quantite = Double.parseDouble(data[4]);

                Carburant carburant = new Carburant(nomCarburant, prix, quantite, seuil);
                stock.put(id, carburant);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
