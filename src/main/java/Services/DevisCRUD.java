/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import interfaces.InterfaceCRUD;
import entities.Devis;
import java.sql.Connection;
import utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.List;
import utils.MyConnection ; 

/**
 *
 * @author rihem
 */
public class DevisCRUD {
    
    //Connection cnx ; 
   /*  public DevisCRUD (){
       // cnx = MyConnection.getInstance().getConnexion();
    
}*/
  public void ajouterDev(Devis d){
        try {
            String requete = "INSERT INTO devis( Id_C, Id_Res, date_envoie, date_Validation, montant, Quantite, etat) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = new MyConnection().cnx.prepareStatement(requete);
            pst.setInt(1,d.getId_C());
            pst.setInt(2,d.getId_Res());
            pst.setDate(3, (java.sql.Date) d.getDate_envoie());
            pst.setDate(4, (java.sql.Date) d.getDate_Validation());
            pst.setDouble(5,d.getMontant());
            pst.setInt(6,d.getQuantited());
            pst.setString(7,d.getEtat());
            pst.executeUpdate();
            System.out.println("votre Devis a été ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   public ArrayList<Devis> afficherDev (){
        ArrayList<Devis> myList;
        myList = new ArrayList<>();
        
        try {
            String requte3 = "SELECT * FROM `pi`.`devis` ";
            Statement st = new MyConnection().cnx.createStatement();
            ResultSet res = st.executeQuery(requte3);
            while(res.next()){
                int Num_Devis = res.getInt(1);
                int Id_C = res.getInt("Id_C");
                int Id_Res = res.getInt("Id_Res");
                //Date date_envoie = res.getDate("date_envoie");
                //Date date_Validation = res.getDate("date_Validation");
                double montant =res.getDouble("montant");
                int quantited = res.getInt("Quantite");
                String etat = res.getString("etat");

                        
            

            Devis Dev = new Devis(Num_Devis,Id_C,Id_Res,null,null,montant,quantited,etat);
            myList.add(Dev);
            }
            System.out.println("votre Facture est");
         
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return myList;
    }
    public void modiferDev(Devis d,int id) {
        try {
            String req = "UPDATE devis SET Id_c=?, Id_Res=?, date_envoie=?, date_Validation=?, montant=?, etat=? "
                    + "WHERE Num_Devis="+id;
            PreparedStatement pst = new MyConnection().cnx.prepareStatement(req);
            pst.setInt(2, d.getId_C());
            pst.setInt(3, d.getId_Res());
            pst.setDate(4, (java.sql.Date) d.getDate_envoie());
            pst.setDate(4, (java.sql.Date) d.getDate_Validation());
            pst.setDouble(5, d.getMontant());
            pst.setString(6, d.getEtat());
            
            
            pst.executeUpdate();
            System.out.println("votre Facture a été modifiée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
  public void supprimerDev( int id ) {
           try {
            String req1 = "delete from devis where Num_Devis ="+id;
            PreparedStatement pst =new MyConnection().cnx.prepareStatement(req1);
           
            pst.executeUpdate();
            System.out.println("votre Devis " + id + " a été supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
    
    
    
    
    
    


}