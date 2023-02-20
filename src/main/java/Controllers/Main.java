package Controllers;

import Entities.Utilisateur;
import Services.UtilisateurService;
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
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("DiscoverTn !");
        Image icon = new Image(getClass().getResourceAsStream("/imgs/logoDiscover.png"));
       stage.getIcons().add(icon);
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