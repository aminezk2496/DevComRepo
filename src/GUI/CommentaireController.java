/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Publication;
import Entities.Commentaire;
import Services.ServiceCommentaire;
import Entities.Utilisateur;
import Services.ServicePublication;
import Services.UserSession;
import Services.UtilisateurService;
import com.itextpdf.text.DocumentException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 *
 * @author fhima
 */
public class CommentaireController {

  
    @FXML
    private Label CommentaireLabel;

    @FXML
    private HBox HboxUser;

    @FXML
    private Circle ImageUser;

    @FXML
    private Label LoginUser;

    @FXML
    private Button ModifierCom;

    @FXML
    private Button SuppCom;

    @FXML
    private VBox vboxUser;
  
    
   private static CommentaireController instance;
    public CommentaireController(){
        instance = this;
    }
      public static CommentaireController getInstance(){
        return instance;
    }
     UtilisateurService us = new UtilisateurService();
     int id;
     
     ServiceCommentaire sc = new ServiceCommentaire();
     
     Utilisateur u = (Utilisateur) UserSession.INSTANCE.get("user");
     public void setDataUser1(Utilisateur usr) {
        LoginUser.setText(usr.getLoginUtilisateur());
        String path = usr.getImgUtilisateur();
        File file = new File(path);
        Image image = new Image("file:"+file.getAbsolutePath());
        ImageUser.setFill(new ImagePattern(image));
    }
     
     public void setDataCom(Commentaire c) {
         CommentaireLabel.setText(c.getContenuc());
         id=c.getId();
         if((c.getIduser().equals(u.getLoginUtilisateur())) || u.getRankUtilisateur()==2 )
        {
            ModifierCom.setVisible(true);
            SuppCom.setVisible(true);
            
        }
         else 
         {
             ModifierCom.setVisible(false);
         
            SuppCom.setVisible(false);
         }

}
     @FXML
    void DropUser(ActionEvent event) throws SQLException {
       sc.deleteCommentaire(id);
    vboxUser.setVisible(false);
    }
      
    
    @FXML
    void UpdateCom(ActionEvent event) throws SQLException {
     Commentaire C;
      C=sc.readCommentaire(id);
     FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ModifierCommentaire.fxml"));
         vboxUser.setVisible(false);
        try {
            AnchorPane anchor1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setUserData(C);
            Scene s = new Scene (anchor1);
            stage.setScene(s);
            stage.setTitle("Modifier Commentaire");
            stage.show();
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

      

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
        

    }
