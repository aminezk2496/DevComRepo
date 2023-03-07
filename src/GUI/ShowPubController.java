/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Publication;
import Entities.Commentaire;
import Entities.Reclamation;
import Services.ServiceCommentaire;
import Entities.Utilisateur;
import static GUI.ModifierPublicationController.fc;
import Services.ReclamationService;
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
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author fhima
 */
public class ShowPubController {
    

    @FXML
    private Label LbLogUser;
@FXML
    private Button ADD;

    @FXML
    private TextField Contenu;
 @FXML
    private Button image;

    @FXML
    private ImageView imageV;

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
     String path;
    
    public static FileChooser fc = new FileChooser();
    File selectedfile;
    ServicePublication sp = new ServicePublication();
        ServiceCommentaire sc = new ServiceCommentaire();

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
    @FXML
    private void importerImageUpdate(ActionEvent event) throws MalformedURLException, FileNotFoundException, IOException {
        // l fc declarith mel fou9 9bal l initialize  public static FileChooser fc = new FileChooser();
        // l selectedfile declarith mel fou9 9bal l initialize File selectedfile
        //l path type mteeha string declariha mel fou9 9bal l initialize 

//        System.out.println(System.getProperty("user.home"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedfile = fc.showOpenDialog(null);

        if (selectedfile != null) {

            path = selectedfile.getAbsolutePath();

            File source = new File(path);
            File destination = new File("C:\\xampp\\htdocs\\devcomimgupload");

            FileUtils.copyFileToDirectory(source, destination);
            Image img = new Image(selectedfile.toURI().toString());
            image.setText(path);
            imageV.setImage(img);

        }
    }

    public void ShowPub() {
       
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
                    System.out.println("com null");
                } else {
                    publicationController1.setDataComs(mesCom);
                    System.out.println(mesCom);
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
    void CreatePub(ActionEvent event) throws SQLException  {
       
        String path1=path;
        if (Contenu.getText().length() == 0 || path1.length()==0 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("conteu ou image vide");
            alert.setContentText("****** veuillez remplir votre publication dans les champs dessous  *****");
            alert.showAndWait();
        } else {
            Publication p = new Publication(Contenu.getText(),u.getLoginUtilisateur(),image.getText(),new java.sql.Date(new java.util.Date().getTime()));
            sp.ajouterPub(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Reclamation Enregistré avec succés");
            alert.showAndWait();
            Contenu.clear();
            imageV.setImage(null);
            image.setText(null);
            clearData();
            initialize();

        }
       
    }
     @FXML
    void RecentPub(ActionEvent event) 
    {
        clearData();
     Publication p = new Publication();
        Commentaire c = new Commentaire();
        mesPub = sp.trierParDate();
      

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
                    System.out.println("com null");
                } else {
                    publicationController1.setDataComs(mesCom);
                    System.out.println(mesCom);
                }

                flowpane.getChildren().add(cardBox);

                flowpane.setBackground(new Background(new BackgroundFill(Color.rgb(140, 200, 140), new CornerRadii(0), new Insets(0))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
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
