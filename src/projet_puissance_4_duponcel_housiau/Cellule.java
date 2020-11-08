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
public class Cellule {
    Jeton JetonCourant;
    boolean trouNoir;
    boolean desintegrateur;
    
    //Définition du constructeur
    public Cellule(Jeton jetoncourant, boolean trounoir, boolean desintegratueur){
        JetonCourant = jetoncourant;
        trouNoir = trounoir;
        desintegrateur = desintegratueur;
    }
    
    //Classe permettant d'ajouter un jeton passé en paramètre à la cellule
    public boolean affecterJeton(Jeton UnJeton){
        
        //Cas où la case est vide
        if (JetonCourant == null){
            JetonCourant = UnJeton;
            return true;
        }
        //Cas où la case n'est pas vide
        else{
            return false;
        }    
    }
    
    public String lireCouleurDuJeton(){
        return JetonCourant.Couleur;
    }
    
    //Ici, quand on aura créé les trous noir et les désintégrateurs, il faudra
    //que le toString renvoie : le trou noir s'il y a un trou noir et un désintégrateur
    //                          sinon le contenu de la case

    public boolean placerTrouNoir(){
        if (trouNoir == false){
            trouNoir = true;
            return true;
        }
        else {
            return false;
        }
    }
    public boolean presenceTrouNoir(){
        return trouNoir;
        
    }
    public boolean activerTrouNoir(){
        if (trouNoir == true){
            trouNoir = false;
            JetonCourant = null;
            return true;
        }
        else{
            return false;
        }
    }
    public Jeton recupererJeton(){
        return JetonCourant;
    }
    public boolean placerDesintegrateur(){
        if (desintegrateur == false){
            desintegrateur = true;
            return true;
        }
        else{
        return false;   
        }
    }
    
    public boolean presenceDesintegrateur(){
        return desintegrateur;
        
    }
    
    public boolean recupererDesintegrateur(){
        if (desintegrateur = true){
            desintegrateur = false;
            return true;
        }
        else{
            return false;
        }
    }
}
