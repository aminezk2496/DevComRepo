package GUI;

import Entities.Utilisateur;
import Services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgetPassword1 {


    @FXML
    private Label ErrorLabel;

    @FXML
    private TextField GeneratedCode;

    @FXML
    private Label GeneratedCode1;

    @FXML
    private TextField email;

    @FXML
    private Button forgetpwd;
    @FXML
    private TextField pwd1;

    @FXML
    private TextField pwd2;
    @FXML
    private Button VerifCode;
    @FXML
    private Label pwdL1;
    @FXML
    private Button Continuer;
    @FXML
    private Label pwdL2;
    @FXML
    void forgetpwd(ActionEvent event) throws IOException {
        UtilisateurService us = new UtilisateurService();
        String code= us.ForgetPWD(email.getText());
        System.out.println(code);
        email.setVisible(true);
        GeneratedCode1.setVisible(true);
        GeneratedCode.setVisible(true);
        VerifCode.setVisible(true);
            VerifCode.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    if (GeneratedCode.getText().equals(code)) {
                        pwd1.setVisible(true);
                        pwd2.setVisible(true);
                        pwdL1.setVisible(true);
                        pwdL2.setVisible(true);
                        Utilisateur user = us.getUserDataWithEmail(email.getText());
                        Continuer.setOnAction(new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                if (pwd1.getText().equals(pwd2.getText())) {
                                if (us.ChangePWD(user, pwd1.getText())) {
                                    Parent root = null;
                                    try {
                                        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                } else {
                                    System.out.println("erreur");
                                }
                            }else
                                    System.out.println("les mots de passe saisis ne sont pas identiques");
                            }
                        });

                    }
        }});



    }

}