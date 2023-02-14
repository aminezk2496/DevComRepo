package com.devcom.discovertnproject.services;

import com.devcom.discovertnproject.entities.Utilisateur;
import com.devcom.discovertnproject.tools.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurService implements IService<Utilisateur>{
    Connection cnx = MaConnexion.getInstance().getCnx();



    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO UTILISATEUR(nom_utilisateur,prenom_utilisateur,email_utilisateur,login_utilisateur," +
                "mdp_utilisateur,image_utilisateur,rank_utilisateur,telephone_utilisateur,adresse_utilisateur) VALUES(?,?,?,?,?,?,?,?,?)";


            try {
                PreparedStatement ste = cnx.prepareStatement(query);
                ste.setString(1, utilisateur.getNomUtilisateur());
                ste.setString(2, utilisateur.getPrenomUtilisateur());
                ste.setString(3, utilisateur.getEmailUtilisateur());
                ste.setString(4, utilisateur.getLoginUtilisateur());
                ste.setString(5, utilisateur.getMot_de_passeUtilisateur());
                ste.setString(6, utilisateur.getImgUtilisateur());
                ste.setInt(7, utilisateur.getRankUtilisateur());
                ste.setString(8, utilisateur.getNumero_telephoneUtilisateur());
                ste.setString(9, utilisateur.getAdresseUtilisateur());
                ste.executeUpdate();
                System.out.println("User Added Successfully");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> users = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(rs.getInt("id_utilisateur"));
                utilisateur.setNomUtilisateur(rs.getString("nom_utilisateur"));
                utilisateur.setPrenomUtilisateur(rs.getString("prenom_utilisateur"));
                utilisateur.setEmailUtilisateur(rs.getString("email_utilisateur"));
                utilisateur.setLoginUtilisateur(rs.getString("login_utilisateur"));
                utilisateur.setMot_de_passeUtilisateur(rs.getString("mdp_utilisateur"));
                utilisateur.setImgUtilisateur(rs.getString("image_utilisateur"));
                utilisateur.setRankUtilisateur(rs.getInt("rank_utilisateur"));
                utilisateur.setNumero_telephoneUtilisateur(rs.getString("telephone_utilisateur"));
                utilisateur.setAdresseUtilisateur(rs.getString("adresse_utilisateur"));
                users.add(utilisateur);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void modifier(Utilisateur user) {
        String query = "UPDATE utilisateur SET " +
                "nom_utilisateur = '" + user.getNomUtilisateur() +
                "', prenom_utilisateur = '" + user.getPrenomUtilisateur() +
                "', email_utilisateur = '" + user.getEmailUtilisateur() +
                "', login_utilisateur = '" + user.getLoginUtilisateur() +
                "', rank_utilisateur = '" + user.getRankUtilisateur() +
                "', telephone_utilisateur = '" + user.getNumero_telephoneUtilisateur() +
                "', adresse_utilisateur = '" + user.getAdresseUtilisateur() +
                "' WHERE id_utilisateur = " + user.getIdUtilisateur() + "";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("User Updated Successfully ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Utilisateur utilisateur) {
        String query = "DELETE FROM utilisateur WHERE login_utilisateur = '" + utilisateur.getLoginUtilisateur() + "'";
        try {
            Statement ste = cnx.createStatement();
            int deleted = ste.executeUpdate(query);
            System.out.println(deleted);
            if (deleted > 0)
                System.out.println("Deleted successfully");
            else
                System.out.println("Nothing deleted");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
