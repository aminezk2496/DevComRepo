/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

//import java.io.IOException;
//import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.DatePicker;
import com.gluonhq.charm.glisten.control.TextField;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Result;
import com.oracle.javafx.scenebuilder.kit.editor.panel.inspector.editors.IntegerField;

import entities.Facture;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import services.FactureCRUD;
import utils.MyConnection;
/**
 * FXML Controller class
 *
 * @author rihem
 */
public class FacturesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML

     private TableColumn<Facture, Integer> Id_C;

    @FXML

    private TableColumn<Facture, Integer> Id_Facture;

    @FXML

    private TableColumn<Facture, Integer> Id_Res;

    @FXML

    private TableColumn<Facture, Date> date_fact;

    @FXML

    private TableColumn<Facture, String> etat;

    @FXML

    private TableColumn<Facture, Double> prix;

    @FXML

    private TableColumn<Facture, Integer> quantie;

    @FXML

    private TableView<Facture> table_facture;

    @FXML
    private DatePicker TDate_Facture;

    @FXML
    private TextField TEtat;

    @FXML
    private TextField TId_Client;

    @FXML
    private TextField TId_Reser;

    @FXML
    private TextField TPrix;

    @FXML
    private TextField TQuantite;

    ArrayList<Facture> listeF;

    int index = -1;

    Connection cnx = null;
    Resultset rs = null;
    PreparableStatement pst = null;


    public void AddFacture( ){
        try {
            String requete = "INSERT INTO facture( Id_C, Id_Res, date_facture, Quantite, Prix_HT, etat) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = new MyConnection().cnx.prepareStatement(requete);
            pst.setInt(1,Integer.parseInt(TId_Client.getText()));
            pst.setInt(2,Integer.parseInt(TId_Reser.getText()));
            pst.setDate(3, (java.sql.Date) TDate_Facture.getValue());
            pst.setInt(4, Integer.parseInt(TQuantite.getText()));
            pst.setDouble(5,Integer.parseInt(TPrix.getText()));
            pst.setString(6,TEtat.getText());
            pst.executeUpdate();
            System.out.println("votre Facture a été ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Id_Facture.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("ID_Facture"));
        Id_C.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("ID_Client"));
        Id_Res.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("ID_Reservation"));
        date_fact.setCellValueFactory(new PropertyValueFactory<Facture,Date>("Date_Facture"));
        quantie.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("Quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<Facture,Double>("Prix_HT"));
        etat.setCellValueFactory(new PropertyValueFactory<Facture,String>("Etat"));
        FactureCRUD  listeF = new FactureCRUD();

        listeF.afficherFact();
    }    
    /*private Scene scene;
    private BorderPane root;

    public FactureController() throws MalformedURLException, IOException {

        FXMLLoader loader = new FXMLLoader(FactureController.class.getResource("Sample.fxml"));
        loader.setController(this);
        root = loader.load();

        scene = new Scene(root);
    }*/
    
}