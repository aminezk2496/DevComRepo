/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author karra
 */
public class Facture {
    
    private int Id_Facture,Id_C, Id_Res,quantie;
    private String etat;
    Date date_facture;
    private double prix;

    public Facture(int Id_Facture,int Id_C,int Id_Res, Date date_facture,int quantie,double prix, String etat) {
        this.Id_Facture = Id_Facture;
        this.Id_C=Id_C;
        this.Id_Res=Id_Res;
        this.etat = etat;
        this.date_facture = date_facture;
        this.quantie=quantie;
        this.prix=prix;
    }

    

    public int getId_Facture() {
        return Id_Facture;
    }
    
    public int getId_C() {
        return Id_C;
    }
    
    public int getId_Res() {
        return Id_Res;
    }

    public String getEtat() {
        return etat;
    }

    public Date getDate_facture() {
        return date_facture;
    }

    public int getQuantite(){
        return quantie;
    }

    public double getPrix(){
        return prix;
    }

    public void setId_Facture(int Id_Facture) {
        this.Id_Facture = Id_Facture;
    }
    
    public void setId_C(int Id_C) {
        this.Id_C = Id_C;
    }
    
    public void setId_Res(int Id_Res) {
        this.Id_Res = Id_Res;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setQuantite(int quantie){
        this.quantie=quantie;
    }

    public void setPrix(double prix){
        this.prix=prix;
    }

    public void setDate_facture(Date date_facture) {
        this.date_facture = date_facture;
    }

    @Override
    public String toString() {
        return "Facture{" + "Id_Facture=" + Id_Facture + "Id_C=" + Id_C +"Id_Res=" + Id_Res +", date_facture=" + date_facture +", etat=" + etat +  '}';
    }
    
    
}
