package com.devcom.discovertnproject;

import com.devcom.discovertnproject.entities.Utilisateur;
import com.devcom.discovertnproject.services.UtilisateurService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        //launch();
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur(77,"zarrouk","amine","zarrouk","ddd@","zarrouk",
                "1234",1,"777","1254");
        utilisateurService.ajouter(utilisateur);
        utilisateurService.afficher().forEach(System.out::println);
    }
}