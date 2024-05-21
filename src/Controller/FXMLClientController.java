package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Classes.Client;

import Dao.DAOClient;
import Dao.LaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author PC-Etudiante Info
 */
public class FXMLClientController implements Initializable {
    
    @FXML
    private TableView<Client> tv =new TableView<>();

    @FXML
    private TableColumn<Client, String> colCode;

    @FXML
    private TableColumn<Client, String> colNom;

    @FXML
    private TableColumn<Client, String> colPrenom;

    @FXML
    private TableColumn<Client, String> colAdresse;

    @FXML
    private TableColumn<Client, String> colTelephone;

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
    private TextField txtCode;
    
    

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtAdresse;

    @FXML
    private TextField txtTelephone;
    
    
    
    
    static Connection con= LaConnexion.seConnecter();
    ObservableList<Client> observableList;
    
    
    
   
     
   
    
    
    
   
    
    

    @FXML
    void ajouter(ActionEvent event) {
       DAOClient.Ajouter(txtCode.getText(), txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtTelephone.getText());
       lister();
      remiseAzéro();
    }

 @FXML
void archiver(ActionEvent event) {
    DAOClient.Archiver(txtCode.getText());
    lister();
    remiseAzéro();
}


    @FXML
    void modifier(ActionEvent event) {
        DAOClient.Modifier(txtCode.getText(), txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), txtTelephone.getText());
        lister();
        remiseAzéro();
    }
    
    private void remiseAzéro() {
        txtCode.clear();
        txtCode.requestFocus();
        txtNom.clear();
        txtPrenom.clear();
        txtAdresse.clear();
        txtTelephone.clear();

    }
    public void lister(){
        
        observableList.clear();
        try{
            ResultSet rs=con.createStatement().executeQuery("select * from client");
            while(rs.next()){
                observableList.add(new Client(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tv.setItems(observableList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        observableList = FXCollections.observableArrayList();
        

        colCode.setCellValueFactory(new PropertyValueFactory<>("codeCli"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomCli"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenomCli"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresseCli"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephoneCli"));
        
        
        tv.setOnMouseClicked(event -> {
        Client selectedClient = tv.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            String code = selectedClient.getCodeCli();
            String nom = selectedClient.getNomCli();
            String prenom = selectedClient.getPrenomCli();
            String adresse = selectedClient.getAdresseCli();
            String telephone = selectedClient.getTelephoneCli();
            this.txtCode.setText(code);
            this.txtNom.setText(nom);
            this.txtPrenom.setText(prenom);
            this.txtAdresse.setText(adresse);
            this.txtTelephone.setText(telephone);
            
           
        }
    });

    lister();
}
    
    
    @FXML
    void gereMed(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLMedicament.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene); // fenetre najouti feha
        stage.setTitle("Login"); // le tittre de fenetre
        stage.show(); 

    }
    
    
}
