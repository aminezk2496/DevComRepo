package GUI;


import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *..\images\img_204972.png
 * @author rihem
 */
public class MainClass extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("Client.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Admin !");
        //Image icon = new Image(getClass().getResourceAsStream("/imgs/elliot.png"));
        //stage.getIcons().add(icon);
        stage.setScene(scene);

       stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}