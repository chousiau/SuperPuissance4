/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_puissance_4_duponcel_housiau;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

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
        String Couleur1 = "\u001B[31m"+"O"+"\u001B[0m"; //Couleur rouge
        String Couleur2 = "\u001B[34m"+"O"+"\u001B[0m"; //Couleur bleu
        
        Random r = new Random();
        int attribution = r.nextInt(2);
        if (attribution == 0){
            Joueur1.affecterCouleur(Couleur1);
            Joueur2.affecterCouleur(Couleur2);
        }
        else{
            Joueur1.affecterCouleur(Couleur2);
            Joueur2.affecterCouleur(Couleur1);
                   
        }
        
        //int choix=Random.nextInt
        
    }
    public void initialiserPartie(){
        
        //Création ou réinitialisation de la grille de jeu
        
        //Création
        if (this.GrilleDeJeu == null){
            GrilleDeJeu = new Grille();
        }
        
        //Réinitialisation
        else{
            GrilleDeJeu.viderGrille();
        }
        
        //placement des trou noirs
        int compteurTN = 0; //permet de parcourir la boucle while liée aux trous noirs
        //on utilise while et pas for bien que le nombre de trou noirs à placer
        //soit déjà connu car si un trou noir est plaçé sur un autre, le 
        //compteur n'est pas incrémenté
        int compteurTNDes = 0;// Ce compteur permet de compter le nombre de désintégrateurs
                         //placés sur des trou noirs
        int compteurDes = 0;//permet de parcourir la boucle while liée aux désintégrateurs
        Random rand = new Random();
        int coor1;
        int coor2;
        while (compteurTN != 5){
            //Coordonnées choisies au hasard
            coor1 = rand.nextInt(6);
            coor2 = rand.nextInt(7);
            
            if (GrilleDeJeu.Cellules[coor1][coor2].placerTrouNoir()){
                //Si le placement du trou noir est possible, on rajoute un désintégrateur 
                //avec deux fois
                if (compteurTNDes < 2){
                GrilleDeJeu.Cellules[coor1][coor2].placerDesintegrateur();
                compteurTNDes++;//On incrément le compteur trounoir/Désintégrateur
                }
                
                compteurTN+=1;//On incrémente le compteur trou noir
            }
        }
        //Une fois que les 5 trou noirs et les 2 désintégrateurs associés ont été
        //placés, il reste à placer les 3 désintégrateurs restants
        
        while (compteurDes<3){
            coor1 = rand.nextInt(6);
            coor2 = rand.nextInt(7);
            
            //On ne place les trois désintégrateurs restants que sur des cases
            //où il n'y a ni trou noir ni desintegrateur
            if (!GrilleDeJeu.Cellules[coor1][coor2].presenceTrouNoir() && 
                !GrilleDeJeu.Cellules[coor1][coor2].presenceDesintegrateur()){
                GrilleDeJeu.Cellules[coor1][coor2].placerDesintegrateur();
                compteurDes++;
            }
        }
        
        //attribution des jetons
        for (int i = 0; i<21; i++){
            //Il est possible qu'un joueur possède encore des jetons de sa partie
            //précédente. Cepdant, on peut effectuer la boucle 21 fois sans se
            //soucier de cela car si le tableau de jeton est plein, la fonction
            // ajouterJeton renverra juste false et n'entrainera pas d'erreur
            
            ListeJoueurs[0].ajouterJeton(new Jeton(ListeJoueurs[0].couleur));
            ListeJoueurs[1].ajouterJeton(new Jeton(ListeJoueurs[1].couleur));
        }
        
        
    }
    
    public void debuterPartie(){
        
        //Lancement de la boucle de jeu
        
        //Variables et objets
 
        boolean playing = true; //Variable permettant de mettre fin a la partie
        Scanner sc = new Scanner(System.in);//Permet de récupérer la ligne jouée
        
        int iJ1 = 20; //Permet de selectionner la bonne case du tableau de jeton de Joueur 1
        int iJ2 = 20; //Permet de selectionner la bonne case du tableau de jeton de Joueur 2
        
        HashMap selectPlayer = new HashMap();//Permet d'agir differemment dans la boucle en fonction du joueur
        selectPlayer.put(ListeJoueurs[0],iJ1);
        selectPlayer.put(ListeJoueurs[1],iJ2);
        
        int colonneSelectionnee; //Utilisée dans la boucle de jeu pour savoir quelle est la colonne sélectionnée
        
        //Acceuil des joueurs
        System.out.println("Bienvenue à toi "+ListeJoueurs[0].nom+"."
                + " Voici un de tes jetons "+ListeJoueurs[0].couleur);
        System.out.println("Bienvenue à toi "+ListeJoueurs[1].nom+"."
                + " Voici un de tes jetons "+ListeJoueurs[1].couleur);
        
        GrilleDeJeu.afficherGrilleSurConsole();
        
        //Lancement de la boucle de jeu
        while (playing){
            
            //Pour chaque occurence de la boucle while, on parcours le tableau
            //ListeJoueurs pour faire jouer les deux joueurs
            for (Joueur i : ListeJoueurs) {
                
                JoueurCourant = i; //Permet plus de clarté dans la lecture du code
                
                //Tour du joueur  -------------
                //Message pour le joueur
                System.out.println("C'est au tour de " + JoueurCourant.nom + " de jouer.\n"
                        + JoueurCourant.nom+" possède "+ JoueurCourant.nombreDesintegrateur+" désintégrateurs."
                        + " \n-Pour jouer, saisis le numéro de la colonne dans la quelle"
                        + "tu souhaites ajouter un jeton\n-Pour récupérer un jeton, tapes 8"
                        + "\n-Pour utiliser un désintégrateur, tapes 9");
                

                
                //Tour de jeu du joueur
                
                int colonneRecup;//Colonne du jeton à récupérer ou a désintégrer si l'option est utilisée
                int ligneRecup;//Ligne du jeton à récupérer ou a désintégrer
                boolean ConditionRecup = true; //Permet de rester dans la boucle s'il y a un problème avec la récupération du jeton
                
                //La boucle ci dessous permet de reproposer au joueur de jouer 
                //s'il fait une action invalide (par exemple récupérer un jeton adverse, 
                //mettre un jeton dans une colonne pleine etc...)
                do{
                    colonneSelectionnee = sc.nextInt();
                    
                    //Cas ou le joueur choisi d'utiliser un désintégrateur
                    if (colonneSelectionnee == 9){
                        //Cas ou le joueur possède au moins un désintégrateur
                        if (JoueurCourant.utiliserDesintegrateur()){
                            System.out.println("Sélectionnez la colonne du jeton à désintégrer");
                            colonneRecup = sc.nextInt() - 1;
                            System.out.println("Sélectionnez la ligne du jeton à désintégrer");
                            ligneRecup = sc.nextInt() - 1;

                            //Cas ou le joueur cible une case vide
                            if (GrilleDeJeu.Cellules[ligneRecup][colonneRecup].JetonCourant == null) {
                                System.out.println("Il n'y a pas de jeton ici. Veux-tu jouer un jeton ou sélectionner une autre case ?");
                                JoueurCourant.obtenirDesintegrateur();//Le désintégrateur n'est pas utilisé, on le rend donc au joueur
                                colonneSelectionnee = 7;//Si colonneSelectionnee reste égale à 9, cela va entrainer une erreur lors de la lecture de la conditon de la boucle
                                ConditionRecup = false; //Permet de rester dans la 
                                //boucle pour reproposer au 
                                //joueur de jouer
                                
                            } //Cas où le joueur cible son propre jeton
                            else if (JoueurCourant.couleur.equals(GrilleDeJeu.Cellules[ligneRecup][colonneRecup].JetonCourant.lireCouleur())) {
                                System.out.println("Tu ne peux pas détruire tes propres jetons. Veux-tu jouer un jeton, récupérer un jeton ou sélectionner une autre case ?");
                                colonneSelectionnee = 7;//Si colonneSelectionnee reste égale à 9, cela va entrainer une erreur lors de la lecture de la conditon de la boucle
                                JoueurCourant.obtenirDesintegrateur();//Le désintégrateur n'est pas utilisé, on le rend donc au joueur
                                ConditionRecup = false; //Permet de rester dans la 
                                //boucle pour reproposer au 
                                //joueur de jouer
                            } 
                            //Cas ou la désintégration se passe bien
                            else {
                                
                                GrilleDeJeu.Cellules[ligneRecup][colonneRecup].JetonCourant = null;
                                GrilleDeJeu.tasserGrille(ligneRecup, colonneRecup);
                                colonneSelectionnee = 7;
                                ConditionRecup = true;
                                
                            }
                        }
                        //Cas ou le joueur ne possède pas de désintégrateur
                        else{
                            System.out.println("Tu ne possèdes pas de désintégrateur. Veux tu "
                                    + "jouer un jeton ou en récupérer un ?");
                            ConditionRecup = false; //Permet de rester dans la boucle pour proposer au joueur de rejouer
                            colonneSelectionnee = 7;//Si colonneSelectionnee reste égale à 9, cela va entrainer une erreur lors de la lecture de la conditon de la boucle

                        }
                        
                    }
                    
                    //Cas où le joueur choisi de récupérer un jeton
                    else if (colonneSelectionnee == 8){ 
                        System.out.println("Sélectionnez la colonne du jeton à récupérer");
                        colonneRecup = sc.nextInt() - 1;
                        System.out.println("Sélectionnez la ligne du jeton à récupérer");
                        ligneRecup = sc.nextInt() - 1;
                        
                        //Cas pas de jeton courant
                        if (GrilleDeJeu.Cellules[ligneRecup][colonneRecup].JetonCourant == null){
                            System.out.println("Il n'y a pas de jeton ici. Veux-tu jouer un jeton ou sélectionner une autre case ?");
                            colonneSelectionnee = 7;//Si colonneSelectionnee reste égale à 8, cela va entrainer une erreur lors de la lecture de la conditon de la boucle
                            ConditionRecup = false; //Permet de rester dans la 
                                                    //boucle pour reproposer au 
                                                    //joueur de jouer
                            
                        }
                        
                        //Cas ou le jeton visé n'appartiens pas au joueur
                        else if (!JoueurCourant.couleur.equals(GrilleDeJeu.Cellules[ligneRecup][colonneRecup].JetonCourant.lireCouleur())){
                            System.out.println("Ce n'est pas bien d'essayer de voler les jetons de ton adversaire ! Veux-tu jouer un jeton ou sélectionner une autre case?");
                            colonneSelectionnee = 7;//Si colonneSelectionnee reste égale à 8, cela va entrainer une erreur lors de la lecture de la conditon de la boucle
                            ConditionRecup = false;//Maintiens la boucle
                            
                        }
                        else{
                            GrilleDeJeu.recupererJeton(ligneRecup,colonneRecup);
                            GrilleDeJeu.tasserGrille(ligneRecup, colonneRecup);
                            colonneSelectionnee = 7;
                            ConditionRecup = true;//Permet de sortir de la boucle
                        }
                        
                    }
                    
                    //Cas où le joueur choisi de jouer
                    else if (!GrilleDeJeu.colonneRemplie(colonneSelectionnee)){
                        //Ici, si le programme arrive dans la boucle et que la colonne 
                        //est à une case d'être pleine, il rempliera la colonne puis
                        //puis vérifiera la condition et estimera donc que le joueur doit rejouer
                        //Sans le break ci dessous, le programme ne fonctionne que 
                        //lorsque le joueur sélectionne une boucle déjà pleine.
                        GrilleDeJeu.ajouterJetonDansColonne(this,JoueurCourant.ListeJetons[(int) selectPlayer.get(JoueurCourant)], colonneSelectionnee);
                        break;
                    }
                    //Cas ou la colonne visée est pleine
                    else{
                        System.out.println("colonne déjà remplie, saisis-en un autre !");

                    }
                       
                }while (GrilleDeJeu.colonneRemplie(colonneSelectionnee) || !ConditionRecup);
                
                GrilleDeJeu.afficherGrilleSurConsole();//Affichage de la grille après tour

                //Le joueur a joué un jeton, on décrément iJ1 ou iJ2de 1 et on 
                //supprime un jeton de sa liste de jetons. On utilise pas 
                //l'objet selectPlayer car HashMap peut lire la valeur d'une
                //variable mais pas la modifier
                if (i == ListeJoueurs[0]){
                    i.ListeJetons[iJ1] = null;
                    iJ1--;
                    selectPlayer.put(ListeJoueurs[0],iJ1);
                }
                if (i == ListeJoueurs[1]){
                    i.ListeJetons[iJ2] = null;
                    iJ2--;    
                    selectPlayer.put(ListeJoueurs[1],iJ2);
                }
                
                

                //Vérification des conditions de victoire---
                if (GrilleDeJeu.etreGagnantePourJoueur(ListeJoueurs[0])
                    &&GrilleDeJeu.etreGagnantePourJoueur(ListeJoueurs[1])){
                    System.out.println("Faute de jeu pour "+JoueurCourant.nom+".");
                    
                    //On échange le joueur courant car en dehors de la classe Partie
                    //le joueur courant est récupéré pour savoir qui a gagné, et ici
                    //le joueur courant perd
                    if (JoueurCourant == ListeJoueurs[0]){
                        JoueurCourant = ListeJoueurs[1];
                    }
                    else{
                        JoueurCourant = ListeJoueurs[0];
                    }
                    playing = false;
                    break;
                }
                if (GrilleDeJeu.etreGagnantePourJoueur(JoueurCourant)){
                    playing = false;
                    break;

                }
                if (GrilleDeJeu.etreRemplie()){
                    playing = false;
                    //On récupérera JoueurCourant dans main pour savoir qui étais
                    //Le joueur qui a obtenu une grille gagnante (et par conscéquent
                    //mis fin a la boucle de jeu) Si il y a égalité, il ne faut donc
                    //pas qu'il y ait de JoueurCourant a la fin de la boucle de jeu
                    JoueurCourant = null; 
                    break;
                }
            }           
        }
    }
}
