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
public class Camping {
    
    private int Periode,Nbr_PlaceC;
    private int id_Camping;
    private String Nom,Lieux,Description;
    LocalDate date_Debut,date_Fin;
    private double Prix;
    private String image;
    private String imageC;
//    id_Camping, Nom, date_Debut, date_Fin, Periode, Prix, Lieux, Description
    public Camping(int id_Camping,String Nom,LocalDate date_Debut,LocalDate date_Fin,int Periode,double Prix,String Lieux,String Description,String image,int Nbr_PlaceC,String imageC){
        this.id_Camping = id_Camping;
        this.Nom=Nom;
        this.date_Debut=date_Debut;
        this.date_Fin = date_Fin;
        this.Periode = Periode;
        this.Prix=Prix;
        this.Lieux=Lieux;
        this.Description =Description;
        this.Nbr_PlaceC=Nbr_PlaceC;
        this.image =image;
        this.imageC= imageC;
    }
 boolean check;
    

   public Camping() {
    }  
   
public boolean isCheck() {
            return check;
        }

    public int getId_Camping() {
        return id_Camping;
    }
    
    public String getNom() {
        return Nom;
    }
    
    public LocalDate getDate_Debut() {
        return date_Debut;
    }

    public LocalDate getDate_Fin() {
        return date_Fin;
    }

    public int getPeriode() {
        return Periode;
    }

    public double getPrix(){
        return Prix;
    }
    public int getNbr_PlaceC() {
        return Nbr_PlaceC;
    }
    
    public String getImage(){
        return image;
    }
    
    public String getLieux(){
        return Lieux;
    }
    
    public String getDescription(){
        return Description;
    }
    public String getImageC(){
        return imageC ;
    }

    public void setId_Camping(int id_Camping) {
        this.id_Camping = id_Camping;
    }
    
    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    
    public void setDate_Debut(LocalDate date_Debut) {
        this.date_Debut = date_Debut;
    }

    public void setDate_Fin(LocalDate date_Fin) {
        this.date_Fin = date_Fin;
    }

    public void setPeriode(int Periode){
        this.Periode=Periode;
    }

    public void setPrix(double Prix){
        this.Prix=Prix;
    }
    
    public void setNbr_PlaceC(int Nbr_PlaceC){
        this.Nbr_PlaceC=Nbr_PlaceC;
    }
    
    public void setImage (String image){
        this.image=image;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setLieux(String Lieux){
        this.Lieux = Lieux;
    }
    public void setImageC (String imageC){
        this.imageC=imageC;
    }

    @Override
    public String toString() {
        return "Camping { " + " Id Camping = " + id_Camping + ",  Nom = " + Nom +", Date Debut = " + date_Debut +", Date Fin = " + date_Fin +
                ", Periode = " + Periode + ", Prix ="+ Prix + ", Lieux = "+ Lieux + ", Description = " +Description + '}';
    }
        
}
