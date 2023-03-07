/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Publication;
import Entities.Utilisateur;
import java.sql.Connection;
import Tools.MaConnexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;
import Tools.MaConnexion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author fhima
 */
public class ServicePublication<pst> implements IService<Publication> {
   
    Connection cnx = MaConnexion.getInstance().getCnx();
    
         public Publication readPublication(int id) throws SQLException {
        String sql = "SELECT * FROM publication WHERE id = ?";
        Publication publication = null;
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
              publication = new Publication();
                publication.setId(result.getInt("id"));
                publication.setIduser(result.getString("iduser"));
                publication.setContenu(result.getString("contenu"));
                publication.setUrlimage(result.getString("urlimage"));
                publication.setDate(result.getDate("date_publication"));
            }
        }
        return publication;
    }

    public void ajouterPub(Publication p ){
        try {
            String requete = "INSERT INTO publication (contenu,iduser,urlimage,date_publication)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,p.getContenu());
            pst.setString(2,p.getIduser());
            pst.setString(3,p.getUrlimage());
            pst.setDate(4,p.getDate());
            pst.executeUpdate();
            System.out.println("votre publication a été ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 @Override
    public ObservableList<Publication> afficher() {
        ObservableList<Publication> myList = FXCollections.observableArrayList();

        try {
            String requte3 = "SELECT * FROM `publication` ";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requte3);
            while(res.next()){
                /*publication pub = new publication();
                pub.setContenu(rs.getString("contenu"));
                pub.setId(rs.getInt("id"));
                 pub.setIduser(rs.getString("iduser"));

                pub.setDate(rs.getDate("date_publication"));
                 pub.setUrlimage(rs.getString("urlimage"));
                
                myList.add(pub);*/
                int id = res.getInt(1);
            
            String contenu = res.getString("contenu");
            String iduser = res.getString("iduser");
             String urlimage = res.getString("urlimage");
            
            Date date = res.getDate("date_publication");
            

            Publication pub = new Publication(id,contenu,iduser,urlimage, (java.sql.Date) date);
                myList.add(pub);
            }
            
         
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return myList;
    }
  public void modiferPub(Publication p,int id) {
        try {
            String req = "UPDATE publication SET contenu=?,iduser=?,urlimage=? "
                    + "WHERE id="+id;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getContenu());
            pst.setString(2, p.getIduser());
            pst.setString(3, p.getUrlimage());
            
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
  public void supprimerPub( int id ) {
           try {
            String req1 = "delete from publication where id ="+id;
            PreparedStatement pst =cnx.prepareStatement(req1);
           
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
           
        
    }
    
    
    
 public List<Publication> trierParDate(){
                    List<Publication> list=new ArrayList<>();
        try{

             String requete="select * from publication";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            
            while(rs.next()){
                   Publication p=new Publication();
            
          
               Date date = rs.getDate("date_publication");
              list.add(new Publication(rs.getInt("id"),rs.getString("contenu"),rs.getString("iduser"),rs.getString("urlimage"),rs.getDate("date_publication")));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
         Collections.sort(list,new myComparator());
        Collections.reverse(list);
        return list;
        
   } 
 public ObservableList<Publication> afficherWithCon(String Contenu) {
        ObservableList<Publication> publications = FXCollections.observableArrayList();
        String query = "SELECT * FROM publication where contenu LIKE '%"+Contenu+"%'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Publication pub = new Publication();
                pub.setContenu(rs.getString("contenu"));
                pub.setId(rs.getInt("id"));
                 pub.setIduser(rs.getString("iduser"));

                pub.setDate(rs.getDate("date_publication"));
                 pub.setUrlimage(rs.getString("urlimage"));
                
                publications.add(pub);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return publications;
    }
 public boolean SendMail(Publication p,String signal )
    {
        UtilisateurService us = new UtilisateurService();
        Utilisateur u =us.getUserData("ali123");
        String password = "slzwonaplhtpfcww";
        String from,to,host,sub,content;
        from = "devcompi2023@gmail.com";
        to =u.getEmailUtilisateur();
        host="localhost";
   
            sub="Signal publlication";
            content="Bonjour Monsieur Administrateur Je voudrais rapporter cette annonce id:("+p.getId()+") et de contenu :( "+p.getContenu()+") par ce qu'elle contient. "+signal+".";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            Session session=Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication(from,password);
                        }
                    }
            );
            try {
                MimeMessage m =new MimeMessage(session);
                m.setFrom(from);
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(sub);
                m.setText(content);
                Transport.send(m);
                return true;

            } catch (MessagingException e) {
                e.printStackTrace();
            }
      

        return false;
    }
  public boolean SendMailConf(String mail,Publication p,String signal )
    {
        UtilisateurService us = new UtilisateurService();
        Utilisateur u =us.getUserData("ali123");
        String password = "slzwonaplhtpfcww";
        String from,to,host,sub,content;
        from = "devcompi2023@gmail.com";
        to =mail;
        host="localhost";
   
            sub="Signal publlication";
            content="Bonjour Monsieur votre reclamation pour cette annonce  de contenu :( "+p.getContenu()+") par ce qu'elle contient. "+signal+"a ete bien enregistré.";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            Session session=Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication(from,password);
                        }
                    }
            );
            try {
                MimeMessage m =new MimeMessage(session);
                m.setFrom(from);
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(sub);
                m.setText(content);
                Transport.send(m);
                return true;

            } catch (MessagingException e) {
                e.printStackTrace();
            }
      

        return false;
    }
 

    @Override
    public void ajouter(Publication t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void modifier(Publication t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Publication t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
class myComparator implements Comparator<Publication>{

    @Override
    public int compare(Publication t, Publication t1) {
        return t.getDate().compareTo(t1.getDate());
    }
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
