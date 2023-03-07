/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Camping;
import Entities.Utilisateur;
import Services.UserSession;
import Services.UtilisateurService;
//import Services.CampingCRUD;
import Tools.MaConnexion;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author rihem
 */
public class CampingController implements Initializable {
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
    private TextField idC;
    
    @FXML
    private TableView<Camping> tableC;
    
    @FXML
    private TableColumn<Camping, Double> ColPrix;

//    @FXML
//    private TableColumn<Camping, Button> colAction;

    @FXML
    private TableColumn<Camping, Date> colDate_F;

    @FXML
    private TableColumn<Camping, Date> colDate_d;

    @FXML
    private TableColumn<Camping, String> colDescrip;

    @FXML
    private TableColumn<Camping, Integer> colID_C;

    @FXML
    private TableColumn<Camping, String> colLieux;

    @FXML
    private TableColumn<Camping, String> colNom;

    @FXML
    private TableColumn<Camping, Integer> colPeriode;
      
    @FXML
    private TableColumn<Camping, Integer> colNbrP;
    
     @FXML
    private TableColumn<Camping, String> colImage;
    
//    
    @FXML
    private Button Add;

    @FXML
    private DatePicker date_Fin;

    @FXML
    private DatePicker date_debut;

    @FXML
    private TextArea description;

    @FXML
    private ComboBox lieux;

    @FXML
    private TextField nom;

    @FXML
    private TextField periode;

    @FXML
    private TextField Prix;
    
    @FXML
    private TextField NbrP;
    
    @FXML
    private Button bclear;

    @FXML
    private Button bdelete;

    @FXML
    private Button bupdate;
    
    @FXML
    private Button insertImageButton;
    
    ObservableList<String> ListD = FXCollections.observableArrayList("Bizerte","AinDrahem", "Nabeul","Beja","Klibia","Touzer","Gafsa","Mahdia","Haouaria","Tabarka");



   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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

        
        showCamping();
            lieux.setItems(ListD);
    }
    int myIndex;
     @FXML

    public ObservableList<Camping> getCamping(){
                        Connection cnx = MaConnexion.getInstance().getCnx();

        ObservableList<Camping> camping = FXCollections.observableArrayList();
        String query = "SELECT * FROM camping";
        
        try{
        Statement st = cnx.createStatement();
        ResultSet res = st.executeQuery(query);
        while(res.next()){
            Camping camp = new Camping();
            camp.setId_Camping(res.getInt("id_Camping"));
            camp.setNom(res.getString("Nom"));
            camp.setLieux(res.getString("Lieux"));
            camp.setDate_Debut(res.getDate("date_Debut").toLocalDate());
            camp.setDate_Fin(res.getDate("date_Fin").toLocalDate());
            camp.setPeriode(res.getInt("Periode"));
            camp.setPrix(res.getInt("Prix"));
            camp.setDescription(res.getString("Description"));
            camp.setNbr_PlaceC(res.getInt("Nbr_PlaceC"));
            camping.add(camp);
        }
       } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return camping;
    }
    public void showCamping(){
        ObservableList<Camping> list = getCamping();
        tableC.setItems(list);
        colID_C.setCellValueFactory(new PropertyValueFactory<>("id_Camping"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colDate_d.setCellValueFactory(new PropertyValueFactory<>("date_Debut"));
        colDate_F.setCellValueFactory(new PropertyValueFactory<>("date_Fin"));
        colPeriode.setCellValueFactory(new PropertyValueFactory<>("Periode"));
        ColPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        colLieux.setCellValueFactory(new PropertyValueFactory<>("Lieux"));
        colNbrP.setCellValueFactory(new PropertyValueFactory<>("Nbr_PlaceC"));
        colDescrip.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tableC.setRowFactory( tv -> {
		     TableRow<Camping> myRow = new TableRow<>();
		     myRow.setOnMouseClicked (event -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            myIndex =  tableC.getSelectionModel().getSelectedIndex();
		 
		          idC.setText(String.valueOf(tableC.getItems().get(myIndex).getId_Camping()));
		           nom.setText(tableC.getItems().get(myIndex).getNom());
		           lieux.setValue(tableC.getItems().get(myIndex).getLieux());
                           Prix.setText(String.valueOf(tableC.getItems().get(myIndex).getPrix()));
                           periode.setText(String.valueOf(tableC.getItems().get(myIndex).getPeriode()));
                           NbrP.setText(String.valueOf(tableC.getItems().get(myIndex).getNbr_PlaceC()));
                           description.setText(tableC.getItems().get(myIndex).getDescription());
                       date_debut.setValue(tableC.getItems().get(myIndex).getDate_Debut());
		           date_Fin.setValue(tableC.getItems().get(myIndex).getDate_Fin());
                          System.out.println(tableC.getItems().get(myIndex).getDescription());

                         
                           

		        }
		     });
		        return myRow;
         
         
    });
                }
    
    public void AjouterCamp(ActionEvent event)throws SQLException, FileNotFoundException, IOException {
        String Nom = nom.getText();
        String date_d = String.valueOf(date_debut.getValue());
        String date_f = String.valueOf(date_Fin.getValue());
        String Periode = periode.getText();
        String prix = Prix.getText();
        String Lieux = (String) lieux.getValue();
        String Description = description.getText();       
        String NbrPlace= NbrP.getText();

        if (Nom.isEmpty() || date_d.isEmpty() || date_f.isEmpty() || Periode.isEmpty() || prix.isEmpty() || Lieux.isEmpty() || Description.isEmpty() || selectedImage.isEmpty() || NbrPlace.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else {
           insert();
           clear();
           System.out.println("votre Camping a été ajouté");
        }
    }
        private void insert() throws FileNotFoundException, IOException {
                            Connection cnx = MaConnexion.getInstance().getCnx();

        String insert = "INSERT INTO camping(Nom, date_Debut, date_Fin, Periode, Prix, Lieux, Description, ImageC, Nbr_PlaceC,Image) VALUES (?,?,?,?,?,?,?,?,?,?)";
      
//        File imageFile = new File(selectedImage);
//        byte[] imageData = Files.readAllBytes(imageFile.toPath());
                File file = new File(selectedImage);
byte[] imageBytes = new byte[(int) file.length()];

try (FileInputStream fileInputStream = new FileInputStream(file)) {
    fileInputStream.read(imageBytes);
} catch (IOException e) {
    e.printStackTrace();
    System.out.println(file.getAbsolutePath());
}
        try {
        
             PreparedStatement pst = cnx.prepareStatement(insert);
                pst.setString(1, nom.getText());
                pst.setString(2, String.valueOf(date_debut.getValue()));
                pst.setString(3, String.valueOf(date_Fin.getValue()));
                pst.setInt(4, Integer.parseInt(periode.getText()));
                pst.setDouble(5, Double.parseDouble(Prix.getText()));
                pst.setString(6, (String) lieux.getValue());
                pst.setString(7, description.getText());
//              FileInputStream fis = new FileInputStream(imageFile);
                pst.setString(8, "file:/" +selectedImage);
                pst.setInt(9, Integer.parseInt(NbrP.getText()));
                pst.setBytes(10, imageBytes);

                pst.executeUpdate();
                showCamping();
                 Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("produit ajouté !");
                alert.showAndWait();
                
                
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
             
            
//        Produit p2 = new Produit(txtTitre.getText(),prix,description,bN);
//        ps1.ajouterProduit(p2);
//         Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setContentText("produit ajouté !");
//        alert.showAndWait();
       
    
public void delete() {
                    Connection cnx = MaConnexion.getInstance().getCnx();

        String delete = "DELETE FROM camping where id_Camping = ?";
        try {
            PreparedStatement st =cnx.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(idC.getText()));
            st.executeUpdate();
            showCamping();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 
    private void update() {
                        Connection cnx = MaConnexion.getInstance().getCnx();

        String update= "UPDATE camping SET Nom=?, date_Debut=?, date_Fin=?, Periode=?, Prix=?, Lieux=?, Description=?,ImageC=?,Nbr_PlaceC=? "
                    + "WHERE id_Camping=" + Integer.parseInt(idC.getText());
//        File file = new File(selectedImage);
//byte[] imageBytes = new byte[(int) file.length()];
//
//try (FileInputStream fileInputStream = new FileInputStream(file)) {
//    fileInputStream.read(imageBytes);
//} catch (IOException e) {
//    e.printStackTrace();
//    System.out.println(file.getAbsolutePath());
//}
        try {
            PreparedStatement st = cnx.prepareStatement(update);
            st.setString(1, nom.getText());
            st.setString(2, String.valueOf(date_debut.getValue()));
            st.setString(3, String.valueOf(date_Fin.getValue()));
            st.setInt(4, Integer.parseInt(periode.getText()));
            st.setDouble(5, Double.parseDouble(Prix.getText()));
            st.setString(6, (String) lieux.getValue());
            st.setString(7, description.getText());
            st.setInt(9, Integer.parseInt(NbrP.getText()));
            st.setString(8, "file:/" +selectedImage);
            st.executeUpdate();
            showCamping();
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
    }
 
    void clear() {
        idC.setText(null);
        nom.setText(null);
        date_debut.setValue(null);
        date_Fin.setValue(null);
        periode.setText(null);
        Prix.setText(null);
        lieux.getItems().clear();
        description.setText(null);
        NbrP.setText(null);
    }
 
//    @FXML
//    private void saveEvent(ActionEvent event) {
//        insert();
//        clear();
//    }
    public String selectedImage;
 @FXML
    void insertImageAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(insertImageButton.getScene().getWindow());
        if (selectedFile != null) {
            //insertImage(selectedFile.getPath());
//             URL url  = formatImagePath(selectedFile.toString());
//            selectedImage =  url.toString();        
              
        try {
            javafx.scene.image.Image image = new javafx.scene.image.Image(selectedFile.toURI().toString());
            String imageUrl = selectedFile.toURI().toString().substring(6);
            
        selectedImage =  imageUrl; 
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
        }  

    }
   
    public static URL formatImagePath(String filePath) {
        try {
            
            URI uri = new URI("file", filePath.replace("\\", "/"), null);
            URL url = uri.toURL();
            return url;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    
    @FXML
    private void updateEvent(ActionEvent event) {
        update();
        clear();
        Add.setDisable(false);
    }
 
    @FXML
    private void deleteEvent(ActionEvent event) {
        delete();
        clear();
    }
 
    @FXML
    private void clearEvent(ActionEvent event) {
        clear();
    }
@FXML
        void BackA(ActionEvent event) {
try {
            Parent root=FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene scene =new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RandonneeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 @FXML
    void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
  


        
    

