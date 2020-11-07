/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_puissance_4_duponcel_housiau;



import java.util.Scanner;

/**
 *
 * @author Charl
 */
public class Projet_puissance_4_DUPONCEL_HOUSIAU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        //Création et initialisation des variables et objets
        //----------------------------------------------------------------------
        
        //Création d'un objet scanner pour récupérer le nom des joueurs
        Scanner sc = new Scanner(System.in);
        
        //Joueur 1 
        System.out.println("Saisir le nom du joueur 1");
        Joueur Joueur1 = new Joueur(sc.nextLine());
        
        //Joueur 2
        System.out.println("saisir le nom du joueur 2");
        Joueur Joueur2 = new Joueur(sc.nextLine());
        
        //Création de la partie
        Partie game = new Partie();
        game.ListeJoueurs[0] = Joueur1;
        game.ListeJoueurs[1] = Joueur2;
                
        
        //Lancement de la partie
        //----------------------------------------------------------------------
        
        //On fait une boucle pour que les joueurs puisse rejouer
        int Reponse = 1; //Permet au joueur de choisir de rejouer ou  non
        while (Reponse == 1){
            
            game.attribuerCouleurAuxJoueurs(Joueur1, Joueur2);
            game.initialiserPartie();
            game.debuterPartie();
        
        //Conclusion de la partie 
        //----------------------------------------------------------------------
            if (game.JoueurCourant != null){
                System.out.println("VICTOIRE DE "+game.JoueurCourant.nom.toUpperCase());
                game.GrilleDeJeu.afficherGrilleSurConsole();
                System.out.println("Souhaitez vous rejouer ? (1 : oui | 0 : non)");
                Reponse = sc.nextInt();
            }
            else{
                System.out.println("MATCH NUL");
                game.GrilleDeJeu.afficherGrilleSurConsole();
                System.out.println("Souhaitez vous rejouer ? (1 : oui | 0 : non)");
                Reponse = sc.nextInt();
            }
        }
        System.out.println("Merci d'avoir joué !");
    } 
}
