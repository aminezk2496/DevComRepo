/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.location;
import Tools.MaConnexion;
import java.sql.*;
import javafx.scene.control.Alert;
/**
 *
 * @author allal
 */
public class LocationServices implements IlocationCRUD {
     //Connection myConn = myConnection.getInstance().getConnection();
        Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterLocation(location p) {
        try {
            String sql="insert into location values(null,?,?,?,?,?,?)"; 
            PreparedStatement ste= cnx.prepareStatement(sql);
                
            ste.setString(1,p.getNom());
            ste.setString(2,p.getType());
            ste.setString(3,p.getLieu());
            ste.setString(5,p.getHeure());
            ste.setInt(4,p.getNum());
            ste.setInt(6,p.getUserid());
            ste.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");

		
		alert.setHeaderText("ajout de la location");
		alert.setContentText("Ajouté avec succés!");

		alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
Connection con;
    PreparedStatement pst;
    int myIndex;
    
    @Override
    public void modifierLocation(location p) {
         try {
       pst = con.prepareStatement("update location set nom_location = ?,type_location = ? ,duree_location = ?,lieu_location =?,description_location = ? ,prix_location = ? where id_location = ? ");
            pst.setString(1, p.getNom());
            pst.setString(2, p.getType());
           // pst.setString(3, p.getDuree());
            pst.setString(4, p.getLieu());
           // pst.setString(5, p.getDescription());
           // pst.setString(6, p.getPrix());
             pst.setInt(7, p.getId());
            pst.executeUpdate();
         
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

   // @Override
    public void supprimerLocation(location p) {
          try {
            String sql="delete from location where id=? )"; 
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setInt(1,p.getId());
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void afficherLocation() {
        
    }
    
}
