/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_puissance_4_duponcel_housiau;

/**
 *
 * @author Charl
 */
public class Joueur {
   String nom;
   String couleur;
   Jeton []ListeJetons = new Jeton[21];
   int nombreDesintegrateur;
   int nombreJetonsRestant;
   
   public Joueur(String Nom){
       nom = Nom;
   }
   public void affecterCouleur(String Couleur){
       this.couleur = Couleur;
   }
   
   public boolean ajouterJeton(Jeton unJeton){
       for (int i = 0; i < 21 ; i++){
           if (this.ListeJetons[i] == null){
               this.ListeJetons[i] = unJeton;
               return true;
           }
       }
       return false;
   }
    
}
