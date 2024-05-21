/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.DAOMedicament;
import Classes.Medicament;

import Dao.LaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author PC-Etudiante Info
 */
public class FXMLMedicamentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Medicament> tv = new TableView<>();

    @FXML
    private TableColumn<Medicament, String> colCodMed;

    @FXML
    private TableColumn<Medicament, String> colLibelle;

    @FXML
    private TableColumn<Medicament, String> colCategorie;

    @FXML
    private TableColumn<Medicament, Float> colPrix;

    @FXML
    private TableColumn<Medicament, Integer> colQte;
    
 

    @FXML
    private TableColumn<Medicament, Medicament.TypeMedicament> colType;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnArchiver;

    @FXML
    private Button btnModifier;
    
    @FXML
    private Button btnMed;
    
    @FXML
    private Button btnClients;
    
    @FXML
    private Button btnAcceuil;
    
    @FXML
    private Button btnHistorique;

    @FXML
    private TextField txtCodeMed;

    @FXML
    private TextField txtLibelle;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtCat;

    @FXML
    private TextField txtPrix;

    @FXML
    private TextField txtQte;
    
     

    
    static Connection con= LaConnexion.seConnecter();
    ObservableList<Medicament> observableList;
    
    
    @FXML
    void Acceuil(ActionEvent event) throws IOException {
         Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLHome.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Home Page");
            stage.show(); 

    }

    @FXML
    void gérerClients(ActionEvent event) throws IOException {
         Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLClient.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Register Page");
            stage.show();

    }

    @FXML
    void gérerMedicament(ActionEvent event) throws IOException {
         Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLMedicament.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Register Page");
            stage.show();

    }
    
    @FXML
    void Historique(ActionEvent event) throws IOException {
        Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLPatientMedicament.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Register Page");
            stage.show();

    }
    
    
    
    @FXML
    void ajouter(ActionEvent event) {
        String codeMed = txtCodeMed.getText();
        String libelle = txtLibelle.getText();
        String categorie = txtCat.getText();
        float prix = Float.parseFloat(txtPrix.getText());
        int qteStock = Integer.parseInt(txtQte.getText());
        Medicament.TypeMedicament type= Medicament.TypeMedicament.valueOf(txtType.getText().toUpperCase());
        
        DAOMedicament.ajouterMed(codeMed, libelle, categorie, prix, qteStock, type);
        lister();
    }

    @FXML
    void archiver(ActionEvent event) {
        DAOMedicament.archiver(txtCodeMed.getText());
        lister();
    }

    @FXML
    void modifier(ActionEvent event) {
        String codeMed = txtCodeMed.getText();
        String libelle = txtLibelle.getText();
        String categorie = txtCat.getText();
        float prix = Float.parseFloat(txtPrix.getText());
        int qteStock = Integer.parseInt(txtQte.getText());
        Medicament.TypeMedicament type= Medicament.TypeMedicament.valueOf(txtType.getText().toUpperCase());
        
        DAOMedicament.updateMed(codeMed, libelle, categorie, prix, qteStock, type);
        lister();
    }
    public void lister(){
    observableList.clear();
    try{
        ResultSet rs = con.createStatement().executeQuery("select * from medicament");
        while(rs.next()){
            String codeMed = rs.getString("codeMed");
            String libelleMed = rs.getString("libelleMed");
            String categorieMed = rs.getString("categorieMed");
            float prixMed = rs.getFloat("prixMed");
            int qteStock = rs.getInt("qteStock");
            String typeMedStr = rs.getString("typeMed");
            
            Medicament.TypeMedicament typeMed = Medicament.TypeMedicament.valueOf(typeMedStr);
            observableList.add(new Medicament(codeMed, libelleMed, categorieMed, prixMed, qteStock, typeMed));
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    tv.setItems(observableList);
}

    
    private void remiseAzéro() {
        txtCodeMed.clear();
        txtLibelle.clear();
        txtCat.clear();
        txtPrix.clear();
        txtQte.clear();
        txtType.clear();
    }
    
    
    
@Override
public void initialize(URL url, ResourceBundle rb) {
    // Initialiser la liste observable
    observableList = FXCollections.observableArrayList();

    
    
    colCodMed.setCellValueFactory(new PropertyValueFactory<>("codeMed"));
    colLibelle.setCellValueFactory(new PropertyValueFactory<>("libelleMed"));
    colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorieMed"));
    colPrix.setCellValueFactory(new PropertyValueFactory<>("prixMed"));
    colQte.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
    colType.setCellValueFactory(new PropertyValueFactory<>("typeMed"));

    // Ajouter un gestionnaire d'événements pour sélectionner un médicament dans le TableView
    tv.setOnMouseClicked(event -> {
        Medicament selectedMedicament = tv.getSelectionModel().getSelectedItem();
        if (selectedMedicament != null) {
            // Remplir les champs de texte avec les informations du médicament sélectionné
            txtCodeMed.setText(selectedMedicament.getCodeMed());
            txtLibelle.setText(selectedMedicament.getLibelleMed());
            txtCat.setText(selectedMedicament.getCategorieMed());
            txtPrix.setText(String.valueOf(selectedMedicament.getPrixMed()));
            txtQte.setText(String.valueOf(selectedMedicament.getQteStock()));
            txtType.setText(selectedMedicament.getTypeMed().toString());
        }
    });

    // Appeler la méthode lister pour afficher les médicaments dans le TableView
    lister();
}
  
    
}
