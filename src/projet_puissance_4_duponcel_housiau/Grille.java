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
public class Grille {
    Cellule [][]Cellules = new Cellule[6][7];
    
    public Grille(){
        
        for (int i = 0; i<6; i++){
            for (int j = 0; j<7; j++){
                Cellules[i][j]=new Cellule(null,false,false);
            }
        }
    }

    
    public boolean ajouterJetonDansColonne(Jeton unJeton, int colonne){
        int ligne = 0;
        while (ligne<6){
            if (Cellules[5-ligne][colonne-1].affecterJeton(unJeton)){
                return true;
            }
            else{
                ligne+=1;
            }
        }
        return false;
    }
    public boolean etreRemplie(){
        for (int i =0;i<7;i++){
            if (Cellules[6][i]==null){
                return false;
            }
        }
        return true;             
    }
    
    //Cette méthode réinitialise la grille
    public void viderGrille(){
        for (int i = 0; i<6; i++){
            for (int j = 0; j<7; j++){
                Cellules[i][j]=null;
            }
        }
    }
    
    public void afficherGrilleSurConsole(){
        String Text="";
        for (int i = 0; i<6; i++){
            for (int j = 0; j<7; j++){
                if (Cellules[i][j].JetonCourant==null){
                    Text+="[ ]";
                }
                else{
                    Text+="["+Cellules[i][j].lireCouleurDuJeton()+"]";
                }   
            }
            Text+="\n";
        }
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
        
        //On parcours les colones
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
        
        //On parcourt dans un premier temps la colonne de gauche avec P. Il n'est
        //pas nécessaire d'aller au dela de la 3è ligne car après cette ligne 
        // on ne peux pas caser 4 jeton sur la diagonale déscendante
        
        while (iDepart<3){
            
            
            while ((i+iDepart)<5 || (j+jDepart)<6){
                if (Cellules[iDepart+i][jDepart+j].JetonCourant!=null && Cellules[iDepart+i+1][jDepart+j].JetonCourant!=null){
                    if (Cellules[iDepart + i][jDepart + j].JetonCourant.Couleur.equals(unJoueur.couleur)
                            && Cellules[iDepart + i + 1][jDepart + j + 1].JetonCourant.Couleur.equals(unJoueur.couleur)) {
                        compteur += 1;
                        if (compteur == 3) {
                            return true;
                        } 
                    }else {
                        compteur = 0;
                        }
                }
            i++;
            j++;
            }
        }
        return false;
    }
    
}
