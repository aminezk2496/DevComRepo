package GUI;


import Entities.Utilisateur;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.types.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LogWithFacebookController {
    private String app_Id = "127733576891116";
    private String app_Secret = "a219e9077471d59e1c8c7da9dbb40b1d";
    private String redarect_url = "http://localhost/";
    private String state = "9812";
    private String redirect_url_encoded = "http%3A%2F%2Flocalhost%2F";
    private String authentication = "https://www.facebook.com/v8.0/dialog/oauth?client_id="+app_Id+"&redirect_uri="+redirect_url_encoded+"&state="+state;
    private String granh = "https://graph.facebook.com/v8.0/oauth/access_token?client_id="+app_Id+"&redirect_uri="+redirect_url_encoded+"&client_secret="+app_Secret+"&code=";
    @FXML
    private Button login_as_facebook;
    @FXML
    private Button gotoSignup;

    @FXML
    private Label name;

    @FXML
    private Circle profile_pic;

    @FXML
    private AnchorPane root;

    @FXML
    void gotoSignup(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        u.setPrenomUtilisateur(name.getText());
        u.setImgUtilisateur(profile_pic.toString());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setUserData(u);
        // SignUp2Controller signUp2Controller=loader.getController();
        //   signUp2Controller.returnUser(LoginTextField1.getText());
        //  signUp2Controller.show(LoginTextField1.getText());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void initialize()
    {
        gotoSignup.setVisible(false);
    }
    @FXML
    void login_as_facebook(ActionEvent event) {
        gotoSignup.setVisible(false);
        WebView webView = new WebView();
        WebEngine eg = webView.getEngine();
        Pane wView = new Pane();
        wView.getChildren().add(webView);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(wView,800,500));
        stage.show();
        eg.load(authentication);
        eg.locationProperty().addListener((obs,oldlocation,newlocation) ->{
            if (newlocation!=null && newlocation.startsWith("http://localhost")){
                int codeOffset = newlocation.indexOf("code=");
                String code = newlocation.substring(codeOffset + "code=".length());
                DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
             //   FacebookClient.AccessToken accessToken = facebookClient.obtainUserAccessToken (app_Id, app_Secret, "http://localhost/", code);
                String access_token ="EAAfTnLkZBt2QBAIRKnFyaSHQkSZBXGopo3M3sZBL51aHOQU6K4q6jjIZCPcU4BLET0QZAwW6NjqXszZB9I9T1uLZCjsEvGqvnIZCoVW2eHTjVgzORWXSlygAHDNMT20UV9OulnVZCRmF0fllm7U4aJTZANHlrZBfZCaRNo8yYLjTU5T0qPZBLGZCBLl1FzsP3RTkWuZCzvAwrm7fjUSpZBYPZB9Km4GI1qPZB63FgjZBcNugUImLaL5rPDhYbiwcQaY";
                FacebookClient fbClient = new DefaultFacebookClient(access_token,Version.LATEST);
                fbClient.createClientWithAccessToken(access_token);
                JsonObject profile_pic = fbClient.fetchObject("me/picture",JsonObject.class, Parameter.with("redirect","false"));
                User user = fbClient.fetchObject("me",User.class);
                name.setText(user.getName());
                int si = profile_pic.toString().indexOf("url\":\"");
                int ei= profile_pic.toString().indexOf("\",\"");
                String profile_url = profile_pic.toString().substring(si+6, ei);
                this.profile_pic.setFill(new ImagePattern(new Image(profile_url)));
                name.setText(user.getName());
                login_as_facebook.setVisible(false);
                gotoSignup.setVisible(true);

            }
        });

    }

}