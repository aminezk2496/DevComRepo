/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import Entities.Evenement;
import Entities.EvenementType;
import Entities.Utilisateur;
import Services.UserSession;
import Services.UtilisateurService;

import Tools.MaConnexion;
import java.awt.Label;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 *
 * @author Amal
 */
public class GestionEvenementAdmin  implements Initializable {
    
       @FXML
    private javafx.scene.control.Label LbLogUser;
 @FXML
    private Circle idimg1;
  @FXML
    private ScrollPane UserScrollPane;
 
    @FXML
    private TextField FindContenu;
     Utilisateur u = new Utilisateur();
    UtilisateurService us = new UtilisateurService();
    private Label label;
    private TextField id_event;
    private TextField titre_event;
    private TextField date_event;
    private TextField prix_event;
    private TextField lieu_event;
    private TextField desc_event;
    private TextField images_event;
    private String Path_image_Upload;

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
    private ImageView ImageEventView;

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField id_ch;
    @FXML
    private TextField titre_ch;
    @FXML
    private ComboBox type_ch;
    
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
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    
    ObservableList<String> listEvt = FXCollections.observableArrayList();
    
    Connection con = MaConnexion.getInstance().getCnx();
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
        
        bdelete.setVisible(false);
        bupdate.setVisible(false);
        affiche();
    }
    public static final LocalDate LOCAL_DATE (String dateString){
        LocalDate localDate = null;
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            localDate = LocalDate.parse(dateString.substring(0,19), formatter);
            
        }catch (Exception e) {
            System.out.println(e);
        }
        return localDate;
    }
     public void back(javafx.event.ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("MainFront.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Events Space");
        stage.setScene(scene);
        stage.show();
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
        type_ch.getSelectionModel().select(listEvt.get(et.getEventType()-1));
        File f = new File(et.getImageEvent());
        javafx.scene.image.Image img= new javafx.scene.image.Image("file:"+f.getAbsolutePath());
        ImageEventView.setImage(img);
    }
 
    public ObservableList<Evenement> getEvenement() {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        
        String select = "SELECT * FROM evenement;"; 
        String selectType = "SELECT * FROM evenement_type"; 
        try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              //  System.out.println(rs.getString("date_event"));
                
               
                Evenement et = new Evenement();
                et.setIdEvent(rs.getInt("id_event"));
                et.setTitreEvent(rs.getString("titre_event"));
                et.setDateDebutEvent(rs.getString("date_debut_event"));
                et.setDateFinEvent(rs.getString("date_fin_event"));
                et.setPrixEvent(rs.getString("prix_event"));
                et.setLieuEvent(rs.getString("lieu_event"));
                et.setDescEvent(rs.getString("desc_event"));
                et.setImageEvent(rs.getString("image_event"));
                et.setEventType(rs.getInt("id_evenement_type"));
                list.add(et);
            }
           
            PreparedStatement st1 = con.prepareStatement(selectType);
            ResultSet rsType = st1.executeQuery();
            while (rsType.next()) {
                EvenementType EnT = new EvenementType();
                listEvt.add(rsType.getString("nom_evenement_type"));
            }
            type_ch.setItems(listEvt);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       return list;
    }
    
 
    public void affiche() {

         ObservableList<Evenement> list = getEvenement();
        col_id_event.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        col_titre_event.setCellValueFactory(new PropertyValueFactory<>("titreEvent"));
        col_date_debut_event.setCellValueFactory(new PropertyValueFactory<>("dateDebutEvent"));
        col_date_fin_event.setCellValueFactory(new PropertyValueFactory<>("dateFinEvent"));
        col_prix_event.setCellValueFactory(new PropertyValueFactory<>("prixEvent"));
        col_lieu_event.setCellValueFactory(new PropertyValueFactory<>("lieuEvent"));
        col_desc_event.setCellValueFactory(new PropertyValueFactory<>("descEvent"));
        col_image_event.setCellValueFactory(new PropertyValueFactory<>("imageEvent"));

        table.setItems(list);
    }
 
     private void insert() throws ClassNotFoundException {
         if(this.VerifChamp()){
             return;
         }
         String select = "INSERT INTO evenement (titre_event,date_debut_event,date_fin_event,prix_event,lieu_event,desc_event,image_event,id_evenement_type)VALUES(?,?,?,?,?,?,?,?)";
        
        try {
            LocalDate value_date_debut_ch = date_debut_ch.getValue();
            LocalDate value_date_fin_ch = date_fin_ch.getValue();
            PreparedStatement st = con.prepareStatement(select);
            //st.setString(1, id_ch.getText());
            st.setString(1, titre_ch.getText());
            st.setString(2, value_date_debut_ch.toString());
            st.setString(3, value_date_fin_ch.toString());
            st.setString(4, prix_ch.getText());
            st.setString(5, lien_ch.getText());
            st.setString(6, desc_ch.getText());
            st.setString(7, image_ch.getText());
            st.setInt(8, (type_ch.getSelectionModel().getSelectedIndex()+1));
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            
           System.out.println(ex);
        }
    }
     public boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    static String getAlphaNumericString(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
	for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
      	}
	return sb.toString();
    }
 

     @FXML
    void ModifierEventIMG() throws IOException {
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\Users\\USER\\Desktop\\ESPRIT\\Pi\\discover\\image\\"+this.getAlphaNumericString(10)+".jpg";
        image_ch.setText(DBPath);
        
        
        
        if (file != null) {
            File f = new File(file.getAbsolutePath());

            javafx.scene.image.Image img= new javafx.scene.image.Image("file:"+f.getAbsolutePath());
            ImageEventView.setImage(img);
            // pickUpPathField it's your TextField fx:id
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            int b=0;
            while(b!=-1){
                b=bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
        } 
    }
    private boolean VerifChamp(){
        LocalDate value_date_debut_ch = date_debut_ch.getValue();
        LocalDate value_date_fin_ch = date_fin_ch.getValue();
        String title = titre_ch.getText();
        String prix = prix_ch.getText();
        String lieu = lien_ch.getText();
        String desc = desc_ch.getText();
        String image = image_ch.getText();

        if("".equals(title)){
            JOptionPane.showMessageDialog(null, "Champ titre est obligatoire");
            return true;
        }
        if("".equals(prix)){
            JOptionPane.showMessageDialog(null, "Champ prix est obligatoire");
            return true;
        }
        if(!this.isStringInt(prix)){
            JOptionPane.showMessageDialog(null, "Champ prix doit être nombre");
            return true;
        }
        if("".equals(lieu)){
            JOptionPane.showMessageDialog(null, "Champ lieu est obligatoire");
            return true;
        }
        if("".equals(desc)){
            JOptionPane.showMessageDialog(null, "Champ déscription est obligatoire");
            return true;
        }
        if("".equals(image)){
            JOptionPane.showMessageDialog(null, "Champ image est obligatoire");
            return true;
        }
        return false;
    }
    
    private void delete() throws ClassNotFoundException {
        String delete = "DELETE FROM evenement WHERE id_event = ?";

        try {
            PreparedStatement st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(id_ch.getText()));
             System.out.println(st);
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
           System.out.println(ex);
        }
    }
    
    private void update() throws ClassNotFoundException {
        String update = "UPDATE Evenement SET titre_event = ?,date_debut_event = ?,date_fin_event = ?,prix_event = ?,lieu_event = ?,desc_event = ?,image_event = ?,id_evenement_type = ? where id_event =?";
        try {
            PreparedStatement st = con.prepareStatement(update);
           
            st.setString(1, titre_ch.getText());
            st.setDate(2, java.sql.Date.valueOf(date_debut_ch.getValue()));
            st.setDate(3, java.sql.Date.valueOf(date_fin_ch.getValue()));
            st.setString(4, prix_ch.getText());
            st.setString(5, lien_ch.getText());
            st.setString(6, desc_ch.getText());
            st.setString(7, image_ch.getText());
            st.setInt(8, (type_ch.getSelectionModel().getSelectedIndex()+1));
            st.setString(9, id_ch.getText());
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
 
    void clear() {
        id_ch.setText(null);
        titre_ch.setText(null);
        date_debut_ch.setValue(LOCAL_DATE("2023-02-20 15:31:31"));
        date_fin_ch.setValue(LOCAL_DATE("2023-02-20 15:31:31"));
        prix_ch.setText(null);
        lien_ch.setText(null);
        desc_ch.setText(null);
        image_ch.setText(null);
        
        
        File f = new File("src\\GUI\\upload.jpg");
        System.out.println(f.getAbsolutePath());
        javafx.scene.image.Image img= new javafx.scene.image.Image("file:"+f.getAbsolutePath());
        ImageEventView.setImage(img);
        bsave.setDisable(false);
    }
    @FXML 
    private void saveEvent() throws ClassNotFoundException {
        insert();
        clear();
    }
    @FXML
    private void updateEvent() throws ClassNotFoundException {
        bdelete.setVisible(true);
        update();
        clear();
        //bsave.setDisable(false);
    }
    @FXML
    private void deleteEvent() throws ClassNotFoundException {
        int input = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ?");  
        if(input == 0){
            delete();
            clear();
            JOptionPane.showMessageDialog(null, "Supprimé avec succès");
        }else{
            JOptionPane.showMessageDialog(null, "Suppression annulée");
        }
    }
 
    @FXML 
    private void clearEvent() {
        bdelete.setVisible(false);
        bsave.setVisible(true);
        bupdate.setVisible(false);
        clear();
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

