package GUI;

import Entities.Reclamation;
import Entities.Utilisateur;
import Services.ReclamationService;
import Services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ReclamationCardController {

    @FXML
    private Circle ImageUser;

    @FXML
    private Label NomUser;

    @FXML
    private Button dropRec;

    @FXML
    private Label prenomUser;

    @FXML
    private Button replyRec;
    @FXML
    private Label idRec;
    @FXML
    private Label sujetRec;
    private static ReclamationCardController instance;
    public ReclamationCardController(){
        instance = this;
    }
    public static ReclamationCardController getInstance(){
        return instance;
    }
    @FXML
    private VBox vboxUser;
    UtilisateurService us = new UtilisateurService();

    public void initialize() throws SQLException {

    }
    public void setReclamation(Reclamation reclamation) throws SQLException {
        Utilisateur userReclamation = new Utilisateur();
        userReclamation=us.getUserDataWithEmail(reclamation.getEmail_utilisateur());
        sujetRec.setText(reclamation.getDescription_reclamation());
        NomUser.setText(userReclamation.getNomUtilisateur());
        prenomUser.setText(userReclamation.getPrenomUtilisateur());
        String path= us.LoadIMG(userReclamation);
        File f = new File(path);
        Image img= new Image("file:"+f.getAbsolutePath());
        ImageUser.setFill(new ImagePattern(img));
        idRec.setText(reclamation.getEmail_utilisateur());
        idRec.setVisible(false);
    }
    @FXML
    void CloseRec(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        if (rs.CloseRec(idRec.getText()))
        {
            vboxUser.setVisible(false);
        }
    }

    @FXML
    void replyRec(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ReplyForm.fxml"));

        try {
            AnchorPane anchor1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setUserData(idRec.getText());
            Scene s = new Scene (anchor1);
            stage.setScene(s);
            stage.setTitle("Reply");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
