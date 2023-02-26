package Controllers;

import Controllers.Main;
import Controllers.ReclamationCardController;
import Entities.Reclamation;
import Entities.Utilisateur;
import Services.ReclamationService;
import Services.UserSession;
import Services.UtilisateurService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GestionGererReclamationController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    FXMLLoader fxmlLoader;
    @FXML
    private GridPane GridRec;
    @FXML
    private TextField FindRec;

    @FXML
    private Label MessageUpdate;

    @FXML
    private ScrollPane ScrollRec;

    @FXML
    private HBox hboxRec;

    @FXML
    private ImageView imgArea;
    private List<Reclamation> mesReclamations;
    @FXML
    void switchToMainFront(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionUtilisateur.fxml"));
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
        try {
            int columns = 0;
            int row = 1;

            for (int i = 0; i < mesReclamations.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Views/ReclamationCard.fxml"));

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
                fxmlLoader.setLocation(getClass().getResource("/Views/ReclamationCard.fxml"));

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

        for (int x = 0; x < GridRec.getColumnCount(); x++) {
            for (int i = 0; i < GridRec.getRowCount(); i++) {
                {
                    removeNodeByRowColumnIndex(i, x, GridRec);
                }
            }
        }
    }
    public boolean removeNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                gridPane.getChildren().remove(node);
                return true;
            }
        }
        return false;
    }
}
