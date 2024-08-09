package view; 


import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.*;

import model.*;
import model.seaObjects.*;




public class Grille extends JPanel implements ChangeListener {
	private Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
	private int width=(int)size.getWidth()/8*3;

	private static final int lignes=10;
	private static final int col=10;
	private int taille=width/10-10;

	private int fontSize = 15;
	private Font font = new Font("", Font.ITALIC, fontSize);

	private BattleShips myGrid;
	private BattleShips ennemyGrid;
	
	/**
	 * Méthode constructeur de la class Grille
	 * @param myGrid
	 * @param ennemyGrid
	 * @ensures this.myGrid == myGrid
	 * @ensures this.ennemyGrid == ennemyGrid
	 */
	public Grille(BattleShips myGrid, BattleShips ennemyGrid){
		this.setBackground( Color.WHITE );
		setPreferredSize(new Dimension((lignes+1)*taille+2,(col+1)*taille+2));

		this.myGrid = myGrid;
		this.myGrid.addChangeListener(this);

		this.ennemyGrid = ennemyGrid;
		this.ennemyGrid.addChangeListener(this);

	}


	/**
	 * Méthode qui dessine un bateau touché dans la grille
	 * @param g 
	 * @param co
	 */
	public void drawHit(Graphics g, String co){
		drawHit(g, co.charAt(0) - 'A' + 1, Integer.parseInt(co.substring(1)));
	}

	/**
	 * Méthode qui dessine un bateau touché dans la grille
	 * @param g
	 * @param co
	 */
	public void drawHit(Graphics g, int x, int y){
		drawColoredCircle(g, x, y, Color.RED);
	}

	/**
	 * Méthode qui dessine un coup raté dans la grille
	 * @param g
	 * @param co
	 */
	public void drawMiss(Graphics g, String co){
		drawMiss(g, co.charAt(0) - 'A' + 1, Integer.parseInt(co.substring(1)));
	}

	/**
	 * Méthode qui dessine un coup raté dans la grille
	 * @param g
	 * @param co
	 */
	public void drawMiss(Graphics g, int x, int y){
		drawColoredCircle(g, x, y, Color.GREEN);
	}


	/**
	 * Méthode qui dessine un cercle de la couleur choisie
	 * @param g
	 * @param x
	 * @param y
	 * @param color
	 */
	public void drawColoredCircle(Graphics g, int x, int y, Color color){
		g.setColor(color);
		g.fillOval((x*taille) + taille /4, (y*taille) + taille /4, taille/2, taille/2);
		g.setColor(Color.BLACK);
	}

	/**
	 * Méthode qui dessine un bateau dans les coordonnées choisies
	 * @param g
	 * @param co1
	 * @param co2
	 */
	public void drawBoat(Graphics g, String co1, String co2){
		drawBoat(g, 
		co1.charAt(0) - 'A' + 1, Integer.parseInt(co1.substring(1)),
		co2.charAt(0) - 'A' + 1, Integer.parseInt(co2.substring(1)));
	}


	/**
	 * Méthode qui dessine un bateau dans les coordonnées choisies
	 * @param g
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawBoat(Graphics g, int x1, int y1, int x2, int y2){
		
		//Dessin du bateau:
		//Cas ou le debut du bateau est a gauche de la fin
		if(x1 < x2){
			//Dessin des 2 arcs
			g.drawArc(x1*taille, y1*taille, taille, taille, 90, 180);
			g.drawArc(x2*taille, y2*taille, taille, taille, 270, 180);
			//Dessin des 2 traits
			g.drawLine(x1*taille+taille/2, y1*taille, x2*taille+taille/2, y2*taille);
			g.drawLine(x1*taille+taille/2, (y1+1)*taille, x2*taille+taille/2, (y2+1)*taille);
		}
		//Cas ou le debut du bateau est a droite de la fin
		else if(x1 > x2){
			//Dessin des 2 arcs
			g.drawArc(x1*taille, y1*taille, taille, taille, 270, 180);
			g.drawArc(x2*taille, y2*taille, taille, taille, 90, 180);
			//Dessin des 2 traits
			g.drawLine(x1*taille+taille/2, y1*taille, x2*taille+taille/2, y2*taille);
			g.drawLine(x1*taille+taille/2, (y1+1)*taille, x2*taille+taille/2, (y2+1)*taille);
		}
		//Cas ou le debut du bateau est au dessus de la fin
		else if(y1 < y2){
			//Desin des 2 arcs
			g.drawArc(x1*taille, y1*taille, taille, taille, 0, 180);
			g.drawArc(x2*taille, y2*taille, taille, taille, 180, 180);
			//Dessin des 2 traits
			g.drawLine(x1*taille, y1*taille+taille/2, x2*taille, y2*taille+taille/2);
			g.drawLine((x1+1)*taille, y1*taille+taille/2, (x2+1)*taille, y2*taille+taille/2);
		}
		//Cas ou le debut du bateau est en dessous de la fin
		else if(y1 > y2){
			//Dessin des 2 arcs
			g.drawArc(x1*taille, y1*taille, taille, taille, 180, 180);
			g.drawArc(x2*taille, y2*taille, taille, taille, 0, 180);
			//Dessin des 2 traits
			g.drawLine(x1*taille, y1*taille+taille/2, x2*taille, y2*taille+taille/2);
			g.drawLine((x1+1)*taille, y1*taille+taille/2, (x2+1)*taille, y2*taille+taille/2);
		}

	}



	/**
	 * Méthode qui dessine dans le panel
	 * @param g
	 */
	@Override
	protected void paintComponent(Graphics g){
	super.paintComponent(g);

	//Génération des graphic en 2D
	Graphics2D g2d = (Graphics2D) g;
	g2d.setFont(this.font); //Mise à jour de la police d'écriture



	//Dessin des bateaux !!!

	if(!this.ennemyGrid.getPlayer().getClass().toString().equals("class controller.player.Bot")){
		for(ArrayList<String> coBoat: this.ennemyGrid.getAllCoBoat()){
			drawBoat(g, coBoat.get(0), coBoat.get(1));
		}
	}
	//Parcours de la grille
	for(int i=0;i<=lignes;i++){
		for(int j=0;j<=col;j++){

			


			//Dessin des coups
			if(j < 10 && i < 10){
				if(ennemyGrid.getGrid()[i][j].getHited()){
					if(ennemyGrid.getGrid()[i][j].isBoat()){

						if(ennemyGrid.getGrid()[i][j].isSunked() && this.ennemyGrid.getPlayer().getClass().toString().equals("class controller.player.Bot")){
							Boat boatHited = (Boat) ennemyGrid.getGrid()[i][j];
							drawBoat(g, boatHited.getCoStart().get(0)+1, boatHited.getCoStart().get(1)+1, boatHited.getCoEnd().get(0)+1, boatHited.getCoEnd().get(1)+1);
							
						}


						this.drawHit(g, i+1, j+1);
					} else {
						this.drawMiss(g, i+1, j+1);
					}
				} 
			}
			


			int x=j*taille;
			int y=i*taille;
			if(i == 0 || j == 0){
				g.setColor(Color.WHITE);
				} 

			g.drawRect(x,y,taille,taille);

			//Desin en noir
			g.setColor(Color.BLACK);
			//Première ligne: écrire les lettres des coordonnées
			if(i == 0 && j > 0){
				g2d.drawString(String.valueOf((char)(j+64)), taille*j + taille/3, taille/2+6); 
				}
			//Première colonne: écrire les entiers des coordonnées
			if(j == 0 && i > 0){
				g2d.drawString(""+i, x+taille/3, y+taille/2+6);
				}
			
			
			}
		
		}	


		
	}

	
	/**
	 * Méthode qui dessine dans le panel
	 * @param g
	 */
	public void stateChanged(ChangeEvent e){
		//System.out.println("Repaint");
		repaint();
	}







}
