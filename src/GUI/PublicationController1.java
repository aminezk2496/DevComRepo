/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;

import Services.ReclamationService;
import Services.BadWords;
import Entities.Utilisateur;
import Entities.Publication;
import Entities.Commentaire;
import Services.ServiceCommentaire;
import Services.ServicePublication;
import Services.UserSession;
import Services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;

/**
 *
 * @author fhima
 */
public class PublicationController1 {

    @FXML
    private HBox HboxCommentaire;

    @FXML
    private HBox HboxContenu;

    @FXML
    private HBox HboxImage;

    @FXML
    private HBox HboxUser;

    @FXML
    private ImageView ImagePub;

    @FXML
    private Circle ImageUser;

    @FXML
    private ListView<Commentaire> ListView;

    @FXML
    private Label LoginUser;

    @FXML
    private Button btn_com;

    @FXML
    private TextField commentaire;

    @FXML
    private Label contenu;

    @FXML
    private VBox vboxUser;
    @FXML
    private Button signaler;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private FlowPane Flowpane1;

    private static PublicationController1 instance;

    public PublicationController1() {
        instance = this;
    }

    public static PublicationController1 getInstance() {
        return instance;
    }

    UtilisateurService us = new UtilisateurService();
    ServicePublication sp = new ServicePublication();
    ServiceCommentaire sc = new ServiceCommentaire();
    Publication p = new Publication();
    int id;
    String idu;
    Utilisateur u = (Utilisateur) UserSession.INSTANCE.get("user");

    public void setDataUser1(Utilisateur usr) {
        LoginUser.setText(usr.getLoginUtilisateur());
        String path = usr.getImgUtilisateur();
        File file = new File(path);
        Image image = new Image("file:" + file.getAbsolutePath());
        ImageUser.setFill(new ImagePattern(image));
    }

    public void setDataPub(Publication pub) {
        id = pub.getId();
        idu = pub.getIduser();
        if ((idu.equals(u.getLoginUtilisateur())) || u.getRankUtilisateur() == 2 || u.getRankUtilisateur() == 1) {
            update.setVisible(true);
            delete.setVisible(true);
            signaler.setVisible(false);
        } else {
            update.setVisible(false);
            signaler.setVisible(true);
            delete.setVisible(false);
        }
        contenu.setText(pub.getContenu());
        String path = pub.getUrlimage();
        File file = new File(path);
        Image image = new Image("file:" + file.getAbsolutePath());
        ImagePub.setImage(image);
        p = pub;
    }

    public void setDataComs(List<Commentaire> Com) throws IOException {
        for (int i = 0; i < Com.size(); ++i) {

            FXMLLoader fxmlLoader1 = new FXMLLoader();

            fxmlLoader1.setLocation(getClass().getResource("Commentaire.fxml"));

            VBox cardBox1 = fxmlLoader1.load();
            CommentaireController commentaireController = CommentaireController.getInstance();
            commentaireController.setDataCom(Com.get(i));
            Utilisateur u1;
            String login = Com.get(i).getIduser();
            u1 = us.getUserData(login);
            commentaireController.setDataUser1(u1);

            Flowpane1.getChildren().add(cardBox1);

        }
    }

    @FXML
    void AddCom(ActionEvent event) throws IOException {
        BadWords.loadConfigs();
        if (BadWords.badWordslength(commentaire.getText()) > 0) {
            commentaire.setText("Vous avez tapez " + String.valueOf(BadWords.badWordslength(commentaire.getText()) + " mot(s) grossier(s) lors de l'insertion de l'article ! \n priére de les enlever !"));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("mot(s) grossier(s)");
            alert.setHeaderText("rejeté");
            alert.setContentText(" mot(s) grossier(s) lors de l'insertion de l'article ! \\n priére de les enlever !");
            alert.showAndWait();

        } else if (commentaire.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("commnentaire");
            alert.setContentText("****** contenu vide *****");
            alert.showAndWait();

        } else {

            Commentaire c = new Commentaire(u.getLoginUtilisateur(), commentaire.getText(), p.getId());
            sc.createCommentaire(c);
            List<Commentaire> updatedList = new ArrayList<>();
            updatedList.add(c);
            setDataComs(updatedList);

        }

    }

    @FXML
    void DropPUB(ActionEvent event) throws SQLException {
        sp.supprimerPub(id);
        vboxUser.setVisible(false);
    }

    @FXML
    void UpdatePub(ActionEvent event) throws SQLException {
        Publication pub;
        pub = sp.readPublication(id);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ModifierPublication.fxml"));
        vboxUser.setVisible(false);
        try {
            AnchorPane anchor1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setUserData(pub);
            Scene s = new Scene(anchor1);
            stage.setScene(s);
            stage.setTitle("Modifier Publication");
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void signaler(ActionEvent event) {
        if (commentaire.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Signal vide");
            alert.setContentText("****** veuillez remplir votre reclamation dans le champ dessous  *****");
            alert.showAndWait();
        } else {
            sp.SendMail(p, commentaire.getText());
            sp.SendMailConf(u.getEmailUtilisateur() ,p, commentaire.getText());
            Reclamation r = new Reclamation(u.getNomUtilisateur(), u.getPrenomUtilisateur(), u.getEmailUtilisateur(), commentaire.getText(), "pending", u);
            ReclamationService rs = new ReclamationService();
            rs.addRec(r, u.getIdUtilisateur());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Publication Enregistré avec succés");
            alert.showAndWait();

        }
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
