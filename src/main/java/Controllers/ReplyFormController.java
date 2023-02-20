package Controllers;

import Entities.Utilisateur;
import Services.ReclamationService;
import Services.UserSession;
import Services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class ReplyFormController {

    @FXML
    private Button BackToMain;

    @FXML
    private Circle ImgUser;

    @FXML
    private TextField ReplyAdmin;

    @FXML
    private Button ReplyWithEmail;

    @FXML
    private TextField adminLog;

    @FXML
    void BackToMain(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

    }
    public void initialize() throws SQLException {
        // Session
       Utilisateur u = new Utilisateur();
        UtilisateurService us = new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        adminLog.setText(u.getEmailUtilisateur());
        String path= us.LoadIMG(u);
        File f = new File(path);
        Image img= new Image("file:"+f.getAbsolutePath());
        ImgUser.setFill(new ImagePattern(img));
    }
    @FXML
    void ReplyWithEmail(ActionEvent event) {
       /* ReclamationService rs = new ReclamationService();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        String Email = (String) stage.getUserData();
        String body=ReplyAdmin.getText();
        if (rs.ContactUserReclam(Email,body))
        {
            stage.close();
        }*/
    }

}
