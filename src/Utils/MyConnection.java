/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.*;
/**
 *
 * @author rihem
 */
public class MyConnection {

   public String url = "jdbc:mysql://localhost:3306/sortie";
  public   String login= "root";
    public String password="";
   public  Connection cnx;

    public static MyConnection instance ;

    public MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, password);
            System.out.println("connexion reussie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    public Connection getConnexion(){
        return cnx;
    }
}