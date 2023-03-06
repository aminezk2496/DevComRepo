/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author fhima
 */
public class Commentaire {


     private int id;
    private String iduser;
    private String contenuc;
    private int idpub;

    public Commentaire() 
    {

    }

    public Commentaire(int id, String iduser, String contenuc, int idpub) {
        this.id = id;
        this.iduser = iduser;
        this.contenuc = contenuc;
        this.idpub = idpub;
    }

    public Commentaire(String iduser, String contenuc, int idpub) {
        this.iduser = iduser;
        this.contenuc = contenuc;
        this.idpub = idpub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getContenuc() {
        return contenuc;
    }

    public void setContenuc(String contenuc) {
        this.contenuc = contenuc;
    }

    public int getIdpub() {
        return idpub;
    }

    public void setIdpub(int idpub) {
        this.idpub = idpub;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + id + ", id utilisateur=" + iduser + ", contenuCommentaire=" + contenuc + ", idpub=" + idpub+ '}';
    }
}
    
    

