package view;

import java.awt.*;
import javax.swing.*;

import model.BattleShips;
import controller.player.*;
import controller.*;



public class GameGUI extends JFrame implements GameView{

	private Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
	private int width=(int)size.getWidth()/2;
	private int height=(int)size.getHeight()/2;

	private BattleShips gameHuman;
	private BattleShips gameBot;

	private Grille gridHuman;
	private Grille gridBot;
	
	private GameController controlHuman;
	private GameController controlBot;


	/**
	 * Méthode constructeur de la class GameGUI
	 * @param gameHuman
	 * @param gameBot
	 */
	public GameGUI(BattleShips gameHuman, BattleShips gameBot){

		this.setTitle("Jeu de la bataille navale");
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2));
		
		this.gameHuman = gameHuman;
		this.gameBot = gameBot;

		this.gridBot = new Grille(gameBot, gameHuman);
		this.gridHuman = new Grille(gameHuman, gameBot);



		if(!this.gameHuman.getPlayer().getClass().toString().equals("class controller.player.Bot")){
			HumanGUI human = new HumanGUI(this.gameHuman.getPlayer().toString());
			this.gameHuman.setPlayer(human);
			this.controlHuman = human.getController();
			this.addMouseListener(controlHuman);
		}
		
		if(!this.gameBot.getPlayer().getClass().toString().equals("class controller.player.Bot")){
			HumanGUI bot = new HumanGUI(this.gameBot.getPlayer().toString());
			this.gameBot.setPlayer(bot);
			this.controlBot = bot.getController();
			this.addMouseListener(controlBot);
		}
		

		this.add(this.gridHuman);
		this.add(this.gridBot);



		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		

		}

	
	/**
	 * Méthode qui ne sert à rien
	 */
	public void print(String text){	}
	

	/**
 	* Méthode qui met à jour les grilles
	*/
	public void situationToString(BattleShips battle){
		this.gridBot.repaint();
		this.gridHuman.repaint();

	}



	/**
	 * Méthode qui met à jour les grilles
	 */
	public void situationToStringGrille(){
		this.gridBot.repaint();
		this.gridHuman.repaint();
	}

	/**
	 * Méthode qui met à jour les grilles
	 */
	public void situationToStringBoat(BattleShips battle){
		this.gridBot.repaint();
		this.gridHuman.repaint();
	}

}
