/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Hebergement;
import java.sql.*;
import java.util.*;

//import edu.worshop.entites.Personne;
import Tools.MaConnexion;
import Entities.Hebergement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUDHebergement implements InterfaceServices {

    Statement ste;
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterpHeber(Hebergement h) {
        // TODO Auto-generated method stub
        try {
            ste = cnx.createStatement();
            String req = "Insert into hebergement values(0,'"
                    + h.getNom_heber() + "','"
                    + h.getDescription_heber() + "','"
                    + h.getDisponibilite() + "','"
                    + h.getPrix_heber() + "','"
                    + h.getLocalisation_heber() + "','"
                    + h.getContact_heber() + "','"
                    + h.getType_heber() + "')";
            ste.executeUpdate(req);
            System.out.println("Hebergement ajouté");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout!!!!");
        }
    }

    @Override
    public void modifierHeber(Hebergement h) {
        // TODO Auto-generated method stub
        try {
            String req = "UPDATE `hebergement` SET "
                    + "`nom_heber` = '" + h.getNom_heber() + "', "
                    + "`description_heber` = '" + h.getDescription_heber() + "',"
                    + "`nb_chambre` = '" + h.getDisponibilite() + "',"
                    + "`prix_heber` = '" + h.getPrix_heber() + "', "
                    + "`localisation_heber` = '" + h.getLocalisation_heber() + "',"
                    + "`contact_heber` = '" + h.getContact_heber()+ "', "
                    + "`type_heber` = '" + h.getType_heber() + "'"
                    + "WHERE `hebergement`.`id_heber` = " + h.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Hebergement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerHeber(int id) {
        // TODO Auto-generated method stub
        try {
            String req = "DELETE FROM `hebergement` WHERE id_heber = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Hebergement> afficherhebergement() {
        // TODO Auto-generated method stub

        List<Hebergement> pers = new ArrayList<Hebergement>();
        try {
            String req = "SELECT * FROM `hebergement`";
            Statement ste = cnx.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {
                Hebergement resultHeber;
                resultHeber = new Hebergement(
                        result.getInt("id_heber"), 
                        result.getString("nom_heber"), 
                        result.getString("description_heber"), 
                        result.getInt("nb_chambre"), 
                        result.getInt("prix_heber"), 
                        result.getString("localisation_heber"), 
                        result.getInt( "contact_heber"), 
                        result.getString("type_heber"));
                pers.add(resultHeber);
            }
            System.out.println(pers);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return pers;
    }

    @Override
    public void ajouterHeber(Hebergement h) {
        // TODO Auto-generated method stub

    }

}
