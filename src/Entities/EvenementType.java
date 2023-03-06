/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author Amal
 */
public class EvenementType {
    private int id_evenement_type;
    private String nom_evenement_type;
    public EvenementType() {
        //super();
        super();
       
    }
    public Integer getIdEventType() {
        return id_evenement_type;
    }
    public void setIdEventType(Integer id_evenement_type) {
        this.id_evenement_type = id_evenement_type;
    }
    
    
    public String getTitreEvent() {
        return nom_evenement_type;
    }
    public void setTitreEvent(String nom_evenement_type) {
        this.nom_evenement_type = nom_evenement_type;
    }
}
