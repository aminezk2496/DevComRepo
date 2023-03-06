/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Participation;
import Entities.Utilisateur;
import Services.UtilisateurService;
import Services.UserSession;
import Utils.MyConnection;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author rihem
 */
public class ParticipationController implements Initializable {

      @FXML
    private TableView<Participation> TableT;

  

    @FXML
    private TableColumn<Participation, Date> coldate;

    @FXML
    private TableColumn<Participation, Double> colmontant;

    @FXML
    private TableColumn<Participation, String> coletat;

    @FXML
    private TableColumn<Participation, String> colnome;

    @FXML
    private TableColumn<Participation, String> colreference;

    @FXML
    void pressedT(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 showCamping();
    }    
    @FXML
        
    public ObservableList<Participation> getCamping(){
//        Utilisateur user = new Utilisateur();
//        UtilisateurService us =new UtilisateurService();
//        user = (Utilisateur) UserSession.INSTANCE.get("user");
//        int idUser=user.getIdUtilisateur();

        ObservableList<Participation> Participation = FXCollections.observableArrayList();
        
        /*Fasakh Hedhi*/
        String query = "SELECT * FROM participation";
        
        
       //String query = "SELECT * FROM participation WHERE LOWER(id_Client) LIKE ?";
        
        try{
        Statement st = new MyConnection().cnx.createStatement();
        ResultSet res = st.executeQuery(query);
//      res.setInt(1, "%" + idUser.toLowerCase() + "%");
        while(res.next()){
            Participation part = new Participation();
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

        ObservableList<Participation> list = getCamping();
        TableT.setItems(list);
        coletat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date_Parti"));
        colnome.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colreference.setCellValueFactory(new PropertyValueFactory<>("RefP"));
        colmontant.setCellValueFactory(new PropertyValueFactory<>("Montant"));
        TableT.setRowFactory( tv -> {
		     TableRow<Participation> myRow = new TableRow<>();
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
        String delete = "DELETE FROM participation where RefP = ?";
        try {
            PreparedStatement st = new MyConnection().cnx.prepareStatement(delete);
            st.setString(1, Ref);
            st.executeUpdate();
            showCamping();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
}
