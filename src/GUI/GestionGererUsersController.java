package GUI;

import Entities.Utilisateur;
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

public class GestionGererUsersController {
    @FXML
    private Label LbLogUser;

    @FXML
    private GridPane UserGridPane;

    @FXML
    private ScrollPane UserScrollPane;
    @FXML
    private Circle idimg1;
    @FXML
    private TextField FindUser;
    @FXML
    private HBox userCardLayout;

    @FXML
    private Button printPdf;

    private List<Utilisateur> mesUtilisateurs;

    public void initialize() throws SQLException {
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        LbLogUser.setText(u.getLoginUtilisateur());
        String path= null;
        try {
            path = us.LoadIMG(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        File f = new File(path);
        Image img = new Image("file:"+f.getAbsolutePath());
        idimg1.setFill(new ImagePattern(img));
        ShowUsers();
        UserScrollPane.setStyle("-fx-background: #131313; -fx-border-color: #131313;");
    }

    public void FindUser() {
        FindUser.setOnKeyReleased(keyEvent -> {
            clearData();
            showUsersByLog();
                }
                );
    }
    public void showUsersByLog()
    {
        String login = FindUser.getText();
        UtilisateurService us = new UtilisateurService();
        try {
            System.out.println(mesUtilisateurs);
            mesUtilisateurs = us.afficherWithID(login);
            int columns = 0;
            int row = 1;
            Utilisateur u = new Utilisateur();
            for (int i = 0; i < mesUtilisateurs.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("GestionCardUsers.fxml"));
                VBox cardBox = fxmlLoader.load();
                GestionCardUsersController gestionCardUsersController = GestionCardUsersController.getInstance();
                gestionCardUsersController.setDataUser1(mesUtilisateurs.get(i));
                if (columns == 2) {
                    columns = 0;
                    ++row;
                }
                UserGridPane.add(cardBox, ++columns, row);
                GridPane.setMargin(cardBox, new Insets(5));
                UserGridPane.setStyle("-fx-background-color: #131313;");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void ShowUsers() {
        UtilisateurService us = new UtilisateurService();
        Utilisateur u = new Utilisateur();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
if (u.getRankUtilisateur()==1)
{
    mesUtilisateurs = us.afficherClient();


}else if (u.getRankUtilisateur()==2)
{
    mesUtilisateurs = us.afficher();
   mesUtilisateurs.remove(u);
}
        try {
            int columns = 0;
            int row = 1;


            for (int i = 0; i < mesUtilisateurs.size(); ++i) {



                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("GestionCardUsers.fxml"));
                VBox cardBox = fxmlLoader.load();
                GestionCardUsersController gestionCardUsersController = GestionCardUsersController.getInstance();
                gestionCardUsersController.setDataUser1(mesUtilisateurs.get(i));
                if (columns == 2) {
                    columns = 0;
                    ++row;
                }
                UserGridPane.add(cardBox, ++columns, row);
                GridPane.setMargin(cardBox, new Insets(5));
                UserGridPane.setBackground(new Background(new BackgroundFill(Color.rgb(140, 200, 140), new CornerRadii(0), new Insets(0))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void clearData() {

       UserGridPane.getChildren().clear();
    }
    @FXML
    void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void printUsersPdf(ActionEvent event) throws DocumentException, FileNotFoundException {
        UtilisateurService us = new UtilisateurService();
        us.pdfUtilisateurs();
    }
}


