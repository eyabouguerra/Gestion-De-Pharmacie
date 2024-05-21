/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Classes.Medicament;
import Classes.Medicament.TypeMedicament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC-Etudiante Info
 */
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class DAOMedicament {
    private static Connection con = LaConnexion.seConnecter();
    
   public static int ajouterMed(String codeMed, String libelleMed, String categorieMed, float prixMed, int qteStock, TypeMedicament typeMed) {
        PreparedStatement ps;
        int s = 0;
        String query = "insert into medicament values(?,?,?,?,?,?);";
        
        if (codeMed.isEmpty() || libelleMed.isEmpty() || categorieMed.isEmpty() || typeMed == null) {
            afficherAlerte("Champs vides", "Veuillez remplir tous les champs.");
            return 0;
        }

        try {
            if (qteStock < 0) {
                afficherAlerte("Quantité invalide", "La quantité ne peut pas être négative.");
                return 0;
            }

            ps = con.prepareStatement(query);
            ps.setString(1, codeMed);
            ps.setString(2, libelleMed);
            ps.setString(3, categorieMed);
            ps.setFloat(4, prixMed);
            ps.setInt(5, qteStock);
            ps.setString(6, typeMed.name());

            s = ps.executeUpdate();
            if (s >= 1) {
                afficherInformation("Succès", "Médicament ajouté avec succès.");
                System.out.println("Ajout avec succès");
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                afficherErreur("Erreur", "Le code du médicament existe déjà.");
            } else {
                System.out.println(e.getMessage());
                afficherErreur("Erreur", "Une erreur est survenue lors de l'ajout du médicament.");
            }
        }
        return s;
    }


    
    public static int updateMed(String codeMed, String libelleMed, String categorieMed, float prixMed, int qteStock, TypeMedicament typeMed) {
        PreparedStatement ps;
        int s = 0;
        String query = " update medicament set libelleMed=? , categorieMed=? ,prixMed=? , qteStock=? , typeMed= ? where codeMed=? ;";
        try {
            if (qteStock < 0) {
                afficherAlerte("Quantité invalide", "La quantité ne peut pas être négative.");
                return 0;
            }
            ps = con.prepareStatement(query);
            ps.setString(1, libelleMed);
            ps.setString(2, categorieMed);
            ps.setFloat(3, prixMed);
            ps.setInt(4, qteStock);
            ps.setString(5, typeMed.name());
            ps.setString(6, codeMed);
            s = ps.executeUpdate();
            if (s >= 1) {
                afficherInformation("Succès", "Médicament mis à jour avec succès.");
                System.out.println("Modification avec succes");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            afficherErreur("Erreur", "Une erreur est survenue lors de la mise à jour du médicament.");
        }
        return s;
    }
    
    public static void archiver(String codeMed) {
    PreparedStatement ps;
    String query = "select libelleMed from medicament where codeMed = ?;";
    try {
        ps = con.prepareStatement(query);
        ps.setString(1, codeMed);
        ResultSet rs = ps.executeQuery();
        String libelleMed = "";
        if (rs.next()) {
            libelleMed = rs.getString("libelleMed");
        }

        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Voulez-vous vraiment archiver le médicament '" + libelleMed + "' ?");

        if (confirmation.showAndWait().get() == ButtonType.OK) {
            ps = con.prepareStatement("delete from medicament where codeMed = ?;");
            ps.setString(1, codeMed);
            ps.executeUpdate();
            afficherInformation("Succès", "Médicament archivé avec succès.");
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        afficherErreur("Erreur", "Une erreur est survenue lors de l'archivage du médicament.");
    }
}

    
    
    
     public static Medicament getMedicament(String codeMed){
        Medicament medicament=null;
        PreparedStatement ps;
        ResultSet rs;
        String query= "select * from medicament where codeMed=? ;";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, codeMed);
            rs=ps.executeQuery();
            while(rs.next()){
                String libelleMed = rs.getString("libelleMed");
                String categorieMed = rs.getString("categorieMed");
                float prixMed = rs.getFloat("prixMed");
                int qteStock = rs.getInt("qteStock");
                String typemed = rs.getString("typeMed");
                Medicament.TypeMedicament typeMed = Medicament.TypeMedicament.valueOf(typemed);
                

                medicament = new Medicament(codeMed, libelleMed, categorieMed, prixMed, qteStock, typeMed);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return medicament;
    }
    
    public static void updateStock(String codMed,int nvqte){
        PreparedStatement ps;
        String query = "update medicament set qteStock=? where codeMed=?";
        try {
            ps=con.prepareStatement(query);
            ps.setInt(1, nvqte);
            ps.setString(2, codMed);
            ps.executeUpdate();
            System.out.println("stock mis a jour avec succès ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    private static void afficherAlerte(String titre, String message) {
        Alert alerte = new Alert(AlertType.WARNING);
        alerte.setTitle("Alerte");
        alerte.setHeaderText(titre);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
    
    private static void afficherInformation(String titre, String message) {
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle("Information");
        info.setHeaderText(titre);
        info.setContentText(message);
        info.showAndWait();
    }
    
    private static void afficherErreur(String titre, String message) {
        Alert erreur = new Alert(AlertType.ERROR);
        erreur.setTitle("Erreur");
        erreur.setHeaderText(titre);
        erreur.setContentText(message);
        erreur.showAndWait();
    }
}
