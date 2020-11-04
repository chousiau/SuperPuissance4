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
public class Jeton {
    String Couleur;
    
    public Jeton(String couleur){
        Couleur = couleur;
    }    
    public String lireCouleur(){
        return Couleur;
    }
    
}
  