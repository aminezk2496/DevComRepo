/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication1;

/**
 *
 * @author ASUS
 */
public class voiture {
    private int id;
    private String serie;
    private String marque ;
    private int nb_chv;
    private String type;

    public voiture() {
    }

    public voiture(int id, String serie, String marque, int nb_chv, String type) {
        this.id = id;
        this.serie = serie;
        this.marque = marque;
        this.nb_chv = nb_chv;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getNb_chv() {
        return nb_chv;
    }

    public void setNb_chv(int nb_chv) {
        this.nb_chv = nb_chv;
    }

    public String getType() {
        return type;
    }   

    public void setType(String type) {
        this.type = type;
    }
    
}
