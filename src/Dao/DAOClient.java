/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author PC-Etudiante Info
 */
public class DAOClient {
    static Connection con = LaConnexion.seConnecter();
    
    public static int Ajouter(String code, String nom, String prenom, String adresse,String telephone){
    PreparedStatement ps;
    int status=0;
    String query="insert into client values(?,?,?,?,?);";
    if (code.isEmpty() || nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || telephone.isEmpty()) {
        showAlert(AlertType.ERROR, "Erreur", "Champs vides", "Veuillez remplir tous les champs.");
        System.out.println("Veuillez remplir tous les champs.");
        return status;
    }
    try {
        ps=con.prepareStatement(query);
        ps.setString(1, code);
        ps.setString(2, nom);
        ps.setString(3, prenom);
        ps.setString(4, adresse);
        ps.setString(5, telephone);
        status=ps.executeUpdate();
        if(status>=1){
            System.out.println("Ajout avec succes");
            showAlert(AlertType.INFORMATION, "Succès", "Ajout réussi", "Le client a été ajouté avec succès.");
        }
    } catch (SQLException e) {
        System.out.println("Ajout échoué");
    }
    return status;
}

public static int Modifier(String code, String nom, String prenom, String adresse,String telephone){
    PreparedStatement ps;
    int s=0;
    String query="update client set  nomCli=? , prenomCli=? , adresseCli=? , telephoneCli=? where codeCli=? ;";
    try {
        ps=con.prepareStatement(query);
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, adresse);
        ps.setString(4, telephone);
        ps.setString(5, code);
        s=ps.executeUpdate();
        if(s>=1){
            System.out.println("Modification avec succès");
            showAlert(AlertType.INFORMATION, "Succès", "Modification réussie", "Le client a été modifié avec succès.");
        }
    } catch (SQLException e) {
        System.out.println("Modification échouée");
    }
    return s;
}

    
    public static void Archiver(String code) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de l'archivage");
        alert.setContentText("Êtes-vous sûr de vouloir archiver ce client ?");

        ButtonType buttonTypeOui = new ButtonType("Oui");
        ButtonType buttonTypeNon = new ButtonType("Non");

        alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);

        alert.showAndWait().ifPresent(result -> {
            if (result == buttonTypeOui) {
                try {
                    PreparedStatement ps = con.prepareStatement("delete from client where codeCli = ?");
                    ps.setString(1, code);
                    ps.executeUpdate();
                    showAlert(AlertType.INFORMATION, "Succès", "Archivage réussi", "Le client a été archivé avec succès.");
                } catch (SQLException e) {
                    showAlert(AlertType.ERROR, "Erreur", "Erreur lors de l'archivage du client", "Une erreur s'est produite lors de l'archivage du client.");
                }
            }
        });
    }
    
    
    private static void showAlert(AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
}
