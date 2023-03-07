/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commentaire;
import Entities.Utilisateur;
import Services.BadWords;
import Services.ServiceCommentaire;
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
import javafx.scene.control.Alert;

public class ModifierCommentaireController {

    @FXML
    private Button Back;

    @FXML
    private TextField Contenu;
      
  
     @FXML
    private Button DoUpdate;
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
    void BackToUsers(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

    }

    @FXML
    void DoUpdate(ActionEvent event) throws SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        Commentaire c = (Commentaire) stage.getUserData();
        ServiceCommentaire sc = new ServiceCommentaire();
        c.setContenuc(Contenu.getText());
        c.setId(c.getId());
        c.setIdpub(c.getIdpub());
        c.setIduser(c.getIduser());
        
         BadWords.loadConfigs();
        if (BadWords.badWordslength(Contenu.getText())>0){
            
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("mot(s) grossier(s)");
            alert.setHeaderText("rejeté");
            alert.setContentText(" mot(s) grossier(s) lors de l'insertion de l'article ! \\n priére de les enlever !");
            alert.showAndWait();
 }
       else if (Contenu.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("commnentaire");
            alert.setContentText("****** contenu vide *****");
            alert.showAndWait();
          
            
            
        } else {
            
               sc.updateCommentaire(c);
        stage.close();}

        

      
    }
 @FXML
    void Back(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

    }
}