package controller.player;

import java.util.*;
import model.*;

public class Bot implements Player{
    private Random rand;


    /** 
     *  Méthode constructeur de la class Bot
     *  @ensures this.rand != null;
     */
    public Bot(){
        this.rand = new Random();
    }



    // MÉTHODE GETTERS //

    /**
     * Méthode qui renvoie l'objet de type Random appartenant à la class Bot
     * @ensures resultat == this.rand
     * @return l'objet Random de la class
     */
    public Random getRand(){
        return this.rand;
    }



    // MÉTHODE SETTERS //

    /**
     * Méthode qui renvoie les coordonnées choisies pour placer un bateau de façon valide
     * @param nav
     * @ensures coordonnees.size() == 2
     * @ensures nav.validBoat().contains(coordonnees)
     * @return une liste de 2 String représentant des coordonnées valides pour placer un bateau
     */
    public ArrayList<String> setBoat(BattleShips nav){
        ArrayList<ArrayList<String>> validPlace = nav.validBoat();
    
        int rndm = this.rand.nextInt(validPlace.size());
        ArrayList<String> coordonnees = validPlace.get(rndm);
      

        return coordonnees;
        }

        
    // AUTRES MÉTHODES //

    /**
     * Méthode qui renvoie les coordonnées valides pour attaquer le plateau adverse
     * @param nav
     * @ensures nav.validMoves().contains(resultat)
     * @return un String représentant des coordonnées valides pour attaquer
     */
    public String attackCase(BattleShips nav){
        ArrayList<String> validMoves = nav.validMoves();
        int selec = this.rand.nextInt(validMoves.size());

        return validMoves.get(selec);
    }


    /**
     * Méthode qui vérifie si notre objet et l'Objet o pris en argument sont égaux.
     * @param o
     * @ensures resultat == true => this == o || this.getRand().equals(copy.getRand())
     * @ensures resultat == false => o == null || getClass() != o.getClass()
     * @return True si l'objet o est egale à notre objet et false sinon
     */
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Bot copy = (Bot) o;
        return this.getRand().equals(copy.getRand());
    }


}

