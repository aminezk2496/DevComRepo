/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Randonnee;
import Entities.Utilisateur;
import static Services.MailingN.sendFromGMail;
import Services.UtilisateurService;
import Services.UserSession;
import Utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rihem
 */
public class ClientRandController  {

   
    /**
     * Initializes the controller class.
     */
    @FXML
    private FlowPane productPane;    
    
    @FXML
    private TextField searchField;
    
    @FXML
public void search(String searchTerm) {
    // Load data from the database
    String insert = "INSERT INTO participation(RefP ,id_Client,id_Rand, Nombre,Montant, Etat, date_Parti,Nom) VALUES (?,?,?,?,?,?,?,?)";
    String query = "SELECT * FROM randonnee WHERE LOWER(Lieux) LIKE ?";
       
//        Utilisateur user = new Utilisateur();
//        UtilisateurService us =new UtilisateurService();
//        user = (Utilisateur) UserSession.INSTANCE.get("user");
//        int Id_Client=user.getIdUtilisateur();
        /*el Id_Client Na7ehaa w 7ot el eli comment lfou9*/
    int Id_Client=1;
    
    
//        Utilisateur user = new Utilisateur();
//        UtilisateurService us =new UtilisateurService();
//        user = (Utilisateur) UserSession.INSTANCE.get("user");
//        String nomUser=user.getNomUtilisateur();
        /*el nomUser Na7ehaa w 7ot el eli comment lfou9*/
 String nomUser="User";

    try {
        PreparedStatement statement = new MyConnection().cnx.prepareStatement(query);
        statement.setString(1, "%" + searchTerm.toLowerCase() + "%");
        ResultSet resultSet = statement.executeQuery();
        
        // Clear existing product panes
        productPane.getChildren().clear();
        
        // IdClient
        while (resultSet.next()) {
            int idS = resultSet.getInt("id_Randonnee");
            String name = resultSet.getString("Nom");
            Double price = resultSet.getDouble("Prix");
            String Lieux = resultSet.getString("Lieux");
            String description = resultSet.getString("Programme");
            LocalDate dateD =resultSet.getDate("Date_Rand").toLocalDate();
            LocalDate date =resultSet.getDate("Date_Rand").toLocalDate();
            String imageUrl = resultSet.getString("ImagesR");
            int NbrP= resultSet.getInt("Nbr_PlaceR");
            Image image = new Image(imageUrl);
            
            // Create the product pane for each product
            VBox productBox = new VBox();
            productBox.setPrefWidth(250);
            productBox.setSpacing(10);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            Label nameLabel = new Label(name);
            Label lieuxLabel = new Label(Lieux);
            Label priceLabel = new Label("$" + price);
            Label descriptionLabel = new Label(description);
            Label dateLabel = new Label(String.valueOf(date));
            Button Participer =new Button("Participer");
            Participer.setOnAction(event -> {
    String attachmentPath = "/path/to/attachment/file.pdf";
    String from="rihem.drissi@esprit.tn";
    String pass="qnerkiajcikwckjj";
    String[] to = {"khemaiesdrissi@yahoo.fr"};
    String subject = "Participation Mail";
    String body = "<h1> Votre Nouvelle Participation </h1> <br/> <h2><b>Randonnee</b></h2> "+name+"<br/> <h2><b>Montant </b></h2>"+price+"<br/> <h2><b>Date </b></h2>"+dateD;
    
    sendFromGMail(from, pass, to, subject, body);
                // Enregistrement de la participation dans la base de données
               try{
                   int nbP = CalculerRep(Id_Client);
                   PreparedStatement resultGet = new MyConnection().cnx.prepareStatement(insert);
            String reference = "P"+nomUser.replaceAll("\\s+","") + "" + idS + "" +String.valueOf(Year.now()) ;
            resultGet.setString(1, reference);
                resultGet.setInt(2, Id_Client);
                resultGet.setInt(3, idS);
                resultGet.setInt(4, nbP);
                resultGet.setDouble(5, price);
                resultGet.setString(6, "En Attend");
                resultGet.setString(7, String.valueOf(LocalDate.now()));
                resultGet.setString(8, name);
            resultGet.executeUpdate();
            updateNb(idS,NbrP);
                       System.out.println("votre Participation a été ajouté");
               }  catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
            });
            productBox.getChildren().addAll(imageView, nameLabel, priceLabel, lieuxLabel,dateLabel,descriptionLabel,Participer);
            productPane.getChildren().add(productBox);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @FXML
    public void handleSearch(KeyEvent event) {
        KeyCode keyCode = event.getCode();
    if (keyCode == KeyCode.ENTER) {
        String searchTerm = searchField.getText();
        search(searchTerm);
    }
}
    public void initialize() {
        
//        Utilisateur user = new Utilisateur();
//        UtilisateurService us =new UtilisateurService();
//        user = (Utilisateur) UserSession.INSTANCE.get("user");
//        int Id_Client=user.getIdUtilisateur();
        /*el Id_Client Na7ehaa w 7ot el eli comment lfou9*/
    int Id_Client=1;
    
    
//        Utilisateur user = new Utilisateur();
//        UtilisateurService us =new UtilisateurService();
//        user = (Utilisateur) UserSession.INSTANCE.get("user");
//        String nomUser=user.getNomUtilisateur();
        /*el nomUser Na7ehaa w 7ot el eli comment lfou9*/
 String nomUser="User";
    // Load data from the database
                String insert = "INSERT INTO participation(RefP,id_Client,id_Camp, Nombre,Montant, Etat, date_Parti,Nom) VALUES (?,?,?,?,?,?,?,?)";

        try {
            Statement statement = new MyConnection().cnx.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM randonnee");
            // IdClient
            while (resultSet.next()) {
                int idS = resultSet.getInt("id_Randonnee");
                String name = resultSet.getString("Nom");
                Double price = resultSet.getDouble("Prix");
                String description = resultSet.getString("Programme");
                LocalDate dateD =resultSet.getDate("Date_Rand").toLocalDate();
                LocalDate date =resultSet.getDate("Date_Rand").toLocalDate();
                String Lieux = resultSet.getString("Lieux");
                int NbrP= resultSet.getInt("Nbr_PlaceR");
                String imageUrl = resultSet.getString("ImagesR");
                Image image = new Image(imageUrl);
                
                // Create the product pane for each product
                VBox productBox = new VBox();
                productBox.setPrefWidth(250);
                productBox.setSpacing(10);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                Label nameLabel = new Label(name);
                Label lieuxLabel = new Label(Lieux);
                Label priceLabel = new Label("$" + price);
                Label dateLabel = new Label(String.valueOf(date));
                Label descriptionLabel = new Label(description);
                Button Participer =new Button("Participer");
                Participer.setOnAction(event -> {
                    String attachmentPath = "/path/to/attachment/file.pdf";
    String from="rihem.drissi@esprit.tn";
    String pass="qnerkiajcikwckjj";
    String[] to = {"khemaiesdrissi@yahoo.fr"};
    String subject = "Participation Mail";
    String body = "<h1> Votre Nouvelle Participation</h1> <br/> <h2><b>Randonnee</b></h2> "+name+"<br/> <h2><b>Montant </b></h2>"+price+"<br/> <h2><b>Date </b></h2>"+dateD;
    
    sendFromGMail(from, pass, to, subject, body);
                    // Enregistrement de la participation dans la base de données
                   try{
                       int nbP = CalculerRep(Id_Client);
                       PreparedStatement resultGet = new MyConnection().cnx.prepareStatement(insert);
            String reference = "P"+nomUser.replaceAll("\\s+","") + "" + idS + "" +String.valueOf(Year.now()) ;
            resultGet.setString(1, reference);
                resultGet.setInt(2, Id_Client);
                resultGet.setInt(3, idS);
                resultGet.setInt(4, nbP);
                resultGet.setDouble(5, price);
                resultGet.setString(6, "En Attend");
                resultGet.setString(7, String.valueOf(LocalDate.now()));
                resultGet.setString(8, name);
                resultGet.executeUpdate();
                updateNb(idS,NbrP);
                           System.out.println("votre Participation a été ajouté");
                   }  catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
                });
                productBox.getChildren().addAll(imageView, nameLabel, priceLabel, lieuxLabel,descriptionLabel,Participer);
                productPane.getChildren().add(productBox);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int CalculerRep(int ID){
        
        int rowCount = 0;
        String rech ="SELECT COUNT(*) as num_rows FROM participation WHERE id_Client = ?";
try {
   PreparedStatement stmt = new MyConnection().cnx.prepareStatement(rech);
    stmt.setInt(1, ID); 
    
    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
        rowCount = rs.getInt("num_rows");
    }
    
    rs.close();
    stmt.close();
} catch (SQLException e) {
    e.printStackTrace();
}

return rowCount+1;
    }
    public void updateNb(int ID,int NBRP){
            String update= "UPDATE randonnee SET Nbr_PlaceR=? "+ "WHERE id_Randonnee=" + ID;
            try{
                PreparedStatement st = new MyConnection().cnx.prepareStatement(update);
            int Nbr = NBRP - 1;
            st.setInt(1, Nbr);
            st.executeUpdate();
            }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    @FXML
        void BackC(ActionEvent event) {
try {
            Parent root=FXMLLoader.load(getClass().getResource("Client.fxml"));
            Scene scene =new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RandonneeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
