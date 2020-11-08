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
       nombreDesintegrateur = 0;
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
    //toString modifié à des fins de test 
    @Override
    public String toString(){
        String txt;
        txt ="["+ListeJetons[0].lireCouleur();
        for (int i = 1; i<21; i++){
            txt+=", "+ListeJetons[i].lireCouleur()+" ";
        }
        txt+="]";
        return txt;
        
    }
    public void obtenirDesintegrateur(){
        nombreDesintegrateur +=1;
    }
    
    public boolean utiliserDesintegrateur(){
        if (nombreDesintegrateur == 0){
            ;
            return false;
        }
        else{
            nombreDesintegrateur--;
            return true;
        }
        
    }
    
}
