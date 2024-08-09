package view;

import model.*;
import model.seaObjects.*;
import javax.swing.event.*;

import java.util.*;


public class ViewTerminal implements GameView, ChangeListener {
    private BattleShips grille1, grille2;

    /**
     * Méthode constructeur de la class ViewTerminal
     * @param grille1
     * @param grille2
     */
    public ViewTerminal(BattleShips grille1, BattleShips grille2){
        this.grille1 = grille1;
        this.grille2 = grille2;

		this.grille1.addChangeListener(this);
        this.grille2.addChangeListener(this);
    }

    /**
     * Cette méthode permet l'affichage de la partie en cours
     * @param battle
     */
    public void situationToString(BattleShips battle){
        situationToStringGrille();
        String res = "";
        res += "C'est au tour de " + battle.getPlayer() + " de jouer.\nChoisissez votre coup:";
        System.out.println(res);
    }



    /**
     * Méthode qui affiche les grilles avec les éléments si il y en a dedans
     */
    public void situationToStringGrille(){
        ArrayList<SeaObject[][]> listGrid = new ArrayList<>();
        listGrid.add(grille2.getGrid());
        listGrid.add(grille1.getGrid());

        String res = "  A B C D E F G H I J  |   A B C D E F G H I J\n";
        for (int i = 0;i < listGrid.get(0).length; i++) {
            //Affichage ligne par ligne

            for(SeaObject grid[][]: listGrid){
                //Affichage grille 1 puis grille 2

                if(i < 9){
                    res += i+1 + " ";
                }else { //La valeur 10 décale tout l'affichage !!!
                    res += i+1 + "";
                }

                //Affichage des bateaux
                for (int j = 0;j < grid[i].length;j++) {
                    if(grid[j][i].getHited() || grid[j][i].isSunked()){
                        if(grid[j][i].isBoat()){
                            res += "X ";
                        } else {
                            res += "! ";
                        }
                    } else {
                        res += "  ";
                    }
                }

                
                res += " | ";
            }
            res += "\n";
        }
        System.out.println(res);
    }

    /**
     * Cette méthode permet d'afficher les bateaux
     */
    public void situationToStringBoat(BattleShips battle){
        SeaObject grid[][] = battle.getGrid();
        //Afichage de la grille ligne par ligne
        String res = "  A B C D E F G H I J \n";
        for (int i = 0;i < grid.length; i++) {
            if(i < 9){
                res += i+1 + " ";
            }else {
                res += i+1 + "";
            }
            
             for (int j = 0;j < grid[i].length;j++) {
                if(grid[j][i].isBoat()){
                    res += "0 ";
                } else {
                    res += "  ";
                }
            }
            res += "\n";
        }
        System.out.println(res);
    }


    /**
     * Cette méthode affiche un texte
     * @param text
     * 
     */
    public void print(String text){
        System.out.println(text);
    }

    /**
     * Cette méthode s'active lors de la reception d'un ChangeEvent et affiche la partie en cours
     * @param e
     */
    public void stateChanged(ChangeEvent e){



        ArrayList<Integer> listBoatRemaining1 = this.grille1.getListBoatRemaining();
        ArrayList<Integer> listBoatRemaining2 = this.grille2.getListBoatRemaining();
        String text = "";
        if(listBoatRemaining1.size() >= 0 && listBoatRemaining2.size() >= 0 && (listBoatRemaining1.size() != 0 || listBoatRemaining2.size() != 0)){
            
            if(listBoatRemaining1.size() == 0 && listBoatRemaining2.size() == 5){
                print("Fin de la pose des bateaux pour le joueur " + this.grille1.getPlayer() + " !" + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            } else if(listBoatRemaining2.size() == 1 && listBoatRemaining1.size() == 0){
                print("Fin de la pose des bateaux pour le joueur " + this.grille2.getPlayer() + " !" + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            }


            if(listBoatRemaining1.size() > 0 && !this.grille1.getPlayer().getClass().toString().equals("class controller.player.Bot")){

                situationToStringBoat(grille1);
                //Affichage de la taille du reste des bateaux à placer
                text = "\nIl reste les bateaux des tailles suivantes à placer:";
                for(int i=0 ;i< listBoatRemaining1.size();i++){
                    text += "\n--"+listBoatRemaining1.get(i);
                }
                print(text);

                
                
            } else if(listBoatRemaining1.size() == 0 && listBoatRemaining2.size() > 0 && !this.grille2.getPlayer().getClass().toString().equals("class controller.player.Bot")){

                situationToStringBoat(grille2);
                //Affichage de la taille du reste des bateaux à placer
                text = "\nIl reste les bateaux des tailles suivantes à placer:";
                for(int i=0 ;i< listBoatRemaining2.size();i++){
                    text += "\n--"+listBoatRemaining2.get(i);
                }
                print(text);

                
            } 


            

        } else {

            situationToStringGrille();



            if(this.grille1.isOver()){
                this.print(this.grille2.getPlayer() + " est vainqueur !");
            } else if(this.grille2.isOver()){
                this.print(this.grille1.getPlayer() + " est vainqueur !");
            }
        }



        


	}

}
