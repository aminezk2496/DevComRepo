/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author rihem
 */
public class Randonnee {
    private int id_Randonnee,Nbr_PlaceR;
    private String Nom,Lieux,Niveau_diff,Programme,ImageR;
    LocalDate Date_Rand;
    private double Prix;
//    id_Randonnee, Nom, Date_Rand, Lieux, Prix, Niveau_diff, Programme
    public Randonnee(){
        
    }
    public Randonnee(int id_Randonnee,String Nom,LocalDate Date_Rand,String Lieux,Double Prix,String ImageR,String Niveau_diff,int Nbr_PlaceR,String Programme){
        this.id_Randonnee = id_Randonnee;
        this.Nom=Nom;
        this.Date_Rand=Date_Rand;
        this.Niveau_diff = Niveau_diff;
        this.Prix=Prix;
        this.Lieux=Lieux;
        this.Nbr_PlaceR=Nbr_PlaceR;
        this.Programme =Programme;
        this.ImageR=ImageR;
    }

    public String getImageR(){
        return ImageR;
    }
        
        public int getNbr_PlaceR(){
            return Nbr_PlaceR;
        }
    public int getId_Randonnee() {
        return id_Randonnee;
    }
    
    public String getNom() {
        return Nom;
    }
    
    public LocalDate getDate_Rand() {
        return Date_Rand;
    }

    public String getNiveau_diff() {
        return Niveau_diff;
    }

    public double getPrix(){
        return Prix;
    }
    
    public String getLieux(){
        return Lieux;
    }
    
    public String getProgramme(){
        return Programme;
    }

    public void setId_Randonnee(int id_Randonnee) {
        this.id_Randonnee = id_Randonnee;
    }
    
    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    
    public void setDate_Rand(LocalDate Date_Rand) {
        this.Date_Rand = Date_Rand;
    }

    public void setNiveau_diff(String Niveau_diff) {
        this.Niveau_diff = Niveau_diff;
    }

    public void setPrix(double Prix){
        this.Prix=Prix;
    }
    
    public void setLieux(String Lieux) {
        this.Lieux = Lieux;
    }

    public void setProgramme(String Programme) {
        this.Programme = Programme;
    }
    public void setNbr_PlaceR(int Nbr_PlaceR){
        this.Nbr_PlaceR=Nbr_PlaceR;
    }
    public void setImageR(String ImageR){
        this.ImageR=ImageR;
    }

    @Override
    public String toString() {
        return "Camping { " + " Id Randonnee = " + id_Randonnee + ",  Nom = " + Nom +", Date Randonnee = " + Date_Rand +
        ", Lieux = "+ Lieux + ", Prix ="+ Prix + ", Niveau Difficulte = " + Niveau_diff + ", Programme = " + Programme + '}';
    }
    
    
}
