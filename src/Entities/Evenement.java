/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author Amal
 */
public class Evenement {

    private int id_event;
    private String titre_event;
    private String date_debut_event;
    private String date_fin_event;
    private String prix_event;
    private String lieu_event;
    private String desc_event;
    private String image_event;
    private int id_evenement_type;
    public Evenement(/*Integer id_event,String titre_event,String date_event,String prix_event,String lieu_event,String desc_event,String image_event*/) {
        //super();
        super();
       
    }
    public Evenement(Integer id_event,String titre_event,String date_debut_event,String date_fin_event,String prix_event,String lieu_event,String desc_event,String image_event,Integer id_evenement_type) {
       
        this.id_event = id_event;
         this.titre_event = titre_event;
        this.date_debut_event= date_debut_event;
        this.date_fin_event = date_fin_event;
        this.prix_event = prix_event;
        this.lieu_event = lieu_event;
        this.desc_event = desc_event;
        this.image_event = image_event;
        this.id_evenement_type = id_evenement_type;
    }
    public Integer getIdEvent() {
        
        return id_event;
    }
    public void setIdEvent(Integer id_event) {
        this.id_event = id_event;
    }
    public int IdEventProperty() {
        return id_event ;
    }
    
    public String getTitreEvent() {
        return titre_event;
    }
    public void setTitreEvent(String titre_event) {
        this.titre_event = titre_event;
    }
    public String getDateDebutEvent() {
        return date_debut_event;
    }
    public void setDateDebutEvent(String date_debut_event) {
        this.date_debut_event = date_debut_event;
    }
    public String getDateFinEvent() {
        return date_fin_event;
    }
    public void setDateFinEvent(String date_fin_event) {
        this.date_fin_event = date_fin_event;
    }
    
    public String getPrixEvent() {
        return prix_event;
    }
    public void setPrixEvent(String prix_event) {
        this.prix_event = prix_event;
    }
    
    public String getLieuEvent() {
        return lieu_event;
    }
    public void setLieuEvent(String lieu_event) {
        this.lieu_event = lieu_event;
    }
    
    public String getDescEvent() {
        return desc_event;
    }
    public void setDescEvent(String desc_event) {
        this.desc_event = desc_event;
    }
    
    public String getImageEvent() {
        return image_event;
    }
    public void setImageEvent(String image_event) {
        this.image_event = image_event;
    }
    
    public Integer getEventType() {
        
        return id_evenement_type;
    }
    public void setEventType(Integer id_evenement_type) {
        this.id_evenement_type = id_evenement_type;
    }
    

    /*public static void CreateTable() throws ClassNotFoundException, SQLException {
         String sql = "CREATE TABLE IF NOT EXISTS evenement (\n"
                 + "	id_event integer PRIMARY KEY,\n"
                 + "	titre_event varchar(50) NOT NULL,\n"
                 + "	date_event DATETIME NOT NULL,\n"
                 + "	prix_event varchar(50) NOT NULL,\n"
                 + "	lieu_event varchar(50) NOT NULL,\n"
                 + "	desc_event text NOT NULL,\n"
                 + "	image_event varchar(100) NOT NULL\n"
                + ");CREATE TABLE `discovertn`.`evenement_type` ( `id_evenement_type` INT NOT NULL AUTO_INCREMENT , `nom_evenement_type` VARCHAR(30) NOT NULL , PRIMARY KEY (`id_evenement_type`)) ";
        Connection connexion = (Connection) Connexion.getConnexion();
        java.sql.Statement stm = connexion.createStatement();
        boolean res = stm.execute(sql);
    }*/

    
}
