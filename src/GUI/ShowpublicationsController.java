/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Utilisateur;
import GUI.*;
import Services.UserSession;
import Services.UtilisateurService;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import Tools.MaConnexion;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author fhima
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.sql.SQLException;



public class ShowpublicationsController {
     private Stage stage;
    private Scene scene;
    private Parent root;
    FXMLLoader fxmlLoader;
    @FXML
    private TextField FindRec;

    @FXML
    private Label MessageUpdate;

    @FXML
    private FlowPane flowPane;

    @FXML
    private HBox hboxRec;

    @FXML
    private ImageView imgArea;

    @FXML
    private Button switchToReclamation;
      @FXML
    private Button switchToMainFront;
    @FXML
    private GridPane GridRec;
    @FXML
    private ScrollPane ScrollRec;
    @FXML private ScrollPane scrollPane;
    

    @FXML
    private Connection cnx; // your database connection object
    public ShowpublicationsController (){
    cnx = MaConnexion.getInstance().getCnx();
       
    }
      
      
    @FXML
    public void initialize() {
            Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");

       
        try {
             
            // Query the database for the 100 most recent publications
            String query = "SELECT * FROM publication ORDER BY date_publication DESC ";
            PreparedStatement stmt = cnx.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            // Loop over each publication and create a new VBox for it
            while (rs.next()) {
                VBox publicationBox = new VBox();
                publicationBox.setPrefSize(100, 100);
                publicationBox.setStyle("-fx-background-color: white; -fx-border-color: lightgray; -fx-border-width: 1px; -fx-padding: 10.0;");
                
                // Populate the UI elements with the data for the publication
                Label contenuLabel = new Label(rs.getString("contenu"));
                contenuLabel.setStyle("-fx-font-weight: bold;");
                publicationBox.getChildren().add(contenuLabel);
                
                Label iduserLabel = new Label(rs.getString("iduser"));
                publicationBox.getChildren().add(iduserLabel);
                
                Label datePublicationLabel = new Label(rs.getDate("date_publication").toString());
                publicationBox.getChildren().add(datePublicationLabel);
                
                String imageUrl = rs.getString("urlimage");
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    File f=new File(imageUrl);
                    Image image = new Image("file:"+f.getAbsolutePath());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(150);
                    imageView.setFitHeight(150);
                    publicationBox.getChildren().add(imageView);
                }
                
                // Add the VBox to the FlowPane
                flowPane.getChildren().add(publicationBox);
            }
            
            rs.close();
            stmt.close();
            
            if (u.getRankUtilisateur()==1||u.getRankUtilisateur()==2)
            switchToReclamation.setVisible(true);
        else
            switchToReclamation.setVisible(false);
             
        } catch (SQLException ex) {
        }
    }
        @FXML
    public void switchToReclamation(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("publications.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    
    
}
        @FXML
 public void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}}




    
