/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author rihem
 */
public class Participation {
    //id_Parti, id_Camp, id_Rand, Nombre, Montant, Etat ,date_Parti
    
    private String id_Parti, id_Camp, id_Rand ;
    private int Nombre;
    private Double Montant;
    private LocalDate date_Parti;
    private String Etat,RefP,Nom;
    public Participation(){
    }
    public Participation(String id_Parti,String id_Camp,String id_Rand,int Nombre,Double Montant,String Etat,String RefP ,LocalDate date_Parti,String Nom){
        this.id_Parti = id_Parti;
        this.id_Camp=id_Camp;
        this.id_Rand=id_Rand;
        this.Nombre=Nombre;
        this.Montant=Montant;
        this.Etat=Etat;
        this.date_Parti=date_Parti;
        this.RefP=RefP;
        this.Nom=Nom;
    }

    public String getNom(){
        return Nom;
    }
    public String getRefP(){
        return RefP;
    }
    public String getId_Parti() {
        return id_Parti;
    }
    
    public String getId_Camp() {
        return id_Camp;
    }
    
    public String getId_Rand() {
        return id_Rand;
    }
    
    public int getNombre(){
        return Nombre;
    }
    
    public double getMontant(){
        return Montant;
    }
    
    public LocalDate getDate_Parti() {
        return date_Parti;
    }
    
    public String getEtat(){
        return Etat;
    }

    public void setId_Parti(String id_Parti) {
        this.id_Parti = id_Parti;
    }
    
     public void setId_Camp(String id_Camp) {
        this.id_Camp = id_Camp;
    }
     
      public void setId_Rand(String id_Rand) {
        this.id_Rand = id_Rand;
    }
      
    public void setNombre(int Nombre) {
        this.Nombre = Nombre;
    }
      
    public void setMontant(Double Montant) {
        this.Montant = Montant;
    }

    public void setDate_Parti(LocalDate date_Parti) {
        this.date_Parti = date_Parti;
    }

    public void setEtat(String Etat){
        this.Etat=Etat;
    }
    public void setRefP(String RefP){
        this.RefP = RefP;
    }
    public void setNom(String Nom){
        this.Nom=Nom;
    }
    //id_Parti, id_Camp, id_Rand, Nombre, Montant, Etat ,date_Parti

    @Override
    public String toString() {
        return "Camping { " + " Id Participation = " + id_Parti + " Id Camping = " + id_Camp + " Id Randonnee = " + id_Rand + "Nombre" + Nombre + 
                ",  Montant = " + Montant + ", Etat = " + Etat + ", date_Parti ="+ date_Parti +'}';
    }
    
}
