package Controllers;

import Entities.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;

public class UserCardController {

    @FXML
    private ImageView ImageUser;
    @FXML
    private Label LbLogUser;
    @FXML
    private Circle CircleImg;
    @FXML
    private Label idrole;

    public void setDataUser(Utilisateur user){
        LbLogUser.setText(user.getLoginUtilisateur());
        if (user.getRankUtilisateur()==1) {
            idrole.setText("Admin");
        }
        else if (user.getRankUtilisateur()==2){
            idrole.setText("SupAdmin");

        }
        else
        {
            idrole.setText("Client");
        }
        String path = user.getImgUtilisateur();
        File file = new File(path);
        Image image = new Image("file:"+file.getAbsolutePath());
        CircleImg.setFill(new ImagePattern(image));
    }
}
