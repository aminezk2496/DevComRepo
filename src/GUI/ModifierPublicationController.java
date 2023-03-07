/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commentaire;
import Entities.Publication;
import Entities.Utilisateur;
import static GUI.publicationcontroller.fc;
import Services.BadWords;
import Services.ServiceCommentaire;
import Services.ServicePublication;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

public class ModifierPublicationController {

    
    @FXML
    private Button Back;

    @FXML
    private TextField Contenu;

    @FXML
    private Button DoUpdate;

    @FXML
    private Label idimg;

    @FXML
    private Button image;
        @FXML
    private ImageView imageV;
    String path;
    
    public static FileChooser fc = new FileChooser();
    File selectedfile;
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
  

    @FXML
    void DoUpdate(ActionEvent event) throws SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        Publication p = (Publication) stage.getUserData();
        ServicePublication sp = new ServicePublication();
        p.setContenu(Contenu.getText());
        p.setId(p.getId());
        String s=path;
        p.setUrlimage(s);
        p.setIduser(p.getIduser());
        
       
      if (Contenu.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Publication");
            alert.setContentText("****** contenu vide *****");
            alert.showAndWait();
          
            
            
        } else {
            
               sp.modiferPub(p,p.getId());
        stage.close();}

        

      
    }
 @FXML
    void Back(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

    }
}