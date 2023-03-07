package Services;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Services;
//import Entities.Camping;
//import Utils.MyConnection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Date;
//
///**
// *
// * @author rihem
// */
//public class CampingCRUD {
//    
//    public void ajouterCamp(Camping c){
//        try {
//            String requete = "INSERT INTO camping( id_Camping, Nom, date_Debut, date_Fin, Periode, Prix, Lieux, Description) VALUES (?,?,?,?,?,?,?,?)";
//            PreparedStatement pst = new MyConnection().cnx.prepareStatement(requete);
//            pst.setInt(1,0);
//            pst.setString(2,c.getNom());
//            pst.setDate(3, (java.sql.Date) c.getDate_Debut());
//            pst.setDate(4, (java.sql.Date) c.getDate_Fin());
//            pst.setInt(5, c.getPeriode());
//            pst.setDouble(6, c.getPrix());
//            pst.setString(7,c.getLieux());
//            pst.setString(8, c.getDescription());
//            pst.executeUpdate();
//            System.out.println("votre Camping a été ajouté");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
//// public ArrayList<Camping> afficherCamp (){
////        ArrayList<Camping> myList;
////        myList = new ArrayList<>();
////        
////        try {
////            String requte3 = "SELECT * FROM `sortie`.`camping` ";
////            Statement st = new MyConnection().cnx.createStatement();
////            ResultSet res = st.executeQuery(requte3);
////            while(res.next()){
////                int id_Camping = res.getInt("id_Camping");
////                String Nom = res.getString("Nom");
////                String Lieux = res.getString("Lieux");
////                Date date_Debut = res.getDate("date_Debut");
////                Date date_Fin = res.getDate("date_Fin");
////                int Periode = res.getInt("Periode");
////                double Prix = res.getInt("Prix");
////                String Description = res.getString("Description");
////            
////            
////
////            Camping Camp = new Camping(id_Camping, Nom, date_Debut, date_Fin, Periode, Prix, Lieux, Description);
////            myList.add(Camp);
////            System.out.println("votre Camping est "+ myList);
////            }         
////        } catch (SQLException ex) {
////            System.err.println(ex.getMessage());
////        }
////        
////        return myList;
////    }
//  public void modiferCamp(Camping c,int id) {
//        try {
//            String req = "UPDATE camping SET Nom=?, date_Debut=?, date_Fin=?, Periode=?, Prix=?, Lieux=?, Description=? "
//                    + "WHERE id_Camping="+id;
//            PreparedStatement pst = new MyConnection().cnx.prepareStatement(req);
//            pst.setString(1, c.getNom());
//            pst.setDate(2, (java.sql.Date) c.getDate_Debut());
//            pst.setDate(3, (java.sql.Date) c.getDate_Fin());
//            pst.setInt(4, c.getPeriode());
//            pst.setDouble(5, c.getPrix());
//            pst.setString(6, c.getLieux());
//            pst.setString(7, c.getDescription());
//            
//            
//            pst.executeUpdate();
//            System.out.println("votre Camping a été modifiée");
//            
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        
//    }
//  public void supprimerCamp( int id ) {
//           try {
//            String req1 = "delete from camping where id_Camping ="+id;
//            PreparedStatement pst =new MyConnection().cnx.prepareStatement(req1);
//           
//            pst.executeUpdate();
//            System.out.println("votre Camping " + id + " a été supprimé");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        
//        
//    }
//    
//    
//    
//}
