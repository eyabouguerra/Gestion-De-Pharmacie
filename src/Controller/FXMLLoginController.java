/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.DAOUser;
import Dao.LaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC-Etudiante Info
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Button btnLogin;
    
        @FXML
    private PasswordField password;

    private static final Connection con = LaConnexion.seConnecter();

    @FXML
    void login(ActionEvent event) throws IOException {
        String username = txtUser.getText();
        String password = txtPass.getText();
        if (DAOUser.login(username, password)) {
            Stage stage = new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLClient.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Home Page");
            stage.show();
            ((Stage) btnLogin.getScene().getWindow()).close();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur de connexion", null, "Nom d'utilisateur ou mot de passe incorrect !");
        }
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLRegister.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Register Page");
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if needed
    }
}
