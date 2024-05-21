/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Dao.DAOUser;
import Dao.LaConnexion;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC-Etudiante Info
 */
public class FXMLRegisterController implements Initializable {

     @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtMdp;
    
    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtAdr;


    @FXML
    private Button btnSignup;
    static Connection con= LaConnexion.seConnecter();
    @FXML
    void signup(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtMdp.getText();
        String telephone = txtTel.getText();
        String adresse = txtAdr.getText();

        if (!isValidEmail(username)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setHeaderText(null);
            alert.setContentText("Le nom d'utilisateur doit être une adresse email valide !");
            alert.showAndWait();
            return;
        }

        if (DAOUser.addUser(username, password, telephone, adresse)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inscription réussie");
            alert.setHeaderText(null);
            alert.setContentText("Utilisateur ajouté avec succès !");
            alert.showAndWait();
            ((Stage) btnSignup.getScene().getWindow()).close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'inscription");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'inscription de l'utilisateur !");
            alert.showAndWait();
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
}
