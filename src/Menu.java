import java.util.Scanner;

import controller.player.*;
import view.GameView;
import view.GameGUI;
import view.ViewTerminal;
import model.*;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix; 
        Player p1 = new Human();
	Player p2 = new Bot();

	//Création des grilles
	BattleShips grille1 = new BattleShips(p1);
	BattleShips grille2 = new BattleShips(p2);

        do{
            System.out.println("Menu:");
            System.out.println("1. Lancement de l'interface du jeu");
            System.out.println("2. Lancement du jeu sur le terminal");
            System.out.println("3. Quitter");

            choix = scanner.nextInt();
            //Création des deux joueurs
	   

            switch (choix){
                case 1:
                    System.out.println("Lancement du jeu...");
                    GameView viewMVC = new GameGUI(grille1, grille2);
                    Launcher launcherMVC = new Launcher(grille1, grille2, viewMVC);
                    launcherMVC.play();
                    choix=3;
                    break;
                case 2:
                    System.out.println("Lancement de la partie sur le terminal...");
                    GameView view = new ViewTerminal(grille1, grille2);
                    Launcher launcher = new Launcher(grille1, grille2, view);
                    launcher.play();
                    choix=3;
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Veuillez entrer un choix valide.");
            }
        }
        while (choix != 3);

        scanner.close();
    }
}
