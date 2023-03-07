/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.sql.*;



/**
 *
 * @author allal
 */
public class myConnection {
     static String url ="jdbc:mysql://localhost:3306/espritpidev";
   static String login ="root";
   static String password = "";
   static Connection myConnDB;
   public static myConnection instance;
   

    private myConnection() {
        try {
            
           myConnDB = DriverManager.getConnection(url, login, password);
           System.out.println("Successful connection");
       } catch (SQLException ex) {
           System.out.println(ex);
       }
    }
    public Connection getConnection(){
        return myConnDB;
    }
    
    public static myConnection getInstance(){
      
        if(instance == null){
            instance = new myConnection();
        }
        return instance;
    }
   
}
