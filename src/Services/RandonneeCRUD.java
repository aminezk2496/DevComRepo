///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Services;
//import Entities.Randonnee;
//import Utils.MyConnection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Date;
///**
// *
// * @author rihem
// */
//public class RandonneeCRUD {
//    
//     public void ajouterRand(Randonnee r){
//        try {
//            String requete = "INSERT INTO randonnee(id_Randonnee, Nom, Date_Rand, Lieux, Prix, Niveau_diff, Programme) VALUES (?,?,?,?,?,?,?)";
//            PreparedStatement pst = new MyConnection().cnx.prepareStatement(requete);
//            pst.setInt(1,r.getId_Randonnee());
//            pst.setString(2,r.getNom());
//            pst.setDate(3, (java.sql.Date) r.getDate_Rand());
//            pst.setString(4, r.getLieux());
//            pst.setDouble(5,r.getPrix());
//            pst.setString(6, r.getNiveau_diff());
//            pst.setString(7,r.getProgramme());
//            pst.executeUpdate();
//            System.out.println("votre Randonnee a été ajouté");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
// public ArrayList<Randonnee> afficherCamp (){
//        ArrayList<Randonnee> myList;
//        myList = new ArrayList<>();
//        
//        try {
//            String requte3 = "SELECT * FROM `sortie`.`randonnee` ";
//            Statement st = new MyConnection().cnx.createStatement();
//            ResultSet res = st.executeQuery(requte3);
//            while(res.next()){
//                int id_Randonnee = res.getInt("id_Randonnee");
//                String Nom = res.getString("Nom");
//                String Lieux = res.getString("Lieux");
//                Date Date_Rand = res.getDate("Date_Rand");
//                String Niveau_diff = res.getString("Niveau_diff");
//                double Prix = res.getInt("Prix");
//                String Programme = res.getString("Programme");
//            
//
//            Randonnee Rand = new Randonnee(id_Randonnee, Nom, Date_Rand, Lieux, Prix, Niveau_diff, Programme);
//            myList.add(Rand);
//            System.out.println("votre Randonnee est "+ myList);
//            }
//         
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        
//        return myList;
//    }
//  public void modiferCamp(Randonnee r,int id) {
//        try {
//            String req = "UPDATE randonnee SET Nom=?, Date_Rand=?, Lieux=?, Prix=?, Niveau_diff=?, Programme=? "
//                    + "WHERE id_Randonnee="+id;
//            PreparedStatement pst = new MyConnection().cnx.prepareStatement(req);
//            pst.setString(1, r.getNom());
//            pst.setDate(2, (java.sql.Date) r.getDate_Rand());
//            pst.setString(3, r.getLieux());
//            pst.setDouble(4, r.getPrix());
//            pst.setString(5, r.getNiveau_diff());
//            pst.setString(6, r.getProgramme());
//            
//            
//            pst.executeUpdate();
//            System.out.println("votre Randonnee a été modifiée");
//            
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        
//    }
//  public void supprimerCamp( int id ) {
//           try {
//            String req1 = "delete from randonnee where id_Camping ="+id;
//            PreparedStatement pst =new MyConnection().cnx.prepareStatement(req1);
//           
//            pst.executeUpdate();
//            System.out.println("votre Randonnee " + id + " a été supprimé");
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        
//        
//    }
//    
//}
