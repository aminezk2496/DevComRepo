/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author Gio・ブランドー
 */
public class Reservation {
    private int id_rever, id_heber, nb_chambre, duree;
    private String date;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id_rever;
        hash = 23 * hash + this.nb_chambre;
        return hash;
    }
    
    @Override
    public String toString() {
        return "Reservation{" + "id_rever=" + id_rever + ", id_heber=" + id_heber + ", nb_chambre=" + nb_chambre + ", duree=" + duree + ", date=" + date + '}';
    }
    
    public Reservation(int id_rever, int id_heber , String date, int duree, int nb_chambre) {
        this.id_rever = id_rever;
        this.id_heber = id_heber;
        this.nb_chambre = nb_chambre;
        this.duree = duree;
        this.date = date;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        return this.id_rever == other.id_rever;
    }

    public int getId_rever() {
        return id_rever;
    }

    public void setId_rever(int id_rever) {
        this.id_rever = id_rever;
    }

    public int getId_heberR() {
        return id_heber;
    }

    public void setId_heberR(int id_heber) {
        this.id_heber = id_heber;
    }

    public int getNb_chambre() {
        return nb_chambre;
    }

    public void setNb_chambre(int nb_chambre) {
        this.nb_chambre = nb_chambre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
