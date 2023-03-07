/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication1;
import com.mysql.cj.Session;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.PasswordAuthentication;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btnExcel;
    @FXML
    private Button btnAdd;
@FXML
    private TextField searchField;

    @FXML
    private Button triNom;

    @FXML
    private Button triType;

    @FXML
    private Button triHeure;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<location, String> heureColmn;

    @FXML
    private Label label;

    @FXML
    private TableColumn<location, String> lieuColmn;

    @FXML
    private TableColumn<location, String> nomColmn;

    @FXML
    private TableView<location> table;

    @FXML
    private TableColumn<location, String> telColmn;

    @FXML
    private TextField txtHeure;

    @FXML
    private TextField txtLieu;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private Button btnMail;
    @FXML
    private TextField txtType;

    @FXML
    private TableColumn<location, String> typeColmn;
@FXML
    void sortHeure(ActionEvent event) {

    }

    @FXML
    void sortName(ActionEvent event) {

    }

    @FXML
    void sortType(ActionEvent event) {

    }
   
   
    @FXML
    void Add(ActionEvent event) {
 String name,type,heure,lieu;
 int tel;
 int userid;
 
            name = txtName.getText();
            type = txtType.getText();
            heure = txtHeure.getText();
            lieu = txtLieu.getText();
            tel = Integer.parseInt(txtTel.getText());
            userid=1;
location l = new location(id,name,type,lieu,heure,tel,userid);
 LocationServices pservice= new LocationServices();
            pservice.ajouterLocation(l);
            
    table();
            //txtId.setText("");
            txtName.setText("");
            txtType.setText("");
            txtHeure.setText("");
            txtTel.setText("");
            txtLieu.setText("");
            txtName.requestFocus();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    // Send the SMS message using the Twilio API
    Message sms = Message.creator(new PhoneNumber("+21654169937"), // the recipient's phone number
                                   new PhoneNumber("+15672921420"), // your Twilio phone number
                                   "reservation effectuée") // the message to send
                        .create();
    System.out.println("SMS sent: " + sms.getSid());/*
            
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
public void table()
      {
          Connect();
          ObservableList<location> locations = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("select id_location,nom_location,type_location,lieu_location,numtel_location,heure_location,userid from location ");  
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
		     TableRow<location> myRow = new TableRow<>();
		     myRow.setOnMouseClicked (event -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            myIndex =  table.getSelectionModel().getSelectedIndex();
		 
		          //txtId.setText(table.getItems().get(myIndex).getId());
		           txtName.setText(table.getItems().get(myIndex).getNom());
		           txtType.setText(table.getItems().get(myIndex).getType());
                            txtHeure.setText(table.getItems().get(myIndex).getHeure());
                           txtLieu.setText(table.getItems().get(myIndex).getLieu());
           txtTel.setText(table.getItems().get(myIndex).getNum() + "");
                         
                           
		        }
		     });
		        return myRow;
                   });
    
    
      }
    @FXML
    void Delete(ActionEvent event) {
myIndex = table.getSelectionModel().getSelectedIndex();
String lieu, nom;
        lieu = txtLieu.getText();
                nom = txtName.getText();

                    
 
        try
        {
            pst = con.prepareStatement("delete from location where nom_location = ? and lieu_location = ? ");
            pst.setString(1, nom);
                        pst.setString(2, lieu);

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
String name,type,heure,lieu,prix;
int tel;
       
         myIndex = table.getSelectionModel().getSelectedIndex();
      //  idd = txtId.getText();
String telText = txtTel.getText();
 tel = Integer.parseInt(telText);
            name = txtName.getText();
type = txtType.getText();
            heure = txtHeure.getText();
            lieu = txtLieu.getText();
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
        }
    }
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
final String ACCOUNT_SID = "ACe2213c68e9a45242569ff357a7775dcb";
    final String AUTH_TOKEN = "17e748d166abbe05aeb858f4560c9ef9";   
    
     @FXML
    void sendMail(ActionEvent event) {
     Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("reservation.pdf"));
        document.open();

        // Connect to the database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/espritpidev", "root", "");

        // Prepare the SQL statement
        String sql = "SELECT * FROM location";
        stmt = conn.prepareStatement(sql);

        // Execute the SQL statement
        rs = stmt.executeQuery();

        // Write the data to the PDF file
        while (rs.next()) {
            String data = rs.getString("nom_location") + " " + rs.getString("type_location") + " " + rs.getString("lieu_location") + " " + rs.getString("numtel_location") + " " + rs.getString("heure_location");
            document.add(new Paragraph(data));
        }

        document.close();
    } catch (DocumentException | FileNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the database connection and resources
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    }
    
    @FXML
    void generateExcel(ActionEvent event) {
Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Workbook workbook = new XSSFWorkbook();
    try {
        // Connect to the database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/espritpidev", "root", "");

        // Prepare the SQL statement
        String sql = "SELECT * FROM location";
        stmt = conn.prepareStatement(sql);

        // Execute the SQL statement
        rs = stmt.executeQuery();

        // Create a new sheet in the Excel workbook
        Sheet sheet = workbook.createSheet();

        // Write the data to the Excel sheet
        int rownum = 0;
        while (rs.next()) {
            Row row = sheet.createRow(rownum++);
            int cellnum = 0;
            Cell cell1 = row.createCell(cellnum++);
            cell1.setCellValue(rs.getString("nom_location"));
            Cell cell2 = row.createCell(cellnum++);
            cell2.setCellValue(rs.getString("type_location"));
            Cell cell3 = row.createCell(cellnum++);
            cell3.setCellValue(rs.getString("lieu_location"));
            Cell cell4 = row.createCell(cellnum++);
            cell4.setCellValue(rs.getString("heure_location"));
            Cell cell5 = row.createCell(cellnum++);
            cell5.setCellValue(rs.getString("numtel_location"));
            // add more cells for each column you want to include
        }

        // Write the Excel workbook to the output file
        FileOutputStream out = new FileOutputStream("reservations.xlsx");
        workbook.write(out);
        out.close();
        workbook.close();
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    } finally {
        // Close the database connection and resources
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
    
    
    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Connect();
       table();
       
    }    
    
}
