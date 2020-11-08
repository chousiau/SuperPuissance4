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
public class Grille {
    Cellule [][]Cellules = new Cellule[6][7];
    
    public Grille(){
        
        for (int i = 0; i<6; i++){
            for (int j = 0; j<7; j++){
                Cellules[i][j]=new Cellule(null,false,false);
            }
        }
    }

    
    public boolean ajouterJetonDansColonne(Partie unePartie, Jeton unJeton, int colonne){
        int ligne = 0;  //Définie la ligne dans laquel le jeton sera ajouté
        
        //On parcours les lignes de bas en haut jusqu'a en trouver une vide
        while (ligne<6){
            
            //Cas ou l'affectation du jeton est possible
            if (Cellules[5-ligne][colonne-1].affecterJeton(unJeton)){
                
                //Cas où le jeton est affecté à une case contenant un trou noir et un désintégrateur
                if (Cellules[5-ligne][colonne-1].presenceTrouNoir() && Cellules[5-ligne][colonne-1].presenceDesintegrateur() ){
                    Cellules[5-ligne][colonne-1].activerTrouNoir();
                    Cellules[5-ligne][colonne-1].recupererDesintegrateur();
                    unePartie.JoueurCourant.nombreDesintegrateur++;
                    System.out.println("Oh non ! ton jeton a été aspiré par un trou noir ;)."
                            + " Oh ! Un désintégrateur était caché derrière!");
                }
                
                //Cas où le jeton est affecté à une case avec un trou noir seul
                if (Cellules[5-ligne][colonne-1].presenceTrouNoir()){
                    System.out.println("Oh non ! ton jeton a été aspiré par un trou noir ;).");
                    Cellules[5-ligne][colonne-1].activerTrouNoir();   
                }
                
                //Cas où le jeton est affecté à une case avec un désintégrateur seul
                if (Cellules[5-ligne][colonne-1].presenceDesintegrateur()){
                    Cellules[5-ligne][colonne-1].recupererDesintegrateur();
                    unePartie.JoueurCourant.nombreDesintegrateur++;
                    System.out.println("Tu as gagné un désintégrateur.");
                }
 

                
                return true;
            }
            //Cas ou l'affectation du jeton n'est pas possible, on incrémente la ligne
            else{
                ligne+=1;
            }
        }
        return false;
    }
    public boolean etreRemplie(){
        for (int j =0;j<7;j++){
            if (Cellules[0][j].JetonCourant==null){
                return false;
            }
        }
        return true;             
    }
    
    //Cette méthode réinitialise la grille
    public void viderGrille(){
        for (int i = 0; i<6; i++){
            for (int j = 0; j<7; j++){
                Cellules[i][j]=new Cellule(null,false,false);
            }
        }
    }
    
    public void afficherGrilleSurConsole(){
        String Text="";
        for (int i = 0; i<6; i++){
            for (int j = 0; j<7; j++){
                
                //Cas trou noir + désintégrateur ou trou noir seul
                if ((Cellules[i][j].trouNoir && Cellules[i][j].desintegrateur)
                     ||(Cellules[i][j].trouNoir)){
                    Text+="[O]";
                }
                
                //Cas désintégrateur seul
                else if (Cellules[i][j].desintegrateur){
                    Text+="[\u001B[36m"+"D"+"\u001B[0m]";
                }
                
                //Cas case vide
                else if (Cellules[i][j].JetonCourant==null){
                    Text+="[ ]";
                }
                
                //cas case contenant un jeton
                else{
                    Text+="["+Cellules[i][j].lireCouleurDuJeton()+"]";
                }
                
                //Ajout des numéros de ligne 
                if (j == 6){
                    Text+= " " + (i+1);
                }
            }
            Text+="\n";
        }
        //Ajout des numéros de colonne
        System.out.println(" 1  2  3  4  5  6  7");
        System.out.println(Text);
    }
    public boolean celluleOccupee(int coor1, int coor2){
        return Cellules[coor1-1][coor2-1] != null;
    }
    public String lireCouleurDuJeton(int coor1, int coor2){
        if (Cellules[coor1-1][coor2-1] != null){
            return Cellules[coor1-1][coor2-1].JetonCourant.lireCouleur();
        }
        else{
            return "case innocupée";
        }
    }
    public boolean etreGagnantePourJoueur(Joueur unJoueur){
        //Définition d'un compteur pour compter le nombre de jetons d'affilés
        int compteur = 0;
        
        //On parcours les colonnes
        for (int i = 0; i<6; i++){
            for (int j = 0; j<6;j++){
                
                //On vérifie d'abord si les deux cases comparées contiennent un jeton
                // car on compare uniquement des coueleures par la suite et 
                //il n'est pas possible de comparer une couleur avec une case vide
                if (Cellules[i][j].JetonCourant!=null && Cellules[i][j+1].JetonCourant!=null){
                   

                    if (Cellules[i][j].JetonCourant.Couleur.equals(unJoueur.couleur)
                        &&Cellules[i][j+1].JetonCourant.Couleur.equals(unJoueur.couleur)){
                        compteur += 1;
                        if (compteur == 3){
                            return true;
                        }
                    }
                    else{
                        compteur = 0;
                    }
                }
                
                else{compteur = 0;}
            }
            
            compteur = 0;
        }
        
        //On parcours les lignes
        
        //Réinitialisation du compteur
        compteur = 0;
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 5; i++)
                                //On vérifie d'abord si les deux cases comparées contiennent un jeton
                // car on compare uniquement des coueleures par la suite et 
                //il n'est pas possible de comparer une couleur avec une case vide
                if (Cellules[i][j].JetonCourant!=null && Cellules[i+1][j].JetonCourant!=null){
                   

                    if (Cellules[i][j].JetonCourant.Couleur.equals(unJoueur.couleur)
                        &&Cellules[i+1][j].JetonCourant.Couleur.equals(unJoueur.couleur)){
                        compteur += 1;
                        if (compteur == 3){
                            return true;
                        }
                    }
                    else{
                        compteur = 0;
                    }
                }
            compteur = 0;
            }
        
        //On parcours les diagonales déscendantes
        
        /*On défini un point de départ à partir duquel on déscend en diagonale en 
        faisant varrier i et j. (Point de départ noté P sur le schéma)
        [P][ ][ ][P][ ][ ][ ]
        [ ][\][ ][ ][\][ ][ ]
        [P][ ][\][ ][ ][\][ ]
        [ ][\][ ][\][ ][ ][\]
        [ ][ ][\][ ][\][ ][ ]
        [ ][ ][ ][\][ ][\][ ]
        En déplaçant P le long de la colonne de gauche, puis le long de la ligne
        du haut, on peut couvrir toute les cases (il n'est pas nécessaire de 
        contrîler les cases sur les quelles on ne peut pas faire rentrer 4 jetons
        en diagonale)*/
        int iDepart = 0;
        int jDepart = 0;
        int i = 0;
        int j = 0;
        compteur = 0;
        //On parcourt dans un premier temps la colonne de gauche avec P. Il n'est
        //pas nécessaire d'aller au dela de la 3è ligne car après cette ligne 
        // on ne peux pas caser 4 jeton sur la diagonale déscendante
        
        while (iDepart<3){
            
            //Cette première condition permet de s'arrêter quand on atteint le
            //bord du plateau
            while ((i+iDepart)<5 && (j+jDepart)<6){
                
                //Cette condition permet de vérifier que les deux cases comparées
                //contiennent bien un jeton afin d'éviter des problèmes par la suite
                if (Cellules[iDepart+i][jDepart+j].JetonCourant!=null && Cellules[iDepart+i+1][jDepart+j+1].JetonCourant!=null){
                    
                    //Cette condition compare un jeton à son suivant dans la diagonale
                    if (Cellules[iDepart + i][jDepart + j].JetonCourant.Couleur.equals(unJoueur.couleur)
                            && Cellules[iDepart + i + 1][jDepart + j + 1].JetonCourant.Couleur.equals(unJoueur.couleur)) {
                        compteur += 1; // si les deux jetons sont identiques, on implémente
                        if (compteur == 3) { //Si le compteur atteint 3, la condition de victoire est remplie
                            return true;
                        } 
                    }else {
                        compteur = 0;//Si les deux jetons sont différents, le compteur retourne à 0
                        }
                }
                i++; //On incrémente i et j pour passer à la case suivante en diagonale
                j++;
            }
            i=0;
            j=0;
            compteur = 0;
            iDepart++;
        }
        
        //On parcourt maintenant la ligne du haut avec P.
        //A partir de la cellule d'indice [0][3], on ne peut plus caller 
        //4 jetons en diagonale déscendante. Il n'est donc pas nécessaire de
        //vérifier après cette cellule
        
        //Réinitialisation des variables
        iDepart = 0;
        jDepart = 0;
        i=0;
        j=0;
        compteur = 0;
        while (jDepart < 4) {

            //Cette première condition permet de s'arrêter quand on atteint le
            //bord du plateau
            while ((i + iDepart) < 5 && (j + jDepart) < 6) {

                //Cette condition permet de vérifier que les deux cases comparées
                //contiennent bien un jeton afin d'éviter des problèmes par la suite
                if (Cellules[iDepart + i][jDepart + j].JetonCourant != null && Cellules[iDepart + i + 1][jDepart + j + 1].JetonCourant != null) {

                    //Cette condition compare un jeton à son suivant dans la diagonale
                    if (Cellules[iDepart + i][jDepart + j].JetonCourant.Couleur.equals(unJoueur.couleur)
                            && Cellules[iDepart + i + 1][jDepart + j + 1].JetonCourant.Couleur.equals(unJoueur.couleur)) {
                        compteur += 1; // si les deux jetons sont identiques, on implémente
                        if (compteur == 3) { //Si le compteur atteint 3, la condition de victoire est remplie
                            return true;
                        }
                    } else {
                        compteur = 0;//Si les deux jetons sont différents, le compteur retourne à 0
                    }
                }
                i++; //On incrémente i et j pour passer à la case suivante en diagonale
                j++;
            }
            i = 0;
            j = 0;
            compteur = 0;
            jDepart++;
        }
        
        
        
        //On parcours les diagonales montantes
        
        //Pour les diagonales montantes on déplace P le long de la colone de gauche
        //Et de la ligne du bas
        iDepart = 5;
        jDepart = 0;
        i=0;
        j=0;
        compteur = 0;
        //Ici P est initialement dans le coins en bas à gauche, et on rempnte en 
        //premier le long de la colone de gauche jusqu'à atteindre la cellule [3][0]
        
        //On parcours la colone de gauche de bas en haut
        while (iDepart>2){
            
            //Cette première condition permet de s'arrêter quand on atteint le
            //bord du plateau
            while ((i+iDepart)>0 && (j+jDepart)<6){
                
                //Cette condition permet de vérifier que les deux cases comparées
                //contiennent bien un jeton afin d'éviter des problèmes par la suite
                if (Cellules[iDepart+i][jDepart+j].JetonCourant!=null && Cellules[iDepart+i-1][jDepart+j+1].JetonCourant!=null){
                    
                    //Cette condition compare un jeton à son suivant dans la diagonale
                    if (Cellules[iDepart + i][jDepart + j].JetonCourant.Couleur.equals(unJoueur.couleur)
                            && Cellules[iDepart + i - 1][jDepart + j + 1].JetonCourant.Couleur.equals(unJoueur.couleur)) {
                        compteur += 1; // si les deux jetons sont identiques, on implémente
                        if (compteur == 3) { //Si le compteur atteint 3, la condition de victoire est remplie
                            return true;
                        } 
                    }else {
                        compteur = 0;//Si les deux jetons sont différents, le compteur retourne à 0
                        }
                }
                i--; //On décrémente i et on incrémente j pour passer à la case suivante en diagonale
                j++;
                
            }
            iDepart--;
            compteur = 0;
            i=0;
            j=0;
            
        }
        
        //On parcours maintenant la ligne du bas avec P
        
        //Réinitialisation des variables
        iDepart = 5;
        jDepart = 0;
        i=0;
        j=0;
        compteur = 0;
        while (jDepart < 3) {

            //Cette première condition permet de s'arrêter quand on atteint le
            //bord du plateau
            while ((i + iDepart) > 0 && (j + jDepart) < 6) {

                //Cette condition permet de vérifier que les deux cases comparées
                //contiennent bien un jeton afin d'éviter des problèmes par la suite
                if (Cellules[iDepart + i][jDepart + j].JetonCourant != null && Cellules[iDepart + i - 1][jDepart + j + 1].JetonCourant != null) {

                    //Cette condition compare un jeton à son suivant dans la diagonale
                    if (Cellules[iDepart + i][jDepart + j].JetonCourant.Couleur.equals(unJoueur.couleur)
                            && Cellules[iDepart + i - 1][jDepart + j + 1].JetonCourant.Couleur.equals(unJoueur.couleur)) {
                        compteur += 1; // si les deux jetons sont identiques, on implémente
                        if (compteur == 3) { //Si le compteur atteint 3, la condition de victoire est remplie
                            return true;
                        }
                    } else {
                        compteur = 0;//Si les deux jetons sont différents, le compteur retourne à 0
                    }
                }
                i--; //On décrémente i et on incrémente j pour passer à la case suivante en diagonale
                j++;
            }
            jDepart++;
            compteur = 0;
            i=0;
            j=0;
        }    
        return false;
    }
    
    public Jeton recupererJeton(int coor1,int coor2){
        //On initialise le jeton, car NetBean ne peut pas vérifier que le programme 
        //passera à coup sûr dans la "if" où il est réellement initialisé.
        Jeton JetonArenvoyer;
        JetonArenvoyer = Cellules[coor1][coor2].JetonCourant;
        Cellules[coor1][coor2].JetonCourant = null;
        
        
        //Variables permettant de récupérer les coordonnées du jeton visé


        return JetonArenvoyer;   
        
    }
    public void tasserGrille(int coor1, int coor2){
        Jeton JetonTemp; //Utilisé pour stocker un jeton temporairement dans la boucle
        if (Cellules[coor1][coor2].JetonCourant == null){
            for (int i = coor1-1; i>=0; i--){
                JetonTemp = Cellules[i][coor2].recupererJeton();//Récupération du jeton dans la case n+1 (-1 ici car i va de haut en bas)
                Cellules[i][coor2].JetonCourant = null;//Suppression du jeton dans la case n+1
                Cellules[i+1][coor2].affecterJeton(JetonTemp);//Affectation du jeton dans la case n
            }
        }
    }
    
    public boolean colonneRemplie(int colonne){
        return Cellules[0][colonne - 1].JetonCourant != null;
    }
    
    public boolean placerDesintegrateur(int coor1, int coor2){
        if (!Cellules[coor1][coor2].presenceDesintegrateur()){
            Cellules[coor1][coor2].placerDesintegrateur();
            return true;
        }
        else{
            return false;
        }
    }
    //Override toString à des fins de test du code
    /*@Override
    public String toString(){
        
    }*/
    
}
