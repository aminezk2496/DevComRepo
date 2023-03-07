/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.voiture;

/**
 *
 * @author allal
 */
public interface IvoitureCRUD {
    
     public void ajouterVoiture(voiture p);
     public void modifierVoiture(voiture p);
     public void supprimerVoiture(voiture p);
     public void afficherVoiture();
}
