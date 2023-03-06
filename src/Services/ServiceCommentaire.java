/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Commentaire;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Tools.MaConnexion;


/**
 *
 * @author fhima
 */
public class ServiceCommentaire {

   Connection cnx = MaConnexion.getInstance().getCnx();
   
    
    public void createCommentaire(Commentaire commentaire)  {
        String sql = "INSERT INTO commentaire (iduser, contenu, idpub) VALUES (?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setString(1, commentaire.getIduser());
            statement.setString(2, commentaire.getContenuc());
            statement.setInt(3, commentaire.getIdpub());
            statement.executeUpdate();
            System.out.println("votre commentaire a été ajouté");
        }
          catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Commentaire readCommentaire(int id) throws SQLException {
        String sql = "SELECT * FROM commentaire WHERE id = ?";
        Commentaire commentaire = null;
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
              commentaire = new Commentaire();
                commentaire.setId(result.getInt("id"));
                commentaire.setIduser(result.getString("iduser"));
                commentaire.setContenuc(result.getString("contenu"));
                commentaire.setIdpub(result.getInt("idpub"));
            }
        }
        return commentaire;
    }

    public List<Commentaire> readAllCommentaires()  {
        String sql = "SELECT * FROM commentaire";
        List<Commentaire> commentaires = new ArrayList<>();
        try (PreparedStatement statement = cnx.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setId(result.getInt("id"));
                commentaire.setIduser(result.getString("iduser"));
                commentaire.setContenuc(result.getString("contenu"));
                commentaire.setIdpub(result.getInt("idpub"));
                commentaires.add(commentaire);
            }
            
        }
         catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return commentaires;
    }
 public List<Commentaire> getCommentsByPublicationId(int s) {
    List<Commentaire> comments = new ArrayList<>();
    // assuming that comments are stored in a database table called "comments"
    String query = "SELECT * FROM commentaire WHERE idpub = ?";
    try (PreparedStatement stmt = cnx.prepareStatement(query)) {
        stmt.setInt(1, s);
        ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setId(rs.getInt("id"));
                commentaire.setIduser(rs.getString("iduser"));
                commentaire.setContenuc(rs.getString("contenu"));
                commentaire.setIdpub(rs.getInt("idpub"));
                comments.add(commentaire);
            }
            
        }
         catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return comments;
    }

    public void updateCommentaire(Commentaire commentaire) throws SQLException {
        String sql = "UPDATE commentaire SET iduser = ?, contenu = ?, idpub = ? WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setString(1, commentaire.getIduser());
            statement.setString(2, commentaire.getContenuc());
            statement.setInt(3, commentaire.getIdpub());
            statement.setInt(4, commentaire.getId());
            statement.executeUpdate();
        }
    }

    public void deleteCommentaire(int id) throws SQLException {
        String sql = "DELETE FROM commentaire WHERE id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
