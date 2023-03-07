/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author ASUS
 




    
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
 
/**
*
* @author kobinath
*/
public class location {
    private int id;
    private String nom;
    private String type;
    private String lieu;
    private String heure;
    private int num;
    private int userid;
    public location(){};
    public location(int id, String nom, String type, String lieu, String heure, int num,int userid) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;
        this.heure = heure;
        this.num = num;
        this.userid = userid ;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getType() {
        return type;
    }
    
    public String getLieu() {
        return lieu;
    }
    
    public String getHeure() {
        return heure;
    }
    
    public int getNum() {
        return num;
    }
    
     public int getUserid() {
        return userid;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    public void setHeure(String heure) {
        this.heure = heure;
    }
    
    public void setNum(int num) {
        this.num = num;}
        public void setUserid(int userid) {
        this.userid = userid;
    }
}