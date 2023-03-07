/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;
import javafx.scene.input.KeyEvent;
import Entities.Camping;
import Entities.Utilisateur;
import static Services.JavaMail.sendFromGMail;
import Services.UserSession;
import Services.UtilisateurService;
import Tools.MaConnexion;
import java.io.File;
       
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
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
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rihem
 */

 public class ClientCampController {
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
     */
    @FXML
    private FlowPane productPane;    
    
    @FXML
    private TextField searchField;
    
    @FXML
public void search(String searchTerm) {
                            Connection cnx = MaConnexion.getInstance().getCnx();

    
        Utilisateur user = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        user = (Utilisateur) UserSession.INSTANCE.get("user");
        int Id_Client=user.getIdUtilisateur();
        /*el Id_Client Na7ehaa w 7ot el eli comment lfou9*/
//    int Id_Client=1;
    
    
      
        String nomUser=user.getNomUtilisateur();
        /*el nomUser Na7ehaa w 7ot el eli comment lfou9*/
        // String nomUser="User";

    // Load data from the database
    String insert = "INSERT INTO participations(RefP,id_Client,id_Camp, Nombre,Montant, Etat, date_Parti,Nom) VALUES (?,?,?,?,?,?,?,?)";
    String query = "SELECT * FROM Camping WHERE LOWER(Lieux) LIKE ?";

    try {
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, "%" + searchTerm.toLowerCase() + "%");
        ResultSet resultSet = statement.executeQuery();
        
        // Clear existing product panes
        productPane.getChildren().clear();
        
        // IdClient
        while (resultSet.next()) {
            int idC = resultSet.getInt("id_Camping");
            String name = resultSet.getString("Nom");
            Double price = resultSet.getDouble("Prix");
            String Lieux = resultSet.getString("Lieux");
            int NbrP= resultSet.getInt("Nbr_PlaceC");
            String description = resultSet.getString("Description");
            LocalDate dateD =resultSet.getDate("date_Debut").toLocalDate();
            LocalDate dateF =resultSet.getDate("date_Fin").toLocalDate();
            String imageUrl = resultSet.getString("ImageC");
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
            Label NombreLabel = new Label(String.valueOf(NbrP));
            Label priceLabel = new Label("$" + price);
            Label dateDebutLabel = new Label(String.valueOf(dateD));
            Label dateFinLabel = new Label(String.valueOf(dateF));
            Label descriptionLabel = new Label(description);
            Button Participer =new Button("Participer");
            Participer.setOnAction(event -> {
                String attachmentPath = "/path/to/attachment/file.pdf";
    String from="rihem.drissi@esprit.tn";
    String pass="223JFT5378";
    String[] to = {"rihem.dsi@gmail.com"};
    String subject = "Participation Mail";
    String body = "<h1> Votre Nouvelle Participation </h1> <br/> <h2><b>Camping </b></h2> "+name+"<br/> <h2><b>Montant </b></h2>"+price+"<br/> <h2><b>Date </b></h2>"+dateD;
    
    sendFromGMail(from, pass, to, subject, body);
                // Enregistrement de la participation dans la base de données
               try{
                   int nbP = CalculerRep(Id_Client);
                   PreparedStatement resultGet =cnx.prepareStatement(insert);
            String reference = "P"+nomUser.replaceAll("\\s+","") + "" + idC + "" +String.valueOf(Year.now()) ;
            resultGet.setString(1, reference);
                resultGet.setInt(2, Id_Client);
                resultGet.setInt(3, idC);
                resultGet.setInt(4, nbP);
                resultGet.setDouble(5, price);
                resultGet.setString(6, "En Attend");
                resultGet.setString(7, String.valueOf(LocalDate.now()));
                resultGet.setString(8, name);
            resultGet.executeUpdate();
            updateNb(idC,NbrP);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("participation ajouté !");
        alert.showAndWait();
            
                       System.out.println("votre Participation a été ajouté");
               }  catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
            });
            productBox.getChildren().addAll(imageView, nameLabel, priceLabel, lieuxLabel,dateDebutLabel,dateFinLabel,descriptionLabel,NombreLabel,Participer);
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

                                Connection cnx = MaConnexion.getInstance().getCnx();

        
        Utilisateur user = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        user = (Utilisateur) UserSession.INSTANCE.get("user");
        int Id_Client=user.getIdUtilisateur();
        /*el Id_Client Na7ehaa w 7ot el eli comment lfou9*/
    //int Id_Client=1;
    
    
//        Utilisateur user = new Utilisateur();
//        UtilisateurService us =new UtilisateurService();
//        user = (Utilisateur) UserSession.INSTANCE.get("user");
       String nomUser=user.getNomUtilisateur();
        /*el nomUser Na7ehaa w 7ot el eli comment lfou9*/
 //String nomUser="User";
    
// Load data from the database
                String insert = "INSERT INTO participations(RefP,id_Client,id_Camp, Nombre,Montant, Etat, date_Parti,Nom) VALUES (?,?,?,?,?,?,?,?)";

        try {
            Statement statement =cnx.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Camping");
            // IdClient
            while (resultSet.next()) {
                int idC = resultSet.getInt("id_Camping");
                String name = resultSet.getString("Nom");
                Double price = resultSet.getDouble("Prix");
                String description = resultSet.getString("Description");
                String Lieux = resultSet.getString("Lieux");
                int NbrP= resultSet.getInt("Nbr_PlaceC");
                LocalDate dateD =resultSet.getDate("date_Debut").toLocalDate();
                LocalDate dateF =resultSet.getDate("date_Fin").toLocalDate();
                String imageUrl = resultSet.getString("ImageC");
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
                Label NombreLabel = new Label(String.valueOf(NbrP));
                Label dateDebutLabel = new Label(String.valueOf(dateD));
                 Label dateFinLabel = new Label(String.valueOf(dateF));
                Label priceLabel = new Label("$" + price);
                Label descriptionLabel = new Label(description);
                Button Participer =new Button("Participer");
                Participer.setOnAction(event -> {
                    String attachmentPath = "/path/to/attachment/file.pdf";
    String from="rihem.drissi@esprit.tn";
    String pass="223JFT5378";
    String[] to = {"rihem.dsi@gmail.com"};
    String subject = "Participation Mail";
    String body = "<h1> Votre Nouvelle Participation </h1> <br/> <h2><b>Camping </b></h2> "+name+"<br/> <h2><b>Montant </b></h2>"+price+"<br/> <h2><b>Date </b></h2>"+dateD;
    
    sendFromGMail(from, pass, to, subject, body);
                    // Enregistrement de la participation dans la base de données
                   try{
                       int nbP = CalculerRep(Id_Client);
                       PreparedStatement resultGet =cnx.prepareStatement(insert);
                String reference = "P"+nomUser.replaceAll("\\s+","") + "" + idC + "" +String.valueOf(Year.now()) ;
            resultGet.setString(1, reference);
                resultGet.setInt(2, Id_Client);
                resultGet.setInt(3, idC);
                resultGet.setInt(4, nbP);
                resultGet.setDouble(5, price);
                resultGet.setString(6, "En Attend");
                resultGet.setString(7, String.valueOf(LocalDate.now()));
                resultGet.setString(8, name);
                
                resultGet.executeUpdate();
                updateNb(idC,NbrP);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("participation ajouté !");
        alert.showAndWait();
                           System.out.println("votre Participation a été ajouté");
                   }  catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
                });
                productBox.getChildren().addAll(imageView, nameLabel, priceLabel, lieuxLabel,dateDebutLabel,dateFinLabel,descriptionLabel,NombreLabel,Participer);
                productPane.getChildren().add(productBox);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
      

}
    public void updateNb(int ID,int NBRP){
                                Connection cnx = MaConnexion.getInstance().getCnx();

            String update= "UPDATE camping SET Nbr_PlaceC=? "+ "WHERE id_Camping=" + ID;
            try{
                PreparedStatement st = cnx.prepareStatement(update);
            int Nbr = NBRP - 1;
            st.setInt(1, Nbr);
            st.executeUpdate();
            }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public int CalculerRep(int ID){
                                Connection cnx = MaConnexion.getInstance().getCnx();

        
        int rowCount = 0;
        String rech ="SELECT COUNT(*) as num_rows FROM participations WHERE id_Client = ?";
try {
   PreparedStatement stmt = cnx.prepareStatement(rech);
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
   @FXML
    void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
