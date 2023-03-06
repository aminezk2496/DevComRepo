/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author Amal
 */
public class Participation {
    private int id_Parti;
    private int id_Camp;
    private int id_Rand;
    private int Nombre;
    private int Montant;
    private String Etat;
    private String date_Parti;
    private int  id_evenement;
    private int  id_utilisateur;
    public Participation() {
        //super();
        super();
       
    }
    public Participation(Integer id_Parti,Integer id_Camp,Integer id_Rand,Integer Nombre,Integer Montant,String Etat,String date_Parti,Integer id_evenement,Integer id_utilisateur) {
        this.id_Parti = id_Parti;
        this.id_Camp = id_Camp;
        this.Nombre= Nombre;
        this.Montant= Montant;
        this.Etat= Etat;
        this.date_Parti= date_Parti;
        this.id_evenement= id_evenement;
        this.id_utilisateur= id_utilisateur;
    }
    
    public Integer getIdEvent() {
        return id_Parti;
    }
    public void setIdParti(Integer id_Parti) {
        this.id_Parti = id_Parti;
    }
    public Integer getIdCamp() {
        return id_Camp;
    }
    public void setIdCamp(Integer id_Camp) {
        this.id_Camp = id_Camp;
    }
    public Integer getIdRand() {
        return id_Rand;
    }
    public void setIdRand(Integer id_Rand) {
        this.id_Rand = id_Rand;
    }
    public Integer getNombre() {
        return Nombre;
    }
    public void setNombre(Integer Nombre) {
        this.Nombre = Nombre;
    }
    public Integer getMontant() {
        return Montant;
    }
    public void setMontant(Integer Montant) {
        this.Montant = Montant;
    }
    public String getEtat() {
        return Etat;
    }
    public void setEtat(String Etat) {
        this.Etat = Etat;
    }
    public String getDateParti() {
        return date_Parti;
    }
    public void setDateParti(String date_Parti) {
        this.date_Parti = date_Parti;
    }
    public Integer getIdEvenement() {
        return id_evenement;
    }
    public void setIdEvenement(Integer id_evenement) {
        this.id_evenement = id_evenement;
    }
    public Integer getIdUtilisateurt() {
        return id_utilisateur;
    }
    public void setIdUtilisateurt(Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
}
