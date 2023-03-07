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
import java.sql.Date;

public class Publication {

    int ID;
    String contenu, iduser, urlimage;
    Date date_publication;
   

    public Publication(String contenu, String iduser, String urlimage, Date date_publication) {
        this.contenu = contenu;
        this.iduser = iduser;
        this.urlimage = urlimage;
        this.date_publication = date_publication;
    }

    public Publication(int ID, String contenu, String iduser, String urlimage, Date date_publication) {
        this.ID = ID;
        this.contenu = contenu;
        this.iduser = iduser;
        this.urlimage = urlimage;
        this.date_publication = date_publication;
    }

    public Publication() {
    }

   

    public Date getDate() {
        return date_publication;
    }

    public void setDate(Date date) {
        this.date_publication = date;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String Contenu) {
        this.contenu = Contenu;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String Iduser) {
        this.iduser = Iduser;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String image) {
        this.urlimage = image;
    }
    @Override
    public String toString() {
        return "publication{" + "id=" + ID + ", contenu=" + contenu + ", iduser=" + iduser + ", urlimage=" + urlimage+ " , date publication="+date_publication+'}';
    }

}


