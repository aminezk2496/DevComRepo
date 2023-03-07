/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Hebergement;
import Entities.Reservation;
import Entities.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.CRUDReservation;
import Services.UserSession;
import Services.UtilisateurService;
import Tools.MaConnexion;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gio・ブランドー
 */
public class ReservationController implements Initializable {
      @FXML
    private Label LbLogUser;
 @FXML
    private Circle idimg1;
  @FXML
    private ScrollPane UserScrollPane;
 
    @FXML
    private TextField FindContenu;
     Utilisateur u = new Utilisateur();
    UtilisateurService us = new UtilisateurService();

    @FXML
    private TableView<Hebergement> tbHebergement ;
    @FXML
    private TableColumn<Hebergement, String> tbId;
    @FXML
    private TableColumn<Hebergement, String> tbNom;
    @FXML
    private TableColumn<Hebergement, String> tbDesc;
    @FXML
    private TableColumn<Hebergement, String> tbNb_ch;
    @FXML
    private TableColumn<Hebergement, String> tbPrix;
    @FXML
    private TableColumn<Hebergement, String> tbLoca;
    @FXML
    private TableColumn<Hebergement, String> tbContact;
    @FXML
    private TableColumn<Hebergement, String> tbType;
    @FXML
    private ComboBox cbNb_ch;
    @FXML
    private ComboBox cbDuree;
    @FXML
    private Button btAjout_reser;
    @FXML
    private Button switchToMainFront;
    
    Connection connection = null;
    ObservableList<Hebergement> hebergement = FXCollections.observableArrayList();
    @FXML
    private TextField htfId_heber; //c'est un texte field caché dans l'interface
    @FXML
    private DatePicker slDate;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         u = (Utilisateur) UserSession.INSTANCE.get("user");
        LbLogUser.setText(u.getLoginUtilisateur());

        String path = null;
        try {
            path = us.LoadIMG(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        File f = new File(path);
        javafx.scene.image.Image img = new javafx.scene.image.Image("file:" + f.getAbsolutePath());
        idimg1.setFill(new ImagePattern(img));
           Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        // TODO
        ObservableList<String> List1 = FXCollections.observableArrayList("1","2","3","4","5","6","7");
        cbDuree.setItems(List1);
        
        ObservableList<String> List2= FXCollections.observableArrayList("1","2","3","4","5");
        cbNb_ch.setItems(List2);
        viewHeber();
    }    

        private  void getHeber() {
        try {
            hebergement.clear();
            
            String req = "SELECT * FROM `hebergement`";
            Statement ste = connection.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {
                Hebergement resultHeber;
                resultHeber = new Hebergement(
                        result.getInt("id_heber"), 
                        result.getString("nom_heber"), 
                        result.getString("description_heber"), 
                        result.getInt("nb_chambre"), 
                        result.getInt("prix_heber"), 
                        result.getString("localisation_heber"), 
                        result.getInt( "contact_heber"), 
                        result.getString("type_heber"));
                hebergement.add(resultHeber);
                tbHebergement.setItems(hebergement);
            }
         //   System.out.println(hebergement);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
}
    
    @FXML
    private void viewHeber() {
    connection  = MaConnexion.getInstance().getCnx();
        getHeber();
        tbId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbNom.setCellValueFactory(new PropertyValueFactory<>("nom_heber"));
        tbDesc.setCellValueFactory(new PropertyValueFactory<>("description_heber"));
        tbNb_ch.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        tbPrix.setCellValueFactory(new PropertyValueFactory<>("prix_heber"));
        tbLoca.setCellValueFactory(new PropertyValueFactory<>("localisation_heber"));
        tbContact.setCellValueFactory(new PropertyValueFactory<>("contact_heber"));
        tbType.setCellValueFactory(new PropertyValueFactory<>("type_heber"));
        
                tbHebergement.setRowFactory(tv-> {
            TableRow<Hebergement> row = new TableRow<>();
            row.setOnMouseClicked(even -> {
                if (even.getClickCount() == 1 && !row.isEmpty()){                   
                    int myIndex =  tbHebergement.getSelectionModel().getSelectedIndex();
                   htfId_heber.setText(String.valueOf(tbHebergement.getItems().get(myIndex).getId())); 
                }
            
            }); 
            return row;  
        });
    }

    @FXML
    private void saveReser(ActionEvent event) {
        //int rowIndex = 0;
    //int columnIndex  = 0;
        int id_heber = Integer.parseInt(htfId_heber.getText());
        //System.out.println("Voici l'id "+id_heber);
        String date = slDate.getValue().toString();
        int duree = Integer.parseInt(cbDuree.getSelectionModel().getSelectedItem().toString());
        int nb_chamre = Integer.parseInt(cbNb_ch.getSelectionModel().getSelectedItem().toString());
        
        Reservation r=  new Reservation(0, id_heber, date, duree, nb_chamre);
        CRUDReservation rb = new CRUDReservation();
        rb.ajouterpHeber(r);
    }  

@FXML
 public void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
}
