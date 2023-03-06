/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author fhima
 */
import GUI.*;

import Entities.Publication;
import Entities.Utilisateur;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import java.text.SimpleDateFormat;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import Services.ServicePublication;
import Services.UserSession;
import Services.UtilisateurService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class publicationcontroller implements Initializable {

   
    @FXML
    private Button switchToMainFront;
    @FXML
    private TextArea contenu;

    @FXML
    private Button btn_ajouter;
    @FXML
    private TableView<Publication> tableview;
    @FXML
    private ImageView img1;

    @FXML
    private TableColumn<Publication, String> ID;
    @FXML
    private TableColumn<Publication, Integer> contenu_A;
    @FXML
    private TableColumn<Publication, Float> user_A;
    @FXML
    private TableColumn<Publication, String> imag_A;
    @FXML
    private TableColumn<Publication, Date> date_A;

    Window window;
    ServicePublication pub = new ServicePublication();
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_img;
    @FXML
    private Button btn_img1;

    File selectedfile;
    String path;
    String path2;
    public static FileChooser fc = new FileChooser();
Utilisateur u = new Utilisateur();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UtilisateurService us = new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        ServicePublication pub = new ServicePublication();
        List<Publication> myList = pub.afficher();
        ObservableList list = FXCollections.observableArrayList(myList);
        tableview.setItems(list);
//        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        contenu_A.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        user_A.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        imag_A.setCellValueFactory(new PropertyValueFactory<>("urlimage"));
        date_A.setCellValueFactory(new PropertyValueFactory<>("date_publication"));

        // Add cell factory for the date_A column
        date_A.setCellFactory(column -> {
            return new TableCell<Publication, Date>() {
                private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(format.format(item));

                    }
                }
            };
        });

        // Add listener to selection
        tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Handle the new selection
                System.out.println("Selected item: " + newSelection);
            }
        });

    }

    @FXML
    private void addPub(ActionEvent event) throws IOException, ParseException {
        ServicePublication sp = new ServicePublication();

        if (contenu.getText().length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No publication contenu");
            alert.setContentText("****** contenu vide *****");
            alert.showAndWait();
          
            
            
        } else {
            String s = path;
            
            sp.ajouterPub(new Publication(contenu.getText(),u.getLoginUtilisateur(), s, new java.sql.Date(new java.util.Date().getTime())));
            refresh();

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
            File destination = new File("C:\\Users\\21622\\Documents\\NetBeansProjects\\gestionCommunité\\src\\img");

            FileUtils.copyFileToDirectory(source, destination);
            Image img = new Image(selectedfile.toURI().toString());
            btn_img1.setText(path);
            img1.setImage(img);

        }
    }

    public void updatePublication(ActionEvent event) {
        Publication selectedPublication = tableview.getSelectionModel().getSelectedItem();
        if (selectedPublication == null) {
            // Show an error message if no publication is selected
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No publication selected");
            alert.setContentText("Please select a publication to update.");
            alert.showAndWait();
            return;
        }

        // Get the updated publication details from the UI controls
        
        String updatedContenu = contenu.getText();
        String updatedUrlImage = path2; // Use the existing URL if no new image is selected
        if (selectedfile != null) {
            updatedUrlImage = selectedfile.toURI().toString();
        }

        // Create a new Publication object with the updated details
        Publication updatedPublication = new Publication(selectedPublication.getId(),
                updatedContenu, selectedPublication.getIduser(), updatedUrlImage, selectedPublication.getDate()
        );

        // Call the service method to update the publication in the database
        pub.modiferPub(updatedPublication, updatedPublication.getId());
        // Update the TableView with the new data
        tableview.getItems().set(tableview.getSelectionModel().getSelectedIndex(), updatedPublication);

    }

    private void refresh() {
        ServicePublication pub1 = new ServicePublication();
        List<Publication> myList = pub1.afficher();
        ObservableList list = FXCollections.observableArrayList(myList);
        tableview.setItems(list);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        contenu_A.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        user_A.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        imag_A.setCellValueFactory(new PropertyValueFactory<>("urlimage"));
        date_A.setCellValueFactory(new PropertyValueFactory<>("date_publication"));

        // Add cell factory for the date_A column
        date_A.setCellFactory(column -> {
            return new TableCell<Publication, Date>() {
                private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(format.format(item));

                    }
                }
            };
        });
    }

    @FXML
    private void supprimerPublication(ActionEvent event) {
        Window owner = btn_supprimer.getScene().getWindow();

        // Get the selected publication
        Publication selectedPub = tableview.getSelectionModel().getSelectedItem();

        // Check if a publication is selected
        if (selectedPub == null) {
            showAlert(Alert.AlertType.ERROR, owner, "Error", "Please select a publication to delete.");
            return;
        }

        // Confirm the deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(owner);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this publication?");

        if (alert.showAndWait().orElse(null) != ButtonType.OK) {
            // The user canceled the deletion
            return;
        }

        // Delete the selected publication from the database
        ServicePublication sp = new ServicePublication();
        sp.supprimerPub(selectedPub.getId());
        refresh();
    }

    public void trier() {
        ServicePublication pub2 = new ServicePublication();
        List<Publication> myList = pub2.trierParDate();
        ObservableList list = FXCollections.observableArrayList(myList);
        tableview.setItems(list);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        contenu_A.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        user_A.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        imag_A.setCellValueFactory(new PropertyValueFactory<>("urlimage"));
        date_A.setCellValueFactory(new PropertyValueFactory<>("date_publication"));

        // Add cell factory for the date_A column
        date_A.setCellFactory(column -> {
            return new TableCell<Publication, Date>() {
                private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(format.format(item));

                    }
                }
            };
        });
    }

    @FXML
    public void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
