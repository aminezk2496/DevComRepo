/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Entities.Evenement;
import Entities.Participation;
import Entities.Utilisateur;
import Services.UserSession;
import Services.UtilisateurService;
import Tools.MaConnexion;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Amal
 */
public class DetailEvenementClientController {
     @FXML
    private javafx.scene.control.Button BParti;
    @FXML
    private Label titre_detail;

    @FXML
    private Label date_debut_detail;
    
    @FXML
    private Label date_fin_detail;
    
    @FXML
    private Label prix_detail;
    
    @FXML
    private Label lieu_detail;
    @FXML
    private Label desc_detail;
    @FXML
    private ImageView ImageEvent;
    private Evenement evenement;
    Connection con = MaConnexion.getInstance().getCnx();

    void setData(Evenement evenement) {
        this.evenement = evenement;
        titre_detail.setText(evenement.getTitreEvent());
        date_debut_detail.setText(evenement.getDateDebutEvent());
        date_fin_detail.setText(evenement.getDateFinEvent() );
        prix_detail.setText( evenement.getPrixEvent()+" DT");
        lieu_detail.setText( evenement.getLieuEvent());
        desc_detail.setText( evenement.getDescEvent());
        
        File f = new File(evenement.getImageEvent());
        javafx.scene.image.Image img= new javafx.scene.image.Image("file:"+f.getAbsolutePath());
        ImageEvent.setImage(img);
        BParti.setVisible(true);
        
    }
   public void SaveParti() {
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
       String select = "INSERT INTO participation (Nombre,Montant,Etat,id_evenement,id_utilisateur)VALUES(?,?,?,?,?)";
        BParti.setVisible(false);
        try {
            PreparedStatement st = con.prepareStatement(select);
            //st.setString(1, id_ch.getText());
            st.setInt(1, 1);
            st.setInt(2, Integer.parseInt(evenement.getPrixEvent()));
            st.setString(3, "");
            st.setInt(4, evenement.getIdEvent());
            st.setInt(5, u.getIdUtilisateur());
            st.executeUpdate();
        } catch (SQLException ex) {
           System.out.println(ex);
        }
        
    }
    
}
