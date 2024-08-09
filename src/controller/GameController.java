package controller;

import java.awt.event.*;
import java.awt.*;


public class GameController implements MouseListener {
    private int x = -1;
    private int y = -1; 

    private int width=(int) ((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth())/8*3 /11.5);
    private int height=(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/8*3;




      /**
       * Méthode qui capte le clique de la souris et enregistre la case sur laquelle le clique a été effectué
       */
      public void mousePressed(MouseEvent e) {

      int x = (e.getX() / width -1) %11;
      int y = 9 - ((height - e.getY()) / width) %11 ;

      this.setValues(x, y);
     }

     public void mouseClicked(MouseEvent e) {}
   
     public void mouseReleased(MouseEvent e) {}

     public void mouseEntered(MouseEvent e) {}
     
     public void mouseExited(MouseEvent e) {}




     /**
      * Méthode qui renvoie la valeur de this.x
      * @return int x
      */
     public int getX(){
        return this.x;
     }

     /**
      * Méthode qui renvoie la valeur de this.y
      * @return int y
      */
     public int getY(){
        return this.y;
     }

     /**
      * Méthode qui change la valeur de this.x et this.y par x et y
      * @param x
      * @param y
      * @ensures this.x == x
      * @ensures this.y == y
      */
     public void setValues(int x, int y){
      this.x = x;
      this.y = y;

      assert this.x != x: "Error: wrong x at the end of the method";
      assert this.y != y: "Error: wrong y at the end of the method";
     }

     /**
      * Méthode qui remet les valeurs de x et y à -1 pour qu'elles ne soient plus valides
      * @ensures this.x == -1
      * @ensures this/y == -1
      */
     public void setNull(){
        this.x = -1;
        this.y = -1;
     }

}
