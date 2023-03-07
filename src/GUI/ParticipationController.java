/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Participations;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Services.UserSession;
import Tools.MaConnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author rihem
 */
public class ParticipationController implements Initializable {
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

      @FXML
    private TableView<Participations> TableT;

  

    @FXML
    private TableColumn<Participations, Date> coldate;

    @FXML
    private TableColumn<Participations, Double> colmontant;

    @FXML
    private TableColumn<Participations, String> coletat;

    @FXML
    private TableColumn<Participations, String> colnome;

    @FXML
    private TableColumn<Participations, String> colreference;

    @FXML
    void pressedT(MouseEvent event) {

    }
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
    }    
    @FXML
        
    public ObservableList<Participations> getCamping(){
            Connection cnx = MaConnexion.getInstance().getCnx();

        Utilisateur user = new Utilisateur();
       UtilisateurService us =new UtilisateurService();
       user = (Utilisateur) UserSession.INSTANCE.get("user");
       int idUser=user.getIdUtilisateur();
                /*Na7i el comment*/

        ObservableList<Participations> Participation = FXCollections.observableArrayList();
        
        /*Fasakh Hedhi*/
       
        
        
       String query = "SELECT * FROM participations WHERE id_Client = ?";
        
        try{
PreparedStatement statement = cnx.prepareStatement(query);       
   statement.setInt(1, idUser);
   ResultSet res = statement.executeQuery();

        while(res.next()){
            Participations part = new Participations();
            part.setMontant(res.getDouble("Montant"));
            part.setNombre(res.getInt("Montant"));
            part.setDate_Parti(res.getDate("date_Parti").toLocalDate());
            part.setRefP(res.getString("RefP"));
            part.setEtat(res.getNString("Etat"));
            part.setNom(res.getString("Nom"));
            Participation.add(part);
        }
       } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return Participation;
    }
     int myIndex;
     String Ref;
    public void showCamping(){

        ObservableList<Participations> list = getCamping();
        TableT.setItems(list);
        coletat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date_Parti"));
        colnome.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colreference.setCellValueFactory(new PropertyValueFactory<>("RefP"));
        colmontant.setCellValueFactory(new PropertyValueFactory<>("Montant"));
        TableT.setRowFactory( tv -> {
		     TableRow<Participations> myRow = new TableRow<>();
		     myRow.setOnMouseClicked (event -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            myIndex =  TableT.getSelectionModel().getSelectedIndex();
		 
                  Ref = TableT.getItems().get(myIndex).getRefP() ;
                         
                           

		        }
		     });
		        return myRow;
         
         
    });
                }
        @FXML
    void delete(ActionEvent event) {
            Connection cnx = MaConnexion.getInstance().getCnx();

        String delete = "DELETE FROM participations where RefP = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(delete);
            st.setString(1, Ref);
            st.executeUpdate();
            showCamping();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
