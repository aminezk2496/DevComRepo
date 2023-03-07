/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Entities.location;
import Services.LocationServices;
import Services.UserSession;
import Services.UtilisateurService;
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
 * @author ASUS
 */
public class FXMLRESERVATIONADMINController implements Initializable {
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
   
        Utilisateur user = new Utilisateur();
       
  @FXML
    private TableColumn<location, String> heureColmn;

    @FXML
    private TableColumn<location, String> userColmn;
    @FXML
    private TableColumn<location, String> lieuColmn;

    @FXML
    private TableColumn<location, String> nomColmn;

    @FXML
    private TableView<location> table;

    @FXML
    private TableColumn<location, String> telColmn;

    @FXML
    private TableColumn<location, String> typeColmn;
    /**
     * Initializes the controller class.
     */
    public void table()
      {
          Connect();
          ObservableList<location> locations = FXCollections.observableArrayList();
       try 
       {
           pst = cnx.prepareStatement("select id_location,nom_location,type_location,lieu_location,numtel_location,heure_location,userid from location ");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            location st = new location();
            st.setId(rs.getInt("id_location"));
            st.setNom(rs.getString("nom_location"));
            st.setType(rs.getString("type_location"));
            st.setHeure(rs.getString("heure_location"));
            st.setLieu(rs.getString("lieu_location"));
            st.setNum(rs.getInt("numtel_location"));
            st.setUserid(rs.getInt("userid"));

              
            locations.add(st);
       }
    } 
                table.setItems(locations);
                //idColmn.setCellValueFactory(new PropertyValueFactory<location, String>("id"));
                nomColmn.setCellValueFactory(new PropertyValueFactory<location, String>("nom"));
                typeColmn.setCellValueFactory(new PropertyValueFactory<location, String>("type"));
                telColmn.setCellValueFactory(new PropertyValueFactory<location, String>("num"));
                heureColmn.setCellValueFactory(new PropertyValueFactory<location, String>("heure"));
                 lieuColmn.setCellValueFactory(new PropertyValueFactory<location, String>("lieu"));
                userColmn.setCellValueFactory(new PropertyValueFactory<location, String>("userid"));

                
               

       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
        /* FilteredList<location> filteredData = new FilteredList<>(locations, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (location.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getDepartment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<location> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);*/

                
    
    
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
        // TODO
        Connect();
        table();
    }   
    Connection cnx;
    PreparedStatement pst;
    int myIndex;
    int id;
    
     public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/discovertn","root","");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
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
