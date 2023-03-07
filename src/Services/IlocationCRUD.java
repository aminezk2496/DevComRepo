/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.location;

/**
 *
 * @author allal
 */
public interface IlocationCRUD {
    
     public void ajouterLocation(location p);
     public void modifierLocation(location p);
     public void supprimerLocation(location p);
     public void afficherLocation();
}
