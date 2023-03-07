/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Amine
 */


import Entities.Hebergement;
import Services.CRUDHebergement;
import Entities.Reservation;
import Services.CRUDReservation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("DevCom !");
        //Image icon = new Image(getClass().getResourceAsStream("/imgs/elliot.png"));
       //stage.getIcons().add(icon);
        stage.setScene(scene);

       stage.show();
    }

    /*public static void main(String[] args) throws SQLException {

        //launch();
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur("zarrrouk","amiine","zarrouuk","dsdd@","zarrouk",
                "1234",1,"777","1254");
        utilisateurService.ajouter(utilisateur);
        //utilisateurService.afficher().forEach(System.out::println);
    }*/
}