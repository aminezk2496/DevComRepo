/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import java.util.*;

/**
 *
 * @author Gio・ブランドー
 */
public class Hebergement {
    private int id, disponibilite,contact_heber;
    private float prix_heber;
    private String nom_heber,description_heber,localisation_heber, type_heber;

    public Hebergement(int id,String nom,String desc, int dispo, float prix, String loca, int contact, String type) {
        this.id= id;
        this.nom_heber = nom;
        this.description_heber = desc;
        this.disponibilite =dispo;
        this.prix_heber = prix;
        this.localisation_heber = loca;
        this.contact_heber = contact;
        this.type_heber = type;
    }


    @Override
    public String toString() {
        return "Hebergement{" + "Identifiant=" + id + ", Nom=" + nom_heber + ", Description=" + description_heber +", Nombre de Chambre=" +disponibilite+ ", Prix=" +prix_heber+ ", Localisation=" +localisation_heber+ ", Image=" +contact_heber+ ", Type=" +type_heber+'}';
    }

    

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_heber() {
		return nom_heber;
	}

	public void setNom_heber(String nom_heber) {
		this.nom_heber = nom_heber;
	}

	public int getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(int disponibilite) {
		this.disponibilite = disponibilite;
	}

	public float getPrix_heber() {
		return prix_heber;
	}

	public void setPrix_heber(int prix_heber) {
		this.prix_heber = prix_heber;
	}


	public String getDescription_heber() {
		return description_heber;
	}

	public void setDescription_heber(String description_heber) {
		this.description_heber = description_heber;
	}

	public String getLocalisation_heber() {
		return localisation_heber;
	}

	public void setLocalisation_heber(String localisation_heber) {
		this.localisation_heber = localisation_heber;
	}

	public int getContact_heber() {
		return contact_heber;
	}

	public void setContact_heber(int contact_heber) {
		this.contact_heber = contact_heber;
	}

	public String getType_heber() {
		return type_heber;
	}

	public void setType_heber(String type_heber) {
		this.type_heber = type_heber;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.nom_heber);
        return hash;
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
        final Hebergement other = (Hebergement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom_heber, other.nom_heber)) {
            return false;
        }
        return true;
    }
    
}
