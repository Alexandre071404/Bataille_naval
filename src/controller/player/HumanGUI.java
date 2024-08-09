package controller.player;


import model.*;
import controller.*;


public class HumanGUI extends Human implements ControllerGUI{
    private GameController controller;


    /**
     * Méthode constructeur de la class HumanGUI
     * @param name
     */
    public HumanGUI(String name){
        super(name);
        this.controller = new GameController();
    }
    public HumanGUI(){
        this("Joueur Humain");
    }
    

    /// MÉTHODES GETTERS ///


    /**
     * Méthode qui renvoie le controller de la class
     * @return le controller de la class
     */
    @Override
    public GameController getController(){
        return this.controller;
    }

    /**
     * Méthode qui permet d'obtenir le texte entré dans le terminal
     * @return les coordonnées du clique de la souris
     */
    @Override
    public String getScannerNext(){
        
        int coX=-1,coY=-1; //Coordonnées non valides

        while( coX < 0  || coX > 9 || coY < 0 || coX > 9){ //Attente de coordonnées valides
            coX = controller.getX(); coY = controller.getY();
            try {
                Thread.sleep(10); // Suspendre le thread pendant 1 seconde
                } catch (InterruptedException e) {
                e.printStackTrace();
                }

            

        }

        controller.setNull(); //Remise des valeurs à -1
        return BattleShips.moveToCo(coX, coY);
    }


    /// MÉTHODES AUTRES ///


    /**
     * Méthode qui renvoie les coordonnées de l'attaque du joueur
     * @param nav
     * @requires nav != null
     * @return une chaine de caractère représentant l'attaque d'un joueur
     */
    @Override
    public String attackCase(BattleShips nav){
        String coup = "";
        do {
            coup = this.getScannerNext();
        } while(!nav.validMoves().contains(coup));
        return coup;
    }

    


}
