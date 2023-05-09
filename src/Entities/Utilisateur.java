/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Amine
 */


//import org.apache.commons.codec.digest.DigestUtils;
public class Utilisateur {
    private int idUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String emailUtilisateur;
    private String loginUtilisateur;
    private String mot_de_passeUtilisateur;
    private String imgUtilisateur;
    private int rankUtilisateur;
    private String numero_telephoneUtilisateur;
    private String adresseUtilisateur;

    public Utilisateur() {
    }
    /*Constructor with no ID */

    public Utilisateur(String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String loginUtilisateur, String mot_de_passeUtilisateur, String imgUtilisateur, int rankUtilisateur, String numero_telephoneUtilisateur, String adresseUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.loginUtilisateur = loginUtilisateur;
        this.mot_de_passeUtilisateur = mot_de_passeUtilisateur;
        this.imgUtilisateur = imgUtilisateur;
        this.rankUtilisateur = rankUtilisateur;
        this.numero_telephoneUtilisateur = numero_telephoneUtilisateur;
        this.adresseUtilisateur = adresseUtilisateur;
    }

    /*Constructor with ID */

    public Utilisateur(int idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String loginUtilisateur, String mot_de_passeUtilisateur, String imgUtilisateur, int rankUtilisateur, String numero_telephoneUtilisateur, String adresseUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.loginUtilisateur = loginUtilisateur;
        this.mot_de_passeUtilisateur = mot_de_passeUtilisateur;
        this.imgUtilisateur = imgUtilisateur;
        this.rankUtilisateur = rankUtilisateur;
        this.numero_telephoneUtilisateur = numero_telephoneUtilisateur;
        this.adresseUtilisateur = adresseUtilisateur;
    }

    /*Getter*/

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public String getLoginUtilisateur() {
        return loginUtilisateur;
    }

    public String getMot_de_passeUtilisateur() {
        return mot_de_passeUtilisateur;
    }

    public String getImgUtilisateur() {
        return imgUtilisateur;
    }

    public String getAdresseUtilisateur() {
        return adresseUtilisateur;
    }

    public int getRankUtilisateur() {
        return rankUtilisateur;
    }

    public String getNumero_telephoneUtilisateur() {
        return numero_telephoneUtilisateur;
    }
    /*Setter*/

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public void setLoginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
    }

//    public void setMot_de_passeUtilisateur(String mot_de_passeUtilisateur) {
//        this.mot_de_passeUtilisateur = DigestUtils.sha1Hex(mot_de_passeUtilisateur);
//    }

    public void setImgUtilisateur(String imgUtilisateur) {
        this.imgUtilisateur = imgUtilisateur;
    }

    public void setAdresseUtilisateur(String adresseUtilisateur) {
        this.adresseUtilisateur = adresseUtilisateur;
    }

    public void setRankUtilisateur(int rankUtilisateur) {
        this.rankUtilisateur = rankUtilisateur;
    }

    public void setNumero_telephoneUtilisateur(String numero_telephoneUtilisateur) {
        this.numero_telephoneUtilisateur = numero_telephoneUtilisateur;
    }
    /*toString()*/

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur='" + idUtilisateur + '\'' +
                ", nomUtilisateur='" + nomUtilisateur + '\'' +
                ", prenomUtilisateur='" + prenomUtilisateur + '\'' +
                ", emailUtilisateur='" + emailUtilisateur + '\'' +
                ", loginUtilisateur='" + loginUtilisateur + '\'' +
                ", mot_de_passeUtilisateur='" + mot_de_passeUtilisateur + '\'' +
                ", imgUtilisateur='" + imgUtilisateur + '\'' +
                ", rankUtilisateur='" + rankUtilisateur + '\'' +
                ", numero_telephoneUtilisateur='" + numero_telephoneUtilisateur + '\'' +
                ", adresseUtilisateur='" + adresseUtilisateur + '\'' +
                '}';
    }
}
