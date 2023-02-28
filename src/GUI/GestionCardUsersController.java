package GUI;

import Entities.Utilisateur;
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

public class GestionCardUsersController {

    @FXML
    private Button BostUser;

    @FXML
    private Button DropUser;

    @FXML
    private Circle ImageUser;
    @FXML
    private VBox vboxUser;
    @FXML
    private Label LoginUser;

    @FXML
    private Button UpdateUser;
    private static GestionCardUsersController instance;
    public GestionCardUsersController(){
        instance = this;
    }
    public static GestionCardUsersController getInstance(){
        return instance;
    }
    UtilisateurService us = new UtilisateurService();

    public void setDataUser1(Utilisateur usr) {
        LoginUser.setText(usr.getLoginUtilisateur());
        String path = usr.getImgUtilisateur();
        if(usr.getRankUtilisateur()==2)
        {
            DropUser.setVisible(false);
            BostUser.setVisible(false);
        }
        if (usr.getRankUtilisateur()==1 || usr.getRankUtilisateur()==2)
        {
            BostUser.setVisible(false);

        }else
        {
            BostUser.setVisible(true);

        }
        File file = new File(path);
        Image image = new Image("file:"+file.getAbsolutePath());
        ImageUser.setFill(new ImagePattern(image));
    }
    @FXML
    void BostUser(ActionEvent event) {
    if (us.RankToAdmin(LoginUser.getText()))
    {
        vboxUser.setVisible(false);
    }
    }

    @FXML
    void DropUser(ActionEvent event) {
        Utilisateur u1 = new Utilisateur();
        u1.setLoginUtilisateur(LoginUser.getText());
    us.supprimer(u1);
    vboxUser.setVisible(false);
    }

    @FXML
    void UpdateUser(ActionEvent event) {
        Utilisateur u ;
        u=us.getUserData(LoginUser.getText());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ModifierUser.fxml"));
        try {
            AnchorPane anchor1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setUserData(u);
            Scene s = new Scene (anchor1);
            stage.setScene(s);
            stage.setTitle("Modifier Utilisateur");
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
