/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Reservation;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Tools.MaConnexion;

/**
 *
 * @author Gio・ブランドー
 */
public class CRUDReservation {
    Statement ste;
    Connection cnx = MaConnexion.getInstance().getCnx();
    
        public void ajouterpHeber(Reservation r) {
        // TODO Auto-generated method stub
        try {
            ste = cnx.createStatement();
            String req = "Insert into reservation values(0,'"
                    + r.getId_heberR() + "','"
                    + r.getDate() + "','"
                    + r.getDuree() + "','"
                    + r.getNb_chambre() + "')";
            ste.executeUpdate(req);
            System.out.println("Reservation effectuéé");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout!!!!");
            
        }
    }
}
