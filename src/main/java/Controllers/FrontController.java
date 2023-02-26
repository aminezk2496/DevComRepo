package Controllers;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FrontController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;
    private List<Utilisateur> mesUtilisateurs;

    @FXML
    private Label LbLogUser;
    @FXML

    private Button adminCard1;
    @FXML
    private AnchorPane eventUserLayout;
    @FXML
    private Circle adminCard11;


    @FXML
    private ImageView adminCard111;

    @FXML
    private Label adminCard1111;
    @FXML
    private Button adminCard2;

    @FXML
    private Circle adminCard22;

    @FXML
    private Button adminCard3;

    @FXML
    private Circle adminCard33;

    @FXML
    private ImageView adminCard333;

    @FXML
    private Circle idimg;
    @FXML
    private Label adminCard3333;
    @FXML
    private ImageView adminCard222;

    @FXML
    private Button Reclamation;
    @FXML
    private Label adminCard2222;

    @FXML
    private VBox userCardLayout;


    public void initialize() throws SQLException {
        ShowUsers();
        // Session
        Utilisateur u = new Utilisateur();
        UtilisateurService us =new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        LbLogUser.setText(u.getLoginUtilisateur());
        String path= us.LoadIMG(u);
        System.out.println(path);
        File f = new File(path);
        System.out.println("//////");
        System.out.println(f.getAbsolutePath());
        Image img= new Image("file:"+f.getAbsolutePath());
        idimg.setFill(new ImagePattern(img));
        adminCard1.setVisible(false);
        adminCard11.setVisible(false);
        adminCard111.setVisible(false);
        adminCard1111.setVisible(false);
        adminCard2.setVisible(false);
        adminCard22.setVisible(false);
        adminCard222.setVisible(false);
        adminCard2222.setVisible(false);
        adminCard3.setVisible(false);
        adminCard33.setVisible(false);
        adminCard333.setVisible(false);
        adminCard3333.setVisible(false);

        if (u.getRankUtilisateur()==1||u.getRankUtilisateur()==2)
        {
            adminCard1.setVisible(false);
            adminCard11.setVisible(false);
            adminCard111.setVisible(false);
            adminCard1111.setVisible(false);
            adminCard2.setVisible(true);
            adminCard22.setVisible(true);
            adminCard222.setVisible(true);
            adminCard2222.setVisible(true);
            adminCard3.setVisible(false);
            adminCard33.setVisible(false);
            adminCard333.setVisible(false);
            adminCard3333.setVisible(false);
            Reclamation.setVisible(false);
        }
    }

    public void Reclamation()
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/FormReclamation.fxml"));
        try {
            AnchorPane anchor1 = fxmlLoader.load();
            Stage stage = new Stage();
            Scene s = new Scene (anchor1);
            stage.setScene(s);
            stage.setTitle("Reclamation");
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public  void switchToEvenement(ActionEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionEvenement.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Events Space");
        stage.setScene(scene);
        stage.show();
    }
    public  void switchToRand(ActionEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/listMuseum.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Events Space");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToBlog(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        if (u.getRankUtilisateur() == 1 || u.getRankUtilisateur() == 2) {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionBlogBackup.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 993, 616);
            stage.setTitle("Welcome To Events Space");
            stage.setScene(scene);
            stage.show();
        } else {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionBlog.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 993, 616);
            stage.setTitle("Welcome To Events Space");
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    void switchToHotel(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionHotel.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Hotels Space");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToCamping(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionCamping.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Campings Space");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToGererUsers(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionGererUsers.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Gerer Users");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void logout(ActionEvent event) throws IOException {
        Utilisateur u;
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        UserSession.INSTANCE.remove("user",u);
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void ShowUsers(){
        UtilisateurService us =new UtilisateurService();
        try{
            mesUtilisateurs = us.afficher();
            Utilisateur u = new Utilisateur();
            u = (Utilisateur) UserSession.INSTANCE.get("user");
            if (u.getRankUtilisateur() == 1 || u.getRankUtilisateur() == 2){

            for (Utilisateur usr : mesUtilisateurs) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Views/UserCard.fxml"));
                VBox cardBox = fxmlLoader.load();
                UserCardController userCardController = fxmlLoader.getController();
                userCardController.setDataUser(usr);
                userCardLayout.getChildren().add(cardBox);
            }
        }}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void switchToSettings(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionUtilisateur.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Settings Space");
        stage.setScene(scene);
        stage.show();
    }
    public  void switchToStats(ActionEvent event) throws IOException{
        fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/GestionStatistique.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Events Space");
        stage.setScene(scene);
        stage.show();
    }

}
