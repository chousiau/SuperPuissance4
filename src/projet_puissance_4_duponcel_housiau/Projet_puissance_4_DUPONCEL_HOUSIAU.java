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
public class Projet_puissance_4_DUPONCEL_HOUSIAU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Jeton unJeton = new Jeton("\u001B[31mO\u001B[0m");
        Jeton unJeton2 = new Jeton("\u001B[34mO\u001B[0m");
        Joueur Joueur1 = new Joueur("\u001B[31mO\u001B[0m");
        Joueur Joueur2 = new Joueur("\u001B[34mO\u001B[0m");
        Grille uneGrille = new Grille();
        

        uneGrille.ajouterJetonDansColonne(unJeton2, 2);
        uneGrille.ajouterJetonDansColonne(unJeton, 2);
        uneGrille.ajouterJetonDansColonne(unJeton2, 2);
        uneGrille.ajouterJetonDansColonne(unJeton2, 2);
        uneGrille.ajouterJetonDansColonne(unJeton, 2);
        
        uneGrille.ajouterJetonDansColonne(unJeton, 1);
        uneGrille.ajouterJetonDansColonne(unJeton, 1);
        uneGrille.ajouterJetonDansColonne(unJeton2, 1);
        uneGrille.ajouterJetonDansColonne(unJeton, 1);
        
        uneGrille.ajouterJetonDansColonne(unJeton, 3);
        uneGrille.ajouterJetonDansColonne(unJeton, 3);
        uneGrille.ajouterJetonDansColonne(unJeton, 3);
        
        
        uneGrille.ajouterJetonDansColonne(unJeton, 4);
        uneGrille.ajouterJetonDansColonne(unJeton2, 4);
        uneGrille.ajouterJetonDansColonne(unJeton2, 4);
        uneGrille.ajouterJetonDansColonne(unJeton, 4);
        uneGrille.ajouterJetonDansColonne(unJeton, 4);
        uneGrille.ajouterJetonDansColonne(unJeton2, 4);
        
        uneGrille.ajouterJetonDansColonne(unJeton2, 5);
        uneGrille.ajouterJetonDansColonne(unJeton2, 5);
        uneGrille.ajouterJetonDansColonne(unJeton, 5);
        uneGrille.ajouterJetonDansColonne(unJeton2, 5);
        uneGrille.ajouterJetonDansColonne(unJeton, 5);
        uneGrille.ajouterJetonDansColonne(unJeton, 5);
        
        uneGrille.ajouterJetonDansColonne(unJeton2, 6);
        uneGrille.ajouterJetonDansColonne(unJeton2, 6);
        uneGrille.ajouterJetonDansColonne(unJeton, 6);
        uneGrille.ajouterJetonDansColonne(unJeton, 6);
        uneGrille.ajouterJetonDansColonne(unJeton, 6);
        
        
        uneGrille.ajouterJetonDansColonne(unJeton2, 7);
        uneGrille.ajouterJetonDansColonne(unJeton, 7);
        uneGrille.ajouterJetonDansColonne(unJeton, 7);
        
        System.out.println(uneGrille.etreGagnantePourJoueur(Joueur1));
        System.out.println(uneGrille.etreGagnantePourJoueur(Joueur2));
        
        uneGrille.afficherGrilleSurConsole();
    
    }
    
}
