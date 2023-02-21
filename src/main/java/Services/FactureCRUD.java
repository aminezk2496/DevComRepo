/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import interfaces.InterfaceCRUD;
import entities.Facture;
import java.sql.Connection;
import utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.MyConnection ; 


/**
 *
 * @author rihem
 */
public class FactureCRUD {
    
   // Connection cnx = MyConnection.getInstance().getConnexion(); 
    /*public FactureCRUD (){
        cnx = MyConnection.getInstance().getConnexion();
    
}*/
public void ajouterFact(Facture f){
        try {
            String requete = "INSERT INTO facture( Id_C, Id_Res, date_facture, Quantite, Prix_HT, etat) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = new MyConnection().cnx.prepareStatement(requete);
            pst.setInt(1,f.getId_C());
            pst.setInt(2,f.getId_Res());
            pst.setDate(3, (java.sql.Date) f.getDate_facture());
            pst.setInt(4, f.getQuantite());
            pst.setDouble(5, f.getPrix());
            pst.setString(6,f.getEtat());
            pst.executeUpdate();
            System.out.println("votre Facture a été ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 public ArrayList<Facture> afficherFact (){
        ArrayList<Facture> myList;
        myList = new ArrayList<>();
        
        try {
            String requte3 = "SELECT * FROM `pi`.`facture` ";
            Statement st = new MyConnection().cnx.createStatement();
            ResultSet res = st.executeQuery(requte3);
            while(res.next()){
                int Id_Facture = res.getInt(1);
                int Id_C = res.getInt("Id_C");
                int Id_Res = res.getInt("Id_Res");
                String etat = res.getString("etat");
                Date date_facture = res.getDate("date_facture");
                int Quantite = res.getInt("Quantite");
                double Prix = res.getInt("Prix");
            
            

            Facture Fact = new Facture(Id_Facture,Id_C, Id_Res, date_facture,Quantite,Prix, etat);
            myList.add(Fact);
            }
            System.out.println("votre Facture est");
         
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return myList;
    }
  public void modiferFact(Facture f,int id) {
        try {
            String req = "UPDATE facture SET Id_c=?,Id_Res=?,date_facture=?,Quantite=?,Prix_HT=?,etat=? "
                    + "WHERE Id_Facture="+id;
            PreparedStatement pst = new MyConnection().cnx.prepareStatement(req);
            pst.setInt(2, f.getId_C());
            pst.setInt(3, f.getId_Res());
            pst.setDate(4, (java.sql.Date) f.getDate_facture());
            pst.setInt(5, f.getQuantite());
            pst.setDouble(6, f.getPrix());
            pst.setString(5, f.getEtat());
            
            
            pst.executeUpdate();
            System.out.println("votre Facture a été modifiée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
  public void supprimerFact( int id ) {
           try {
            String req1 = "delete from facture where Id_Facture ="+id;
            PreparedStatement pst =new MyConnection().cnx.prepareStatement(req1);
           
            pst.executeUpdate();
            System.out.println("votre Facture " + id + " a été supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
    
    
    
    
    
    


}