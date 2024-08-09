package model;

import java.util.*;

import controller.player.*;
import view.*;


public class Launcher {
    protected BattleShips currentBattle;
    protected ArrayList<BattleShips> listBattle;
    protected GameView view;
    protected Player p1;
    protected Player p2;


    /**
     * méthode constructeur de la class Launcher
     * @param battleJ1
     * @param battleJ2
     * @param view
     * @requires battle1 != null
     * @requires battle2 != null
     * @requires view != null
     * @ensures this.listBattle.get(0).equals(battleJ1) && this.listBattle.get(1).equals(battleJ2)
     * @ensures this.currentBattle.equals(this.listBattle.get(0))
     * @ensures this.view == view
     */
    public Launcher(BattleShips battleJ1, BattleShips battleJ2, GameView view){
        //Ajout des deux grilles de jeu dans la liste
        this.listBattle = new ArrayList<>();
        this.listBattle.add(battleJ1);
        this.listBattle.add(battleJ2);

        this.currentBattle = this.listBattle.get(0); //La grille qui commence à jouer est la première

        this.view = view;
    }

    /**
     * Méthode constructeur de la class Launcher
     * @param battle1
     * @param battle2
     * @requires battle1 != null
     * @requires battle2 != null
     */
    public Launcher(BattleShips battle1, BattleShips battle2){
        this(battle1, battle2, new ViewTerminal(battle1, battle2));
    }

    /**
     * Méthode constructeur de la class Launcher
     * 
     */
    public Launcher(){
        this(new BattleShips(new Human("Joueur1")), new BattleShips(new Bot()));
    }

    

    

    /**
     * Méthode qui renvoie la liste des batailles navales
     * @ensures resultat == this.listBattle
     * @return la liste des deux batailles navales en jeu
     */
    public ArrayList<BattleShips> getListBattleShips(){
        return this.listBattle;
    }

    /**
     * Méthode qui renvoie le premier joueur
     * @ensures resultat == this.p1
     * @return le premier joueur de la partie
     */
    public Player getPlayer1(){
        return this.p1;
    }

    /**
     * Méthode qui renvoie le second joueur
     * @ensures resultat == this.p1
     * @return le second joueur de la partie
     */
    public Player getPlayer2(){
        return this.p2;
    }



    /**
     * Méthode qui contient la boucle principale du jeu
     */
    public void play(){

        
        //Placement des bateaux
        ArrayList<Integer> listBoatRemaining = this.currentBattle.getListBoatRemaining(); //La liste des tailles des bateaux restants à placer
        
        while(listBoatRemaining.size() > 0){ //Tant qu'il reste des bateaux à placer au joueur actuel
            

            

            //Obtentien des coordonnées valides de debut et de fin du bateau
            ArrayList<String> coordonnees = this.currentBattle.getPlayer().setBoat(currentBattle);
            //Obtention de la taille du bateau
            this.currentBattle.setBoat(coordonnees.get(0), coordonnees.get(1));
            listBoatRemaining = this.currentBattle.getListBoatRemaining(); //Liste des bateaux a placer
            

            if(listBoatRemaining.size() == 0){ //Plus de bateaux à poser

                this.currentBattle = this.next(); //Placement des bateaux pour le joueur suivant
                listBoatRemaining = this.currentBattle.getListBoatRemaining(); //La liste des tailles des bateaux restants à placer (tous pour le deuxieme joueur)

            }
        }


        //Boucle principale du jeu
        while(!this.next().isOver()){
            //Choix du coup
            String coup = this.currentBattle.getPlayer().attackCase(this.next());

            this.next().execute(coup);
            
            this.currentBattle = this.next();
        }




    }


    /**
     * Méthode qui permet d'obtenir la btaille navale qui ne joue pas actuellement
     * @ensures resultat != this.currentBattle
     * @ensures this.listeBattle.contains(resultat)
     * @return la bataille navale différente de celle qui joue
     */
    public BattleShips next(){
        if(this.currentBattle.equals(this.listBattle.get(0))){
            return this.listBattle.get(1);
        } else {
            return this.listBattle.get(0);
        }
    }



}
