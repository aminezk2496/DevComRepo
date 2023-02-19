package Controllers;

import Entities.Utilisateur;
import Services.UtilisateurService;
import Tools.MaConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp2Controller {
    @FXML
    private Button show;


    @FXML
    private ImageView imgarea;
    @FXML
    private Button loadimg;
    PreparedStatement store;
    Connection cnx = MaConnexion.getInstance().getCnx();


    @FXML
    void loadimg(ActionEvent event) throws SQLException, IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Utilisateur user = (Utilisateur) stage.getUserData();
        UtilisateurService us =new UtilisateurService();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*")
        );
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\devcomimgupload\\"+user.getLoginUtilisateur()+".jpg";
        if (file != null) {
            // pickUpPathField it's your TextField fx:id
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsolutePath());
            Image img = new Image("file:"+file.getAbsolutePath());
            imgarea.setImage(img);
            int b=0;
            while(b!=-1){
                b=bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
        } else  {
            System.out.println("error");
        }
        if (us.WriteIMG(user,DBPath))
        {
            System.out.println("done");
        }

    }
    @FXML
    public void sendMail(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Utilisateur user = (Utilisateur) stage.getUserData();
        UtilisateurService us = new UtilisateurService();
     //   us.SendMail(user,"null");
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SignUp3.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setUserData(user);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}