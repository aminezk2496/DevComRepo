/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

//import Entities.Camping;
import Entities.Camping;
import Entities.Randonnee;
import Utils.MyConnection;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
//import Utils.MyConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rihem
 */
public class RandonneeController implements Initializable {
 @FXML
    private TableColumn<Randonnee, Date> clDate;

    @FXML
    private TableColumn<Randonnee, String> clLieux;

    @FXML
    private TableColumn<Randonnee, Integer> clNiveau;

    @FXML
    private TableColumn<Randonnee, String> clNom;

    @FXML
    private TableColumn<Randonnee, Double> clPrix;

    @FXML
    private TableColumn<Randonnee, String> clProgramme;

    @FXML
    private TableColumn<Randonnee, Integer> clid;

   @FXML
    private TableColumn<Randonnee, Integer> clNbrP;
   
   @FXML
    private TableColumn<Randonnee, String> clImage;
    
    @FXML
    private TableView<Randonnee> tableR; 

    @FXML
    private Button Add;

      @FXML
    private DatePicker date_Rand;

    @FXML
    private ComboBox lieux;

    @FXML
    private TextField niveau_Diff;

    @FXML
    private TextField idR;
    
    @FXML
    private TextField nom;

    @FXML
    private TextField prix;

    @FXML
    private TextArea programme;
    
    @FXML
    private TextField NbrP;
    
    @FXML
    private Button insertImageButton;
    
    ObservableList<String> ListD = FXCollections.observableArrayList("Bizerte", "Nabeul","Klibia","Touzer","Gabes","Mahdia","Haouaria","Tbarka");

    @FXML
//    void AjouterRand(ActionEvent event) {
//
//    }
    int myIndex;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //tableView.getItems().addAll(getDataFromSource());
        // TODO
        showRandonnee();
                    lieux.setItems(ListD);

    }    
    
    @FXML
    public ObservableList<Randonnee> getRandonnee(){
        ObservableList<Randonnee> randonnee = FXCollections.observableArrayList();
        String query = "SELECT * FROM randonnee";
        
        try{
        Statement st = new MyConnection().cnx.createStatement();
        ResultSet res = st.executeQuery(query);
        while(res.next()){
            Randonnee RD = new Randonnee();
            RD.setId_Randonnee(res.getInt("id_Randonnee"));
            RD.setNom(res.getString("Nom"));
            RD.setLieux(res.getString("Lieux"));
            RD.setDate_Rand(res.getDate("Date_Rand").toLocalDate());
            RD.setNiveau_diff(res.getString("Niveau_diff"));
            RD.setPrix(res.getInt("Prix"));
            RD.setProgramme(res.getString("Programme"));
            RD.setNbr_PlaceR(res.getInt("Nbr_PlaceR"));
            RD.setImageR(res.getString("ImagesR"));
            System.out.println(res.getString("ImagesR"));
            String img = res.getString("ImagesR");
            randonnee.add(RD);
        }
       } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return randonnee;
    }
    public void showRandonnee(){
        ObservableList<Randonnee> list = getRandonnee();
        tableR.setItems(list);
        clid.setCellValueFactory(new PropertyValueFactory<>("id_Randonnee"));
        clNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        clDate.setCellValueFactory(new PropertyValueFactory<>("Date_Rand"));
        clNiveau.setCellValueFactory(new PropertyValueFactory<>("Niveau_diff"));
        clPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        clLieux.setCellValueFactory(new PropertyValueFactory<>("Lieux"));
        clProgramme.setCellValueFactory(new PropertyValueFactory<>("Programme"));
        clNbrP.setCellValueFactory(new PropertyValueFactory<>("Nbr_PlaceR"));
        clImage.setCellValueFactory(new PropertyValueFactory<>("ImagesR"));
    
        System.out.println(clImage);
          tableR.setRowFactory( tv -> {
		     TableRow<Randonnee> myRow = new TableRow<>();
		     myRow.setOnMouseClicked (event -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            myIndex =  tableR.getSelectionModel().getSelectedIndex();
		 
		          idR.setText(String.valueOf(tableR.getItems().get(myIndex).getId_Randonnee()));
		           nom.setText(tableR.getItems().get(myIndex).getNom());
		           lieux.setValue(tableR.getItems().get(myIndex).getLieux());
                            prix.setText(String.valueOf(tableR.getItems().get(myIndex).getPrix()));
                           niveau_Diff.setText(tableR.getItems().get(myIndex).getNiveau_diff());
                           date_Rand.setValue(tableR.getItems().get(myIndex).getDate_Rand());
                           programme.setText(tableR.getItems().get(myIndex).getProgramme());
                         NbrP.setText(String.valueOf(tableR.getItems().get(myIndex).getNbr_PlaceR()));
                           selectedImage=String.valueOf(tableR.getItems().get(myIndex).getImageR());

		        }
		     });
		        return myRow;
         
         
    });
         
    }

    
    public void AjouterRand(ActionEvent event)throws SQLException {
         String Nom = nom.getText();
        String date_r = String.valueOf(date_Rand.getValue());
        String Periode = niveau_Diff.getText();
        String Prix = prix.getText();
        String Lieux = (String) lieux.getValue();
        String Programme = programme.getText();
        String NbrPR = NbrP.getText();
        

        if (Nom.isEmpty() || date_r.isEmpty() || Periode.isEmpty() ||  NbrPR.isEmpty() || Prix.isEmpty() || Lieux.isEmpty() || Programme.isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else {
           insert();
           clear();
           System.out.println("votre Randonnee a été ajouté");
        }
    }
        private void insert() {
        String insert = "INSERT INTO randonnee( Nom, Date_Rand, Lieux, Prix, Niveau_diff, Programme, ImagesR, Nbr_PlaceR) VALUES (?,?,?,?,?,?,?,?)";
File file = new File(selectedImage);
byte[] imageBytes = new byte[(int) file.length()];

try (FileInputStream fileInputStream = new FileInputStream(file)) {
    fileInputStream.read(imageBytes);
} catch (IOException e) {
    e.printStackTrace();
    System.out.println(file.getAbsolutePath());
}
        try {
             PreparedStatement pst = new MyConnection().cnx.prepareStatement(insert);
                pst.setString(1, nom.getText());
                pst.setString(2, String.valueOf(date_Rand.getValue()));
                pst.setString(3, (String) lieux.getValue());
                pst.setDouble(4, Double.parseDouble(prix.getText()));
                pst.setString(5, niveau_Diff.getText());
                pst.setString(6, programme.getText());
                pst.setString(7, "file:/" +selectedImage);
                pst.setInt(8, Integer.parseInt(NbrP.getText()));

                pst.executeUpdate();
                showRandonnee();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Randonnee ajouté !");
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
        String delete = "DELETE FROM randonnee where id_Randonnee = ?";
        String deleteParticipation = "DELETE FROM participation WHERE id_Rand = ?";
    try {
        // Supprimer d'abord les entrées de la table "participation"
        PreparedStatement stParticipation = new MyConnection().cnx.prepareStatement(deleteParticipation);
        stParticipation.setInt(1, Integer.parseInt(idR.getText()));
        stParticipation.executeUpdate();
            PreparedStatement st = new MyConnection().cnx.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(idR.getText()));
            st.executeUpdate();
            showRandonnee();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 
    private void update() {
        String update= "UPDATE randonnee SET Nom=?, Date_Rand=?, Lieux=?, Prix=?, Niveau_diff=?, Programme=?,ImagesR=?,Nbr_PlaceR=? "
                    + "WHERE id_Randonnee="+Integer.parseInt(idR.getText());
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
            PreparedStatement st = new MyConnection().cnx.prepareStatement(update);
            st.setString(1, nom.getText());
            st.setString(2, String.valueOf(date_Rand.getValue()));
            st.setString(3, (String) lieux.getValue());
            st.setDouble(4, Double.parseDouble(prix.getText()));
            st.setString(5, niveau_Diff.getText());
            st.setString(6, programme.getText());
            st.setString(7, "file:/" +selectedImage);
            st.setInt(8, Integer.parseInt(NbrP.getText()));
            st.executeUpdate();
            showRandonnee();
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
    }
 
    void clear() {
        idR.setText(null);
        nom.setText(null);
        date_Rand.setValue(null);
        niveau_Diff.setText(null);
        lieux.getItems().clear();
        programme.setText(null);
        prix.setText(null);
        NbrP.setText(null);
    }
 

    public String selectedImage;
    @FXML
     void insertImageAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(insertImageButton.getScene().getWindow());
        if (selectedFile != null) {   
              
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
}
