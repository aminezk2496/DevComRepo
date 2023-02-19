package Entities;

public class Reclamation {
    int id_reclamation ;
    String nom_utilisateur;
    String prenom_utilisateur;
    String email_utilisateur;
    String description_reclamation;
    String etat_reclamation;
    Utilisateur utilisateur;

    public Reclamation()
    {

    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id_reclamation=" + id_reclamation +
                ", nom_utilisateur='" + nom_utilisateur + '\'' +
                ", prenom_utilisateur='" + prenom_utilisateur + '\'' +
                ", email_utilisateur='" + email_utilisateur + '\'' +
                ", description_reclamation='" + description_reclamation + '\'' +
                ", etat_reclamation='" + etat_reclamation + '\'' +
                ", utilisateur=" + utilisateur +
                '}';
    }

    public Reclamation(String nom_utilisateur, String prenom_utilisateur, String email_utilisateur, String description_reclamation, String etat_reclamation, Utilisateur utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.email_utilisateur = email_utilisateur;
        this.description_reclamation = description_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.utilisateur = utilisateur;
    }

    public Reclamation(int id_reclamation, String nom_utilisateur, String prenom_utilisateur, String email_utilisateur, String description_reclamation, String etat_reclamation, Utilisateur utilisateur) {
        this.id_reclamation = id_reclamation;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.email_utilisateur = email_utilisateur;
        this.description_reclamation = description_reclamation;
        this.etat_reclamation = etat_reclamation;
        this.utilisateur = utilisateur;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getPrenom_utilisateur() {
        return prenom_utilisateur;
    }

    public void setPrenom_utilisateur(String prenom_utilisateur) {
        this.prenom_utilisateur = prenom_utilisateur;
    }

    public String getEmail_utilisateur() {
        return email_utilisateur;
    }

    public void setEmail_utilisateur(String email_utilisateur) {
        this.email_utilisateur = email_utilisateur;
    }

    public String getDescription_reclamation() {
        return description_reclamation;
    }

    public void setDescription_reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    public String getEtat_reclamation() {
        return etat_reclamation;
    }

    public void setEtat_reclamation(String etat_reclamation) {
        this.etat_reclamation = etat_reclamation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
