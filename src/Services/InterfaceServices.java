/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

/**
 *
 * @author Gio・ブランドー
 */
import Entities.Hebergement;
import java.util.List;

public interface InterfaceServices {
    public void ajouterpHeber(Hebergement h);
    public void ajouterHeber (Hebergement h);
    public void modifierHeber(Hebergement h);
    public void supprimerHeber(int id);
    public List <Hebergement> afficherhebergement();
}
