package Services;

import Entities.Utilisateur;
import Tools.MaConnexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.codec.digest.DigestUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Random;
public class UtilisateurService<s> implements IService<Utilisateur> {
    PreparedStatement store;
    Utilisateur user = new Utilisateur();
    Connection cnx = MaConnexion.getInstance().getCnx();
    boolean existe = false;


    public int existe(Utilisateur u) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from utilisateur WHERE login_utilisateur = '" + u.getLoginUtilisateur() + "'");
        int size = 0;
        rs.next();
        size = rs.getInt(1);
        return size;
    }
    public int existeMail(Utilisateur u) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from utilisateur WHERE email_utilisateur = '" + u.getEmailUtilisateur() + "'");
        int size = 0;
        rs.next();
        size = rs.getInt(1);
        return size;
    }

    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO UTILISATEUR(id_utilisateur,nom_utilisateur,prenom_utilisateur,email_utilisateur,login_utilisateur," +
                "mdp_utilisateur,image_utilisateur,rank_utilisateur,telephone_utilisateur,adresse_utilisateur) VALUES(?,?,?,?,?,?,?,?,?,?)";

        int x = existe(utilisateur);
        if (x == 0) {
            try {
                PreparedStatement ste = cnx.prepareStatement(query);
                ste.setInt(1, utilisateur.getIdUtilisateur());
                ste.setString(2, utilisateur.getNomUtilisateur());
                ste.setString(3, utilisateur.getPrenomUtilisateur());
                ste.setString(4, utilisateur.getEmailUtilisateur());
                ste.setString(5, utilisateur.getLoginUtilisateur());
                ste.setString(6, utilisateur.getMot_de_passeUtilisateur());
                ste.setString(7, utilisateur.getImgUtilisateur());
                ste.setInt(8, utilisateur.getRankUtilisateur());
                ste.setString(9, utilisateur.getNumero_telephoneUtilisateur());
                ste.setString(10, utilisateur.getAdresseUtilisateur());
                ste.executeUpdate();
                System.out.println("User Added Successfully");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("user already exists");
        }
    }

    @Override
    public ObservableList<Utilisateur> afficher() {
        ObservableList<Utilisateur> users = FXCollections.observableArrayList();
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
    public ObservableList<Utilisateur> afficherClient() {
        ObservableList<Utilisateur> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM utilisateur where rank_utilisateur=0";
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
   
    public String LoadIMG(Utilisateur utilisateur) throws SQLException {
        String rq="SELECT * from UTILISATEUR where login_utilisateur= '" + utilisateur.getLoginUtilisateur() + "'";
        // System.out.println(rq);
        try {
            PreparedStatement ste = cnx.prepareStatement(rq);
            ResultSet rs = ste.executeQuery(rq);
            while (rs.next()) {
                String path=rs.getString("image_utilisateur");
                return path;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public void modifier(Utilisateur utilisateur) {
        String query = "UPDATE utilisateur SET " +
                "nom_utilisateur = '" + utilisateur.getNomUtilisateur() +
                "', prenom_utilisateur = '" + utilisateur.getPrenomUtilisateur() +
                "', email_utilisateur = '" + utilisateur.getEmailUtilisateur() +
                "', login_utilisateur = '" + utilisateur.getLoginUtilisateur() +
                "', rank_utilisateur = '" + utilisateur.getRankUtilisateur() +
                "', telephone_utilisateur = '" + utilisateur.getNumero_telephoneUtilisateur() +
                "', adresse_utilisateur = '" + utilisateur.getAdresseUtilisateur() +
                "' WHERE id_utilisateur = " + utilisateur.getIdUtilisateur() + "";
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

    public boolean Login(String username, String Password) {
        // System.out.println(Password);
        String q1 = "SELECT * from utilisateur WHERE login_utilisateur = '"
                + username + "' AND mdp_utilisateur = '"
                + Password + "'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(q1);
            if (rs.next() != false) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int signUp1(Utilisateur utilisateur) throws SQLException {
        String rq = "INSERT INTO UTILISATEUR(id_utilisateur,nom_utilisateur,prenom_utilisateur,email_utilisateur,login_utilisateur," +
                "mdp_utilisateur,image_utilisateur,rank_utilisateur,telephone_utilisateur,adresse_utilisateur) VALUES(?,?,?,?,?,?,?,?,?,?)";

        UtilisateurService us = new UtilisateurService();

        int x = us.existe(utilisateur);
        int y = us.existeMail(utilisateur);
        if (x == 0 ) {
            if (y == 0) {
                PreparedStatement ste = cnx.prepareStatement(rq);
                ste.setInt(1, utilisateur.getIdUtilisateur());
                ste.setString(2, utilisateur.getNomUtilisateur());
                ste.setString(3, utilisateur.getPrenomUtilisateur());
                ste.setString(4, utilisateur.getEmailUtilisateur());
                ste.setString(5, utilisateur.getLoginUtilisateur());
                ste.setString(6, utilisateur.getMot_de_passeUtilisateur());
                ste.setNull(7, java.sql.Types.BLOB);
                ste.setInt(8, utilisateur.getRankUtilisateur());
                ste.setString(9, utilisateur.getNumero_telephoneUtilisateur());
                ste.setString(10, utilisateur.getAdresseUtilisateur());
                ste.executeUpdate();
                return 0;
            }
            else
            {
                return 1;
            }
        }else {
            return 2;
        }
    }

    public boolean WriteIMG(Utilisateur utilisateur, String DBPath) {
        String update = "UPDATE UTILISATEUR set image_utilisateur=? where login_utilisateur= '" + utilisateur.getLoginUtilisateur() + "'";
        try {
            store = cnx.prepareStatement(update);
            store.setString(1,DBPath);
            store.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean RankToAdmin(String Login)
    {
        String update = "UPDATE UTILISATEUR set rank_utilisateur=? where login_utilisateur= '" + Login + "'";
        try {
            store = cnx.prepareStatement(update);
            store.setString(1,"1");
            store.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String Login_Dispo(Utilisateur u) throws SQLException {
        Random rand = new Random(); //instance of random class
        int upperbound = 1000;
        int int_random = rand.nextInt(upperbound);
        String Newlogin=u.getLoginUtilisateur()+""+int_random;
        u.setLoginUtilisateur(Newlogin);
        while (existe(u)!=0)
        {
            int_random = rand.nextInt(upperbound);
            Newlogin=u.getLoginUtilisateur()+""+int_random;
        }
        return Newlogin;
    }
    public Utilisateur getUserData(String username) {
        ObservableList<Utilisateur> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM utilisateur where login_utilisateur = '"
                + username + "'OR email_utilisateur = '" + username +"'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                user.setIdUtilisateur(rs.getInt("id_utilisateur"));
                user.setNomUtilisateur(rs.getString("nom_utilisateur"));
                user.setPrenomUtilisateur(rs.getString("prenom_utilisateur"));
                user.setEmailUtilisateur(rs.getString("email_utilisateur"));
                user.setLoginUtilisateur(rs.getString("login_utilisateur"));
                user.setMot_de_passeUtilisateur(rs.getString("mdp_utilisateur"));
                user.setImgUtilisateur(rs.getString("image_utilisateur"));
                user.setRankUtilisateur(rs.getInt("rank_utilisateur"));
                user.setNumero_telephoneUtilisateur(rs.getString("telephone_utilisateur"));
                user.setAdresseUtilisateur(rs.getString("adresse_utilisateur"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public Utilisateur getUserDataWithEmail(String email) {
        ObservableList<Utilisateur> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM utilisateur where login_utilisateur = '"
                + email + "'OR email_utilisateur = '" + email +"'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                user.setIdUtilisateur(rs.getInt("id_utilisateur"));
                user.setNomUtilisateur(rs.getString("nom_utilisateur"));
                user.setPrenomUtilisateur(rs.getString("prenom_utilisateur"));
                user.setEmailUtilisateur(rs.getString("email_utilisateur"));
                user.setLoginUtilisateur(rs.getString("login_utilisateur"));
                user.setMot_de_passeUtilisateur(rs.getString("mdp_utilisateur"));
                user.setImgUtilisateur(rs.getString("image_utilisateur"));
                user.setRankUtilisateur(rs.getInt("rank_utilisateur"));
                user.setNumero_telephoneUtilisateur(rs.getString("telephone_utilisateur"));
                user.setAdresseUtilisateur(rs.getString("adresse_utilisateur"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public Utilisateur getUserDataWithID(int id) {
        ObservableList<Utilisateur> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM utilisateur where id_utilisateur = '"
                + id + "'OR email_utilisateur = '" + id +"'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                user.setIdUtilisateur(rs.getInt("id_utilisateur"));
                user.setNomUtilisateur(rs.getString("nom_utilisateur"));
                user.setPrenomUtilisateur(rs.getString("prenom_utilisateur"));
                user.setEmailUtilisateur(rs.getString("email_utilisateur"));
                user.setLoginUtilisateur(rs.getString("login_utilisateur"));
                user.setMot_de_passeUtilisateur(rs.getString("mdp_utilisateur"));
                user.setImgUtilisateur(rs.getString("image_utilisateur"));
                user.setRankUtilisateur(rs.getInt("rank_utilisateur"));
                user.setNumero_telephoneUtilisateur(rs.getString("telephone_utilisateur"));
                user.setAdresseUtilisateur(rs.getString("adresse_utilisateur"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public boolean SendMail(Utilisateur user,String code )
    {
        Utilisateur u = getUserDataWithEmail(user.getEmailUtilisateur());
        String password = "slzwonaplhtpfcww";
        String from,to,host,sub,content;
        from = "devcompi2023@gmail.com";
        to =u.getEmailUtilisateur();
        host="localhost";
        if (code == "null")
        {
            sub="Bienvenue sur notre Plateforme";
            content="Bonjour Mr/Mme "+u.getNomUtilisateur()+" "+u.getPrenomUtilisateur()+". Au nom de tous les membres du plateforme, je vous souhaite la bienvenue.";
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
        }else
        {
            System.out.println(u);
            sub="Réinitialisation du mot de passe de votre compte ";
            content="Bonjour"+u.getPrenomUtilisateur()+".\n \n Avez-vous oublié votre mot de passe \n \n Taper ce code dans l'application =  " +code+" \n \n" +
                    "Si vous ne souhaitez pas changer votre mot de passe ou si vous ne l’avez pas demandé, veuillez ignorer et supprimer ce message. \n \n" +
                    "Cordialement,\n \n " +
                    "L’équipe PiDevers ";
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
        }

        return false;
    }
    public String ForgetPWD(String email)
    {
        String Code="";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        Utilisateur u =new Utilisateur();
        int length = 7;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        Code=randomString;
        u.setEmailUtilisateur(email);

        if (SendMail(u,Code))
        {
            System.out.println("Service"+Code);
            return Code;
        }
        return "null";
    }
    public boolean ChangePWD(Utilisateur user,String mdp)
    {
        String query="UPDATE utilisateur SET " +
                "nom_utilisateur = '" + user.getNomUtilisateur() +
                "', prenom_utilisateur = '" + user.getPrenomUtilisateur() +
                "', email_utilisateur = '" + user.getEmailUtilisateur() +
                "', login_utilisateur = '" + user.getLoginUtilisateur() +
                "', mdp_utilisateur= '" + DigestUtils.sha1Hex(mdp) +
                "', rank_utilisateur = '" + user.getRankUtilisateur() +
                "', telephone_utilisateur = '" + user.getNumero_telephoneUtilisateur() +
                "', adresse_utilisateur = '" + user.getAdresseUtilisateur() +
                "' WHERE id_utilisateur = " + user.getIdUtilisateur() + "";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ObservableList<Utilisateur> afficherWithID(String login) {
        ObservableList<Utilisateur> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM utilisateur where login_utilisateur LIKE '%"+login+"%'";
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

  public void pdfUtilisateurs() throws FileNotFoundException, DocumentException {
        try {

            String file_name="C:\\xampp\\htdocs\\nosUtilisateurs.pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(file_name));
            doc.open();
            PreparedStatement ps=null;
            ResultSet rs=null;
            String query = "SELECT * FROM UTILISATEUR ";
            ps=cnx.prepareStatement(query);
            rs=ps.executeQuery();

            Paragraph title = new Paragraph("                                                                        Nos Utilisateurs");
            title.setSpacingBefore(15);
            title.setSpacingAfter(15);
            doc.add(title);
            Paragraph head = new Paragraph("              Nom                         Prenom                    Tel                         Email");
            head.setSpacingBefore(15);
            head.setSpacingAfter(15);
            doc.add(head);
            while(rs.next()) {
                Paragraph para=new Paragraph( "             " +
                        rs.getString("nom_utilisateur") + "                    " +
                        rs.getString("prenom_utilisateur")+"                      " +rs.getString("telephone_utilisateur")+"                     "+rs.getString("email_utilisateur")
                );
               /* para.setSpacingBefore(15);
                para.setSpacingAfter(15);*/
                doc.add(para);
                doc.add(new Paragraph(" "));
            }
            doc.close();
            System.out.println("Utilisateurs Pdf Generated With Success");
        }catch(Exception k){
            System.err.println(k);
        }
    }
}







