package Controllers;

import Entities.Utilisateur;
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

public class ModifierUserController {

    @FXML
    private Button BackToUsers;

    @FXML
    private Button DoUpdate;

    @FXML
    private Circle ImgUser;

    @FXML
    private TextField adrUser;

    @FXML
    private TextField emailUser;

    @FXML
    private TextField loginUser;

    @FXML
    private TextField nomUser;
    @FXML
    private Button GetData;
    @FXML
    private TextField prenomUser;

    @FXML
    private TextField telUser;
    /*private static ModifierUserController instance;
    public static ModifierUserController getInstance(){
        return instance;
    }
    public ModifierUserController (){
        instance = this;
    }*/
    public void initialize(){

    }
    @FXML
    void GetData(ActionEvent event) throws SQLException {
        DoUpdate.setVisible(false);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Utilisateur user = (Utilisateur) stage.getUserData();
        UtilisateurService us = new UtilisateurService();
        nomUser.setText(user.getNomUtilisateur());
        prenomUser.setText(user.getPrenomUtilisateur());
        telUser.setText(user.getNumero_telephoneUtilisateur());
        loginUser.setText(user.getLoginUtilisateur());
        emailUser.setText(user.getEmailUtilisateur());
        adrUser.setText(user.getAdresseUtilisateur());
        String path= us.LoadIMG(user);
        File f = new File(path);
        Image img = new Image("file:"+f.getAbsolutePath());
        ImgUser.setFill(new ImagePattern(img));
        GetData.setVisible(false);
        DoUpdate.setVisible(true);

    }


    @FXML
    void BackToUsers(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

    }

    @FXML
    void DoUpdate(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Utilisateur user = (Utilisateur) stage.getUserData();
        UtilisateurService us = new UtilisateurService();
        user.setLoginUtilisateur(loginUser.getText());
        user.setEmailUtilisateur(emailUser.getText());
        user.setNumero_telephoneUtilisateur(telUser.getText());
        user.setNomUtilisateur(nomUser.getText());
        user.setAdresseUtilisateur(adrUser.getText());
        user.setPrenomUtilisateur(prenomUser.getText());
        us.modifier(user);
        stage.close();

    }

}
