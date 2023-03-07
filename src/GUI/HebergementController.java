   /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

//import com.mysql.cj.jdbc.integration.c3p0.MysqlConnectionTester;
import Entities.Hebergement;
import Entities.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.CRUDHebergement;
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
public class HebergementController implements Initializable {
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

    /**
     * Initializes the controller class.
     * 
     */
    //private ComboBox comboDispo;
    @FXML
    private ComboBox comboType;
     @FXML
    private Button switchToMainFront;
    @FXML
    private TextField tfNom;
    @FXML
    private TextArea tfDesc;
    //private TextField tfLoc;
    @FXML
    private TextField tfImage;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnModifier;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfId;
    @FXML
    private Button tfSuppprimer;
    @FXML
    private TableColumn<Hebergement, String> tbId;
    @FXML
    private TableColumn<Hebergement, String> tbNom;
    @FXML
    private TableColumn<Hebergement, String> tbDesc;
    /*@FXML
    private TableColumn<Hebergement, String> tbDispo;
    @FXML*/
    @FXML
    private TableColumn<Hebergement, Float> tbPrix;
    @FXML
    private TableColumn<Hebergement, String> tbLoca;
    @FXML
    private TableColumn<Hebergement, String> tbContact;
    @FXML
    private TableColumn<Hebergement, String> tbType;
    @FXML
    private TableColumn<Hebergement, String> tbNb_ch;
    @FXML
    private TableView<Hebergement> tbHebergement;
    
    
    Connection connection = null;
    ObservableList<Hebergement> hebergement = FXCollections.observableArrayList();
    @FXML
    private Button btSearch;
    @FXML
    private ComboBox comboTri;
    @FXML
    private ComboBox comboLoc;
    @FXML
    private TextField tfNb_ch;
    @FXML
    private TextField recupId; // c'est un texte Field caché dans l'interface
    
    
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
        // TODO
       /* ObservableList<String> List1 = FXCollections.observableArrayList("Oui","Non");
        comboDispo.setItems(List1);*/
           Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        ObservableList<String> List2 = FXCollections.observableArrayList("Hotel","Motel","Maison d'Hote");
        comboType.setItems(List2);
        
        ObservableList<String> List3 = FXCollections.observableArrayList("Nom","Localisation","Prix");
        comboTri.setItems(List3);
        
        ObservableList<String> List4 = FXCollections.observableArrayList("Ariana","Bizert","Nabeul","Tunis");
        comboLoc.setItems(List4);
        
        viewHeber();
        
    }    

    @FXML
    private void saveHeber(ActionEvent event) {
        String nom_heber = tfNom.getText();
        String description_heber = tfDesc.getText();
        String localisation_heber = comboLoc.getSelectionModel().getSelectedItem().toString();
        float prix_heber = Float.parseFloat(tfPrix.getText()) ;
        int contact = Integer.parseInt(tfImage.getText());
        String type_heber = comboType.getSelectionModel().getSelectedItem().toString();
        int nb_chambre = Integer.parseInt(tfNb_ch.getText());
       
        
        //pour la date get.value().toString()
        /* int disponibilite =0;
        
        if("Oui".equals(Ldispo))
            disponibilite = 1;
        else if ("Non".equals(Ldispo))
            disponibilite = 0;*/

        
        Hebergement h = new Hebergement(0, nom_heber, description_heber, nb_chambre, prix_heber, localisation_heber, contact, type_heber);
        CRUDHebergement hb = new CRUDHebergement();
        hb.ajouterpHeber(h);
        //viewHeber();
       // tbHebergement.setItems(hebergement);
       getHeber();
    }

    @FXML
    private void updatedHeber(ActionEvent event) {
        int id_heber = Integer.parseInt(recupId.getText());
        String nom_heber = tfNom.getText();
        String description_heber = tfDesc.getText();
        String localisation_heber = comboLoc.getSelectionModel().getSelectedItem().toString();
        float prix_heber = Float.parseFloat(tfPrix.getText()) ;
        int contact = Integer.parseInt(tfImage.getText());
        String type_heber = comboType.getSelectionModel().getSelectedItem().toString();
        int nb_chambre = Integer.parseInt(tfNb_ch.getText());
       

       /* int disponibilite =0;
        
        if("Oui".equals(Ldispo))
            disponibilite = 1;
        else if ("Non".equals(Ldispo))
            disponibilite = 0;*/
        
        Hebergement h = new Hebergement(id_heber, nom_heber, description_heber, nb_chambre, prix_heber, localisation_heber, contact, type_heber);
        CRUDHebergement hb = new CRUDHebergement();
        hb.modifierHeber(h);
        // tbHebergement.setItems(hebergement);
        getHeber();
    }

    @FXML
    private void deleteHeber(ActionEvent event) {
        int id_heber = Integer.parseInt(recupId.getText());
        CRUDHebergement hb = new CRUDHebergement();
        hb.supprimerHeber(id_heber);
       // tbHebergement.setItems(hebergement);
       getHeber();
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
    public void viewHeber() {
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
        
        //tbHebergement.getColumns().addAll(tbId,tbNom,tbDesc,tbDispo,tbPrix,tbLoca,tbContact,tbType);
       // MyConnection();
       tbHebergement.setRowFactory(tv-> {
            TableRow<Hebergement> row = new TableRow<>();
            row.setOnMouseClicked(even -> {
                if (even.getClickCount() == 1 && !row.isEmpty()){                   
                    int myIndex =  tbHebergement.getSelectionModel().getSelectedIndex();
                    recupId.setText(""+(tbHebergement.getItems().get(myIndex).getId()));
                    tfNom.setText((tbHebergement.getItems().get(myIndex).getNom_heber()));
                    tfDesc.setText((tbHebergement.getItems().get(myIndex).getDescription_heber()));
                    tfPrix.setText(""+(tbHebergement.getItems().get(myIndex).getPrix_heber()));
                    tfNb_ch.setText(""+(tbHebergement.getItems().get(myIndex).getDisponibilite()));
                    tfImage.setText(""+(tbHebergement.getItems().get(myIndex).getContact_heber()));
                    comboLoc.setValue(tbHebergement.getItems().get(myIndex).getLocalisation_heber());
                    comboType.setValue(tbHebergement.getItems().get(myIndex).getType_heber());
                }
            
            }); 
            return row;  
        });
    }

    @FXML
    private void search() {
                String nom = tfId.getText();
    ObservableList<Hebergement> dataList = tbHebergement.getItems();
    for (int i = 0; i < dataList.size(); i++) {
        if (dataList.get(i).getNom_heber().equals(nom)) {
            tbHebergement.getSelectionModel().select(i);
            tbHebergement.scrollTo(i);
            break;
        }
    }
    }

    @FXML
    public void triHeber() {
        String LTri = comboTri.getSelectionModel().getSelectedItem().toString();
        Comparator<Hebergement> comparator = null;
        switch (LTri){
            case "Nom":
            comparator = Comparator.comparing(Hebergement::getNom_heber);
            break;
        case "Localisation":
            comparator = Comparator.comparing(Hebergement::getLocalisation_heber);
            break;
        case "Prix":
            comparator = Comparator.comparing(Hebergement::getPrix_heber);
            break;
        default:
            break;
        }
        if (comparator != null) {
        FXCollections.sort(hebergement, comparator);
        tbHebergement.setItems(hebergement);
    }

    }


@FXML
 public void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
}
    

