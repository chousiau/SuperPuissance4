/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_puissance_4_duponcel_housiau;

import java.util.Random;

/**
 *
 * @author Charl
 * 
 */
public class Partie {
    Joueur []ListeJoueurs = new Joueur[2];
    Grille GrilleDeJeu;
    Joueur JoueurCourant;

    public void attribuerCouleurAuxJoueurs(Joueur Joueur1, Joueur Joueur2){
        String Couleur1 = "\u001B[31m O \u001B[0m"; //Couleur rouge
        String Couleur2 = "\u001B[34m O \u001B[0m"; //Couleur bleu
        
        Random r = new Random();
        int attribution = r.nextInt(2);
        if (attribution == 0){
            Joueur1.couleur = Couleur1;
            Joueur2.couleur = Couleur2;
        }
        else{
            Joueur1.couleur = Couleur2;
            Joueur2.couleur = Couleur1;
                   
        }
        
        //int choix=Random.nextInt
        
    }
    public void initialiserPartie(Joueur Joueur1, Joueur Joueur2){
        
        //Création ou réinitialisation de la grille de jeu
        
        //Création
        if (this.GrilleDeJeu == null){
            GrilleDeJeu = new Grille();
        }
        
        //Réinitialisation
        else{
            GrilleDeJeu.viderGrille();
        }
        
        //attribution des jetons
        for (int i = 0; i<21; i++){
            //Il est possible qu'un joueur possède encore des jetons de sa partie
            //précédente. Cepdant, on peut effectuer la boucle 21 fois sans se
            //soucier de cela car si le tableau de jeton est plein, la fonction
            // ajouterJeton renverra juste false et n'entrainera pas d'erreur
            
            Joueur1.ajouterJeton(new Jeton(Joueur1.couleur));
            Joueur2.ajouterJeton(new Jeton(Joueur2.couleur));
        }
        
    }
   
}
