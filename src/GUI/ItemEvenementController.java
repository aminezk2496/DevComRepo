/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Entities.Evenement;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ItemEvenementController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;
    
    @FXML
    private Label lieuLabel;
    
    @FXML
    private Label date_debut;
    
    @FXML
    private Label date_fin;
    
    @FXML
    private ImageView img;
    @FXML
    private ImageView ImageItemEvent;
    @FXML
    private void click() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("DetailEvenementClient.fxml"));
        try {
            AnchorPane anchor1 = fxmlLoader.load();
            DetailEvenementClientController DetailEvenementClient = fxmlLoader.getController();
            DetailEvenementClient.setData(evenement);
            Stage stage = new Stage();
            Scene s = new Scene (anchor1);
            stage.setScene(s);
            stage.setTitle("Détails de l'événement");
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Evenement evenement;

    public void setData(Evenement evenement) {
        this.evenement = evenement;
        nameLabel.setText(evenement.getTitreEvent());
        lieuLabel.setText(evenement.getLieuEvent());
        priceLable.setText( evenement.getPrixEvent()+" DT");
        date_debut.setText( evenement.getDateDebutEvent());
        date_fin.setText( evenement.getDateFinEvent());
        File f = new File(evenement.getImageEvent());
        javafx.scene.image.Image img= new javafx.scene.image.Image("file:"+f.getAbsolutePath());
        ImageItemEvent.setImage(img);
    }
}
