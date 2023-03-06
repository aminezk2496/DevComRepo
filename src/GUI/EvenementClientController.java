/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Entities.Evenement;
import Entities.Utilisateur;
import Services.UserSession;
import Services.UtilisateurService;
import Tools.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 *
 * @author Amal
 */
public class EvenementClientController implements Initializable {
    private Label label;
    private TextField id_event;
    private TextField titre_event;
    private TextField date_event;
    private TextField prix_event;
    private TextField lieu_event;
    private TextField desc_event;
    

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ScrollPane PartiList;
    
    @FXML
    private javafx.scene.control.Button bsave;
    @FXML
    private javafx.scene.control.Button bupdate;
    @FXML
    private javafx.scene.control.Button bdelete;
    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, Integer> col_id_event;
    @FXML
    private TableColumn<Evenement, String> col_titre_event;
    @FXML
    private TableColumn<Evenement, String> col_lieu_event;
    @FXML
    private TableColumn<Evenement, String> col_prix_event;
    @FXML
    private TableColumn<Evenement, String> col_date_debut_event;
     @FXML
    private TableColumn<Evenement, String> col_date_fin_event;
    @FXML
    private TableColumn<Evenement, String> col_desc_event;
    @FXML
    private TableColumn<Evenement, String> col_image_event;
      
    @FXML
    private TextField id_ch;
    @FXML
    private TextField titre_ch;
    @FXML
    private TextField lien_ch;
    @FXML
    private TextField prix_ch;
    @FXML
    private DatePicker date_debut_ch;
    @FXML
    private DatePicker date_fin_ch;
    @FXML
    private TextArea desc_ch;
    @FXML
    private TextField image_ch;
    
    private ImageView logo;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane gridParti;
    @FXML
    private Label NomUser;
    
    
    @FXML
    private TextField searchInput;
            
    Connection con = MaConnexion.getInstance().getCnx();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        affiche();
        affichePart();
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        NomUser.setText(u.getLoginUtilisateur());
    }
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    @FXML 
    private void tablehandleButtonAction() {
        bdelete.setVisible(true);
        bsave.setVisible(false);
        bupdate.setVisible(true);
        
        Evenement et = table.getSelectionModel().getSelectedItem();
        id_ch.setText(String.valueOf(et.getIdEvent()));
        titre_ch.setText(et.getTitreEvent());
        date_debut_ch.setValue(LOCAL_DATE(et.getDateDebutEvent()));
        date_fin_ch.setValue(LOCAL_DATE(et.getDateFinEvent()));
        prix_ch.setText(et.getPrixEvent());
        lien_ch.setText(et.getLieuEvent());
        desc_ch.setText(et.getDescEvent());
        image_ch.setText(et.getImageEvent());
    }
 
    public ObservableList<Evenement> getEvenement(String searchText) {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        String select = "";
        if("".equals(searchText) ){
            select = "SELECT * FROM evenement;";
        }else{
            select = "SELECT * FROM evenement WHERE titre_event LIKE '%"+searchText+"%' OR lieu_event LIKE '%"+searchText+"%' OR desc_event LIKE '%"+searchText+"%';";
        }
        try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Evenement et = new Evenement();
                et.setIdEvent(rs.getInt("id_event"));
                et.setTitreEvent(rs.getString("titre_event"));
                et.setDateDebutEvent(rs.getString("date_debut_event"));
                et.setDateFinEvent(rs.getString("date_fin_event"));
                et.setPrixEvent(rs.getString("prix_event"));
                et.setLieuEvent(rs.getString("lieu_event"));
                et.setDescEvent(rs.getString("desc_event"));
                et.setImageEvent(rs.getString("image_event"));

                list.add(et);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       return list;
    }
    public void back(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("MainFront.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Events Space");
        stage.setScene(scene);
        stage.show();
    }
    public void affiche() {
        String searchText = "";
        try{
            searchText = searchInput.getText();
        }catch (Exception e) {
             System.out.println(e);
        }
         ObservableList<Evenement> list = getEvenement(searchText);

        try {
            int column = 0;
            int row = 1;
            grid.getChildren().clear();
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("ItemsEvenementClient.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemEvenementController ItemEvenementController = fxmlLoader.getController();
                ItemEvenementController.setData(list.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane,  column++, row); //(child,column,row)
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10,10,10,10));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
       public ObservableList<Evenement> getPartEvenement() {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        String select = "SELECT * FROM participation AS parti INNER JOIN utilisateur AS ut ON parti.id_utilisateur = ut.id_utilisateur INNER JOIN evenement AS evt ON evt.id_event = parti.id_evenement";
        
        try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Evenement et = new Evenement();
                et.setIdEvent(rs.getInt("id_event"));
                et.setTitreEvent(rs.getString("titre_event"));
                et.setDateDebutEvent(rs.getString("date_debut_event"));
                et.setDateFinEvent(rs.getString("date_fin_event"));
                et.setPrixEvent(rs.getString("prix_event"));
                et.setLieuEvent(rs.getString("lieu_event"));
                et.setDescEvent(rs.getString("desc_event"));
                et.setImageEvent(rs.getString("image_event"));

                list.add(et);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       return list;
    }
    public void affichePart() {

         ObservableList<Evenement> list = getPartEvenement();

        try {
            int column = 0;
            int row = 1;
            
            gridParti.getChildren().clear();
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("ItemsParticipationClient.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemEvenementController ItemEvenementController = fxmlLoader.getController();
                ItemEvenementController.setData(list.get(i));
                row++;
                gridParti.add(anchorPane,  column, row); //(child,column,row)
                gridParti.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridParti.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridParti.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                gridParti.setMinHeight(Region.USE_COMPUTED_SIZE);
                // grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridParti.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(5,5,5,5));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
 
}
