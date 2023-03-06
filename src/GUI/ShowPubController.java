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

/**
 *
 * @author fhima
 */
public class ShowPubController {
    

    @FXML
    private Label LbLogUser;

    @FXML
    private FlowPane flowpane;

    @FXML
    private ScrollPane UserScrollPane;
    @FXML
    private Circle idimg1;
    @FXML
    private TextField FindContenu;
    @FXML
    private HBox userCardLayout;
    private List<Publication> mesPub;
    public List<Commentaire> mesCom;

    Utilisateur u = new Utilisateur();
    UtilisateurService us = new UtilisateurService();

    public void initialize() throws SQLException {

        u = (Utilisateur) UserSession.INSTANCE.get("user");
        LbLogUser.setText(u.getLoginUtilisateur());

        String path = null;
        try {
            path = us.LoadIMG(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        File f = new File(path);
        Image img = new Image("file:" + f.getAbsolutePath());
        idimg1.setFill(new ImagePattern(img));
        ShowPub();
        
        UserScrollPane.setStyle("-fx-background: #131313; -fx-border-color: #131313;");
    }

    public void ShowPub() {
        ServicePublication sp = new ServicePublication();
        ServiceCommentaire sc = new ServiceCommentaire();
        Publication p = new Publication();
        Commentaire c = new Commentaire();
        mesPub = sp.afficher();
      

        try {
          
            for (int i = 0; i < mesPub.size(); ++i) {
                System.out.println("err");

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Publication.fxml"));
                VBox cardBox = fxmlLoader.load();
                PublicationController1 publicationController1 = PublicationController1.getInstance();
                publicationController1.setDataPub(mesPub.get(i));
                String login = mesPub.get(i).getIduser();
                int idpub = mesPub.get(i).getId();

                Utilisateur u1;
                u1 = us.getUserData(login);
                publicationController1.setDataUser1(u1);
                mesCom = sc.getCommentsByPublicationId(idpub);
                if (mesCom == null) {
                    System.out.println("err");
                } else {
                    publicationController1.setDataComs(mesCom);
                }

                flowpane.getChildren().add(cardBox);

                flowpane.setBackground(new Background(new BackgroundFill(Color.rgb(140, 200, 140), new CornerRadii(0), new Insets(0))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

     public void FindContenu() {
        FindContenu.setOnKeyReleased(keyEvent -> {
            clearData();
            showPubByCon();
                }
                );
    }
    public void showPubByCon()
    {
        String con = FindContenu.getText();
         ServicePublication sp = new ServicePublication();
        ServiceCommentaire sc = new ServiceCommentaire();
        Publication p = new Publication();
        Commentaire c = new Commentaire();
        try {
            
            mesPub = sp.afficherWithCon(con);
 
          
            for (int i = 0; i < mesPub.size(); ++i) {


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Publication.fxml"));
                VBox cardBox = fxmlLoader.load();
                PublicationController1 publicationController1 = PublicationController1.getInstance();
                publicationController1.setDataPub(mesPub.get(i));
                String login = mesPub.get(i).getIduser();
                int idpub = mesPub.get(i).getId();

                Utilisateur u1;
                u1 = us.getUserData(login);
                publicationController1.setDataUser1(u1);
                mesCom = sc.getCommentsByPublicationId(idpub);
                if (mesCom == null) {
                    System.out.println("err");
                } else {
                    publicationController1.setDataComs(mesCom);
                }

                flowpane.getChildren().add(cardBox);

                flowpane.setBackground(new Background(new BackgroundFill(Color.rgb(140, 200, 140), new CornerRadii(0), new Insets(0))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     public void clearData() {

       flowpane.getChildren().clear();
    }

    
    @FXML
    void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
