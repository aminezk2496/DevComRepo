package GUI;

import Entities.Utilisateur;
import Services.UserSession;
import Services.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;

public class GestionUtilisateurController {
    @FXML
    private Label MessageUpdate;
    @FXML
    private TextField adressepUser;

    @FXML
    private Button btnModifierUser;

    @FXML
    private TextField emailUser;
    @FXML
    private Button switchToReclamation;
    @FXML
    private TextField imageUser;

    @FXML
    private ImageView imgArea;

    @FXML
    private TextField nomUser;
    @FXML
    private TextField pwd;
    @FXML
    private TextField prenomUser;

    @FXML
    private TextField telUser;

    @FXML
    public void initialize() throws SQLException {
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        nomUser.setText(u.getNomUtilisateur());
        prenomUser.setText(u.getPrenomUtilisateur());
        emailUser.setText(u.getEmailUtilisateur());
        adressepUser.setText(u.getAdresseUtilisateur());
        telUser.setText(u.getNumero_telephoneUtilisateur());
        imageUser.setText(u.getImgUtilisateur());
      if (u.getRankUtilisateur()==1||u.getRankUtilisateur()==2)
            switchToReclamation.setVisible(true);
        else
            switchToReclamation.setVisible(false);
        String path= us.LoadIMG(u);
        File f = new File(path);
        Image img= new Image("file:"+f.getAbsolutePath());
        imgArea.setImage(img);
    }
    public void ModifierUser(ActionEvent event) {
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        u.setNomUtilisateur(nomUser.getText());
        u.setPrenomUtilisateur(prenomUser.getText());
        u.setEmailUtilisateur(emailUser.getText());
        u.setAdresseUtilisateur(adressepUser.getText());
        u.setNumero_telephoneUtilisateur(telUser.getText());
        u.setImgUtilisateur(imageUser.getText());
        us.modifier(u);
        MessageUpdate.setText("Information changé");
    }
    public void ModifierUserPWD()
    {
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        if (pwd.getText()!="")
        {
            if (us.ChangePWD(u,pwd.getText()))
            {
            MessageUpdate.setText("Mot de passe changé");
            pwd.setText("");
            }
        }else
        {
            MessageUpdate.setText("Taper un mot de passe");
            pwd.setText("");
        }
    }
    @FXML
    void ModifierUserIMG(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\devcomimgupload\\"+u.getLoginUtilisateur()+".jpg";
        if (file != null) {

            // pickUpPathField it's your TextField fx:id
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            Image img = new Image("file:"+file.getAbsolutePath());
            imgArea.setImage(img);
            int b=0;
            while(b!=-1){
                b=bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
        } else  {
            MessageUpdate.setText("error");
        }


        if (us.WriteIMG(u,DBPath))
        {
            MessageUpdate.setText("Image Modifier");
        }
    }
    public void switchToMainFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
        public void switchToReclamation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GestionGererReclamation.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

    



