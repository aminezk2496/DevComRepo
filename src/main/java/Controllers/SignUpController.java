package Controllers;

import Entities.Utilisateur;
import Services.UtilisateurService;
import Tools.MaConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController {
    @FXML
    private Label ErrorLabel;
    @FXML
    private ImageView imgArea;
    @FXML
    private TextField LoginTextField1;
    @FXML
    private Button loadImg;
    @FXML
    private TextField adresseMailTextField1;
    @FXML
    private Button gnlogin;
    @FXML
    private TextField adresseTextField11;
    @FXML
    private Button registrebutton;
    @FXML
    private Button loginButton;

    @FXML
    private TextField mo1TextField2;
    @FXML
    private Button reciveimg;
    @FXML
    private TextField mo2TextField2;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField numTelTextField11;

    @FXML
    private TextField prenomTextField;
    Connection cnx = MaConnexion.getInstance().getCnx();

    @FXML
    public void registre(ActionEvent event) throws SQLException, IOException {

        if (nomTextField.getText()=="")
        {
            ErrorLabel.setText("Le nom est obligatoire !");
        }else if (prenomTextField.getText()=="")
        {
            ErrorLabel.setText("Le prenom est obligatoire !");
        }else if (adresseMailTextField1.getText()=="")
        {
            ErrorLabel.setText("L'adresse mail est obligatoire !");
        } else if (!isValidEmail(adresseMailTextField1.getText()))
        {
            ErrorLabel.setText("L'adresse mail n'est pas correct!");
        }else if (LoginTextField1.getText()=="")
        {
            ErrorLabel.setText("Le login est obligatoire !");
        }else if (mo1TextField2.getText()=="")
        {
            ErrorLabel.setText("Le mot de passe est obligatoire !");
        }else if (mo2TextField2.getText()=="")
        {
            ErrorLabel.setText("il faut repeter le mot de passe");
        }else if (mo1TextField2.getText().equals(mo2TextField2.getText())==false)
        {
            ErrorLabel.setText("les mots de passe saisis ne sont pas identiques");
        }
        else if (numTelTextField11.getText()=="")
        {
            ErrorLabel.setText("Le numero de telephone  est obligatoire !");
        }else if (!numTelTextField11.getText().matches("\\d{8}"))
        {
            ErrorLabel.setText("Le numero de telephone  n'est pas valide ");
        }
        else if (adresseTextField11.getText()=="")
        {
            ErrorLabel.setText("l'adresse postale est obligatoire !");
        }else
        {

                Utilisateur utilisateur = new Utilisateur();
                UtilisateurService us = new UtilisateurService();
                utilisateur.setNomUtilisateur(nomTextField.getText());
                utilisateur.setPrenomUtilisateur(prenomTextField.getText());
                utilisateur.setEmailUtilisateur(adresseMailTextField1.getText());
                utilisateur.setLoginUtilisateur(LoginTextField1.getText());
                utilisateur.setMot_de_passeUtilisateur(mo1TextField2.getText());
                utilisateur.setRankUtilisateur(0);
                utilisateur.setNumero_telephoneUtilisateur(numTelTextField11.getText());
                utilisateur.setAdresseUtilisateur(adresseTextField11.getText());
       if (us.signUp1(utilisateur)==0) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SignUp2.fxml"));
           Parent root = (Parent) loader.load();
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setUserData(utilisateur);
           // SignUp2Controller signUp2Controller=loader.getController();
           //   signUp2Controller.returnUser(LoginTextField1.getText());
           //  signUp2Controller.show(LoginTextField1.getText());
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }
       else if (us.signUp1(utilisateur)==1)
       {
           ErrorLabel.setText("L'adresse email deja existe");
       }else if (us.signUp1(utilisateur)==2)
       {
           ErrorLabel.setText("Le login est deja utilisé \n Login Disponible :"+us.Login_Dispo(utilisateur)+"  !");

       }
    }}
    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private boolean isValidEmail(String text) {
        Pattern p =Pattern.compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(adresseMailTextField1.getText());
        if (m.find() && m.group().equals(adresseMailTextField1.getText()))
        {
            return true ;
        }
        return false;
        }
    @FXML
    void gnlogin() {
        Random rand = new Random(); //instance of random class
        int upperbound = 10000;
        int int_random = rand.nextInt(upperbound);
        String Newlogin=nomTextField.getText()+"."+int_random;
         Newlogin= Newlogin.replace(" ","");
        LoginTextField1.setText(Newlogin);
    }
    /*public void loadImg()
    {

         file =fileChooser.showOpenDialog(loadImg.getScene().getWindow());
       // String req1="UPDATE UTILISATEUR SET ";
        try {
            fileInputStream= new FileInputStream(file);
            Image image =new Image(fileInputStream);
            imgArea.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/
   /* @FXML
    void reciveimg(ActionEvent event) throws SQLException {
String req1="select img_utilisateur from utilisateur where id_utilisateur =19";
        PreparedStatement ste = cnx.prepareStatement(req1);
        ste.executeQuery();
        ResultSet resultSet = ste.executeQuery();
        System.out.println("t1");
if (resultSet.next()) {
    System.out.println("t2");
    Blob blob = resultSet.getBlob(1);
    System.out.println(blob);
    InputStream inputStream = blob.getBinaryStream();
    System.out.println("t4");
    Image image = new Image(inputStream);
    System.out.println("t5");
    imgArea.setImage(image);
    System.out.println("t6");
}
    }

    */
}
