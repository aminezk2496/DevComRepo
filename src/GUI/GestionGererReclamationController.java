package GUI;

import Entities.Reclamation;
import Entities.Utilisateur;
import Services.ReclamationService;
import Services.UserSession;
import Services.UtilisateurService;
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

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class GestionGererReclamationController {
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
    private GridPane GridRec;
   

   

    @FXML
    private ScrollPane ScrollRec;
    

   
    private List<Reclamation> mesReclamations;
   @FXML
    void switchToMainFront(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(Main.class.getResource("GestionUtilisateur.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Settings Space");
        stage.setScene(scene);
        stage.show();
    }
    public void initialize()
    {
        ScrollRec.setStyle("-fx-background: #131313; -fx-border-color: #131313;");
        GridRec.setStyle("-fx-background: #131313; -fx-border-color: #131313;");
        hboxRec.setStyle("-fx-background: #131313; -fx-border-color: #131313;");
ShowRec();
    }
     void ShowRec()
    {
        UtilisateurService us = new UtilisateurService();
        ReclamationService rs = new ReclamationService();
        Utilisateur u = new Utilisateur();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        mesReclamations=rs.afficher();
        System.out.println(mesReclamations);
        try {
            int columns = 0;
            int row = 1;

            for (int i = 0; i < mesReclamations.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ReclamationCard.fxml"));

                VBox cardBox = fxmlLoader.load();
                System.out.println("A");
                ReclamationCardController reclamationCardController = ReclamationCardController.getInstance();
                System.out.println("A");
                reclamationCardController.setReclamation(mesReclamations.get(i));
                if (columns == 2) {
                    columns = 0;
                    ++row;
                }
                GridRec.add(cardBox, ++columns, row);
                GridPane.setMargin(cardBox, new Insets(10));
                GridRec.setBackground(new Background(new BackgroundFill(Color.rgb(19, 19, 19), new CornerRadii(0), new Insets(0))));

            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
      public void FindRec() {
        FindRec.setOnKeyReleased(keyEvent -> {
                    clearData();
                    ShowRecSpec();
                }
        );
    }
    void ShowRecSpec()
    {
        String rec = FindRec.getText();

        UtilisateurService us = new UtilisateurService();
        ReclamationService rs = new ReclamationService();
        Utilisateur u = new Utilisateur();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        mesReclamations=rs.afficherbynom(rec);
        try {
            int columns = 0;
            int row = 1;

            for (int i = 0; i < mesReclamations.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ReclamationCard.fxml"));

                VBox cardBox = fxmlLoader.load();
                ReclamationCardController reclamationCardController = ReclamationCardController.getInstance();
                reclamationCardController.setReclamation(mesReclamations.get(i));
                if (columns == 2) {
                    columns = 0;
                    ++row;
                }
                GridRec.add(cardBox, ++columns, row);
                GridPane.setMargin(cardBox, new Insets(10));
                GridRec.setBackground(new Background(new BackgroundFill(Color.rgb(19, 19, 19), new CornerRadii(0), new Insets(0))));

            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
    public void clearData() {
 GridRec.getChildren().clear();
    }
}
