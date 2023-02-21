/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author fhima
 */
import java.sql.Date;

public class Devis {

    private int Num_Devis,Id_C,Id_Res,quantited;
    private double montant;
    private String etat;
    Date date_envoie;
    Date date_Validation;
  
  
    public Devis(int Num_Devis, int Id_C, int Id_Res, Date date_envoie, Date date_Validation, double montant, int quantited, String etat) {
        this.Num_Devis = Num_Devis;
        this.Id_C = Id_C;
        this.Id_Res = Id_Res;
        this.date_envoie = date_envoie;
        this.date_Validation = date_Validation;
        this.montant = montant;
        this.quantited=quantited;
        this.etat = etat;
    }


     /*public Devis(int num_Devis2, int id_C2, int id_Res2, java.util.Date date_envoie2, java.util.Date date_Validation2,
            float montant2, String etat2) {
    }*/


    public int getNum_Devis() {
        return Num_Devis;
    }
    
    public int getId_C() {
        return Id_C;
    }
    
    public int getId_Res() {
        return Id_Res;
    }

    public Date getDate_envoie() {
        return date_envoie;
    }
    
    public Date getDate_Validation() {
        return date_Validation;
    }
    
    public double getMontant(){
        return montant;
    }

    public int getQuantited(){
        return quantited;
    }
    
    public String getEtat() {
        return etat;
    }
    
    public void setNum_Devis(int Num_Devis) {
        this.Num_Devis = Num_Devis;
    }
    
    public void setId_C(int Id_C) {
        this.Id_C = Id_C;
    }
    
    public void setId_Res(int Id_Res) {
        this.Id_Res = Id_Res;
    }

    public void setDate_facture(Date date_Validation) {
        this.date_Validation = date_Validation;
    }
    
    public void setDate_envoie(Date date_envoie) {
        this.date_envoie = date_envoie;
    }
 
    
    public void setMontant(double montant){
        this.montant=montant;
    }

    public void setQuantited(int quantited){
        this.quantited=quantited;
    }
    
    public void setEtat(String etat) {
        this.etat = etat;
    }
     @Override
    public String toString() {
        return "Devis{" + "Num_Devis=" + Num_Devis + "Id_C=" + Id_C + "Id_Res=" + Id_Res + ", date_Validation=" + date_Validation + ", date_envoie=" + date_envoie + ", montant=" + montant +  ", quantite=" + quantited +", etat=" + etat + '}';
    }

}
