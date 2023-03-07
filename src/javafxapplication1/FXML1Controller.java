/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

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

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXML1Controller implements Initializable {
  @FXML
    private Button btnAdd;
@FXML
    private TableColumn<voiture, String> chvColmn;

    @FXML
    private TableColumn<voiture, String> marqueColmn;

    @FXML
    private TableColumn<voiture, String> serieColmn;
    @FXML
    private TableColumn<voiture, String> typeColmn;
    @FXML
    private Button btnDelete;
 @FXML
    private TableView<voiture> table;
    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtChv;

    @FXML
    private TextField txtMarque;

    @FXML
    private TextField txtSerie;

    @FXML
    private TextField txtType;
    

    @FXML
    void Add(ActionEvent event) {
String serie,marque;
 int chv;
 
            serie = txtSerie.getText();
String type =  txtType.getText();
            marque = txtMarque.getText();
            chv = Integer.parseInt(txtChv.getText());
voiture l = new voiture(chv,serie,marque,chv,type);
 VoitureServices1 pservice= new VoitureServices1();
            pservice.ajouterVoiture(l);
            
    table();
            //txtId.setText("");
            txtSerie.setText("");
           txtType.setText("");
            txtMarque.setText("");
            txtChv.setText("");
           // txtLieu.setText("");
            txtSerie.requestFocus();/*
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
public void table()
      {
          Connect();
          ObservableList<voiture> voitures = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("select id,serie,marque,nb_chv,type from voiture ");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            voiture st = new voiture();
            st.setId(rs.getInt("id"));
            st.setSerie(rs.getString("serie"));
            st.setType(rs.getString("marque"));
            st.setNb_chv(rs.getInt("nb_chv"));
            st.setType(rs.getString("type"));
            

              
            voitures.add(st);
       }
    } 
                table.setItems(voitures);
                //idColmn.setCellValueFactory(new PropertyValueFactory<location, String>("id"));
                serieColmn.setCellValueFactory(new PropertyValueFactory<voiture, String>("serie"));
                marqueColmn.setCellValueFactory(new PropertyValueFactory<voiture, String>("marque"));
                chvColmn.setCellValueFactory(new PropertyValueFactory<voiture, String>("nb_chv"));
                typeColmn.setCellValueFactory(new PropertyValueFactory<voiture, String>("type"));
                
               

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

                table.setRowFactory( tv -> {
		     TableRow<voiture> myRow = new TableRow<>();
		     myRow.setOnMouseClicked (event -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            myIndex =  table.getSelectionModel().getSelectedIndex();
		 
		          //txtId.setText(table.getItems().get(myIndex).getId());
		           txtMarque.setText(table.getItems().get(myIndex).getMarque());
		           txtType.setText(table.getItems().get(myIndex).getType());
                            txtSerie.setText(table.getItems().get(myIndex).getSerie());
                          
           txtChv.setText(table.getItems().get(myIndex).getNb_chv() + "");
                         
                           
		        }
		     });
		        return myRow;
                   });
    
    
      }
    @FXML
    void Delete(ActionEvent event) {
myIndex = table.getSelectionModel().getSelectedIndex();
String serie;
        serie = txtSerie.getText();

                    
 
        try
        {
            pst = con.prepareStatement("delete from voiture where serie = ?  ");
            pst.setString(1, serie);
                      

            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("deleting");
 
alert.setHeaderText("deleting");
alert.setContentText("Deletedd!");
 
alert.showAndWait();
                  table();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Update(ActionEvent event) {
/*String name,type,marque;
int tel;
       
         myIndex = table.getSelectionModel().getSelectedIndex();
      //  idd = txtId.getText();
String serie = txtSerie.getText();
 int chv = Integer.parseInt(txtChv.getText());
            marque = txtMarque.getText();
type = txtType.getValue();
           
        try
        {
            pst = con.prepareStatement("update location set nom_location = ?,type_location = ? ,heure_location = ?,lieu_location =? ,numtel_location = ? where numtel_location = ? ");
            pst.setString(1, name);
            pst.setString(2, type);
            pst.setString(3, heure);
            pst.setString(4, lieu);
            
            pst.setInt(5, tel);
             pst.setInt(6, tel);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("modification");
 
alert.setHeaderText("modification");
alert.setContentText("modifié!");
 
alert.showAndWait();
                table();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
       table();
       // txtType.setItems(FXCollections.observableArrayList("comfort","normal"));
        
    }    
     Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
     public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/espritpidev","root","");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
