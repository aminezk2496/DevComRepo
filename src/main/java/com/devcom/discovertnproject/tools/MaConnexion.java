package com.devcom.discovertnproject.tools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {
    private String url="jdbc:mysql://localhost:3306/espritpi";
    private String user="root";
    private String pwd ="";
    private Connection cnx;
    private static MaConnexion mc;

    private MaConnexion(){
        try{
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connected Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static MaConnexion getInstance(){
        if(mc == null)
            mc = new MaConnexion();
        return mc;
    }
    public Connection getCnx(){
        return cnx;
    }
}