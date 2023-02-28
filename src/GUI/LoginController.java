package GUI;

import Services.UserSession;
import Services.UtilisateurService;
import Tools.MaConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.Connection;

public class LoginController  {
    @FXML
    private Label ErrorLabel;
    @FXML
    private Button loginButton;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button registre;
    @FXML
    private TextField usernameTextField;
    Connection cnx = MaConnexion.getInstance().getCnx();
    UtilisateurService us = new UtilisateurService();

    public void Login(ActionEvent event) throws IOException {

        String Password= DigestUtils.sha1Hex(passwordTextField.getText());
        if (usernameTextField.getText().equals("") || (passwordTextField.getText()).equals("")) {
            ErrorLabel.setText("username ou le mot de passe est vide");
        }
        else if (us.Login(usernameTextField.getText(),Password)){
            UserSession.INSTANCE.put("user",us.getUserData(usernameTextField.getText()));
            Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setUserData(usernameTextField.getText());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            ErrorLabel.setText("Invalid Data, please try again");
        }
    }
    @FXML
    void LogwithFB(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogWithFacebook.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void registre(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void forgotpassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ForgetPassword1.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}