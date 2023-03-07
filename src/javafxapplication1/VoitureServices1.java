/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import java.sql.*;
import javafx.scene.control.Alert;
/**
 *
 * @author allal
 */
public class VoitureServices1 implements IvoitureCRUD {
     Connection myConn = myConnection.getInstance().getConnection();
    
    
     @Override
    public void ajouterVoiture(voiture p) {
        try {
            String sql="insert into voiture values(null,?,?,?,?)"; 
            PreparedStatement ste= myConn.prepareStatement(sql);
                
            ste.setString(1,p.getSerie());
            ste.setString(2,p.getMarque());
            ste.setInt(3,p.getNb_chv());
            ste.setString(4,p.getType());
            
            ste.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");

		
		alert.setHeaderText("ajout de la voiture");
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
    public void modifierVoiture(voiture p) {
         try {
       pst = con.prepareStatement("update location set nom_location = ?,type_location = ? ,duree_location = ?,lieu_location =?,description_location = ? ,prix_location = ? where id_location = ? ");
         //   pst.setString(1, p.getNom());
            pst.setString(2, p.getType());
           // pst.setString(3, p.getDuree());
          //  pst.setString(4, p.getLieu());
           // pst.setString(5, p.getDescription());
           // pst.setString(6, p.getPrix());
             pst.setInt(7, p.getId());
            pst.executeUpdate();
         
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

   // @Override
    public void supprimerVoiture(voiture p) {
          try {
            String sql="delete from location where id=? )"; 
            PreparedStatement ste= myConn.prepareStatement(sql);
            ste.setInt(1,p.getId());
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

   // @Override
    public void afficherVoiture() {
        
    }

    
}
