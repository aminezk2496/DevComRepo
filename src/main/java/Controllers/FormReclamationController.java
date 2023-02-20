package Controllers;

import Entities.Reclamation;
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

public class FormReclamationController {

    @FXML
    private Button BackToMain;

    @FXML
    private TextField DescriptionUser;

    @FXML
    private Button GetData;

    @FXML
    private Circle ImgUser;

    @FXML
    private TextField emailUser;

    @FXML
    private TextField nomUser;

    @FXML
    private TextField prenomUser;
    Utilisateur u = new Utilisateur() ;
    Reclamation reclamation = new Reclamation();
    ReclamationService rs = new ReclamationService();
    UtilisateurService us= new UtilisateurService() ;
    public void initialize() throws SQLException {

        u = (Utilisateur) UserSession.INSTANCE.get("user");
        prenomUser.setText(u.getPrenomUtilisateur());
        nomUser.setText(u.getNomUtilisateur());
        emailUser.setText(u.getEmailUtilisateur());
        String path= us.LoadIMG(u);
        File f = new File(path);
        Image img= new Image("file:"+f.getAbsolutePath());
        ImgUser.setFill(new ImagePattern(img));
    }
    @FXML
    void BackToMain(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    void SubmitRec(ActionEvent event) throws SQLException {
        u = (Utilisateur) UserSession.INSTANCE.get("user");
       reclamation.setEmail_utilisateur(emailUser.getText());
       reclamation.setNom_utilisateur(nomUser.getText());
       reclamation.setPrenom_utilisateur(prenomUser.getText());
       reclamation.setDescription_reclamation(DescriptionUser.getText());
        rs.addRec(reclamation,u.getIdUtilisateur());
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

}
