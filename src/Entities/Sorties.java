/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Date;

/**
 *
 * @author rihem
 */
public class Sorties {
    //id_Sortie, id_Camp, id_Rand, Type, Nom, Prix, Date, Lieux
    private String id_Sortie, id_Camp, id_Rand ;
    private Double Prix;
    private Date date_Sortie;
    private String Type, Nom,Lieux;
    public Sorties(String id_Sortie,String id_Camp,String id_Rand,String Type,String Nom,Double Prix,Date date_Sortie,String Lieux){
        this.id_Sortie = id_Sortie;
        this.id_Camp=id_Camp;
        this.id_Rand=id_Rand;
        this.Type=Type;
        this.Prix=Prix;
        this.Nom=Nom;
        this.date_Sortie=date_Sortie;
        this.Lieux=Lieux;
    }

    

    public String getId_Sortie() {
        return id_Sortie;
    }
    
    public String getId_Camp() {
        return id_Camp;
    }
    
    public String getId_Rand() {
        return id_Rand;
    }
    
    public String getType(){
        return Type;
    }
    
    public String getNom() {
        return Nom;
    }
    
    public double getPrix(){
        return Prix;
    }
    
    public Date getDate_Sortie() {
        return date_Sortie;
    }
    
    public String getLieux(){
        return Lieux;
    }

    public void setId_Sortie(String id_Sortie) {
        this.id_Sortie = id_Sortie;
    }
    
     public void setId_Camp(String id_Camp) {
        this.id_Camp = id_Camp;
    }
     
      public void setId_Rand(String id_Rand) {
        this.id_Rand = id_Rand;
    }
      
    public void setType(String Type) {
        this.Type = Type;
    }
      
    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    
    public void setPrix(double Prix){
        this.Prix=Prix;
    }

    public void setDate_Sortie(Date date_Sortie) {
        this.date_Sortie = date_Sortie;
    }

    public void setPeriode(String Lieux){
        this.Lieux=Lieux;
    }
    //id_Sortie, id_Camp, id_Rand, Type, Nom, Prix, Date, Lieux

    @Override
    public String toString() {
        return "Camping { " + " Id Sortie = " + id_Sortie + " Id Camping = " + id_Camp + " Id Randonnee = " + id_Rand + "Type" + Type + 
                ",  Nom = " + Nom + ", Date Sortie = " + date_Sortie + ", Prix ="+ Prix + ", Lieux = "+ Lieux + '}';
    }
    
    
}
