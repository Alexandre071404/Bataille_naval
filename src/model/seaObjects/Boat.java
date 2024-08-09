package model.seaObjects;

import java.util.*;

public class Boat extends SeaObject {
    private int size;
    private ArrayList<Integer> coStart;
    private ArrayList<Integer> coEnd;

    /**
     * Méthode constructeur de la class Boat
     * @param size
     * @requires size > 0 && size <= 10
     * @ensures this.size == size
     */
    public Boat(int size){
        super();
        assert this.size < 1 : "Error: size of the boat too small";
        assert this.size > 10 : "Error: size of the boat too big for the grid";
        this.size = size;
        assert this.size != size : "Error: this.size != size";
    }

    /**
     * Méthode constructeur de la class Boat
     * @param size
     * @param coStart
     * @param coEnd
     * @requires size > 0 && size < 10
     * @requires coStart.size() == 2 && coStart.get(0) < 10 && coStart.get(0) >= 0 && coStart.get(1) < 10 && coStart.get(1) >= 0 
     * @requires coEnd.size() == 2 && coEnd.get(0) < 10 && coEnd.get(0) >= 0 && coEnd.get(1) < 10 && coEnd.get(1) >= 0 
     * @ensures this.size == size
     * @ensures this.coStart == coStart
     * @ensures this.coEnd == coEnd
     */
    public Boat(int size, ArrayList<Integer> coStart, ArrayList<Integer> coEnd){
        this(size);
        assert this.size != size : "Error: this.size != size";

        assert coStart.size() != 2 : "Error: wrong size for coStart";
        assert coStart.get(0) >= 10 && coStart.get(0) < 0 && coStart.get(1) >= 10 && coStart.get(1) < 0 : "Error: Wrong number in coStart";
        assert coEnd.size() != 2 : "Error: wrong size for coEnd";
        assert coEnd.get(0) >= 10 && coEnd.get(0) < 0 && coEnd.get(1) >= 10 && coEnd.get(1) < 0  : "Error: Wrong number in coEnd";
        this.coStart = coStart;
        assert !this.coStart.equals(coStart): "Error: this.coStart != coStart";
        this.coEnd = coEnd;
        assert !this.coEnd.equals(coEnd): "Error: this.coEnd != coEnd";
    }

    /**
     * Méthode constructeur de la class Boat
     * @param size
     * @param coStartX
     * @param coStartY
     * @param coEndX
     * @param coEndY
     * @requires size > 0 && size < 10
     * @requires coStartX < 10 && coStartX >= 0 && coStartY < 10 && coStartY >= 0 
     * @requires coEndX < 10 && coEndX >= 0 && coEndY < 10 && coEndY >= 0 
     * @ensures this.size == size
     * @ensures this.coStart.size() == 2 && this.coStart.get(0) == coStartX && this.coStart.get(1) == coStartY
     * @ensures this.coEnd.size() == 2 && this.coEnd.get(0) == coEndX && this.coEnd.get(1) == coEndY
     */
    public Boat(int size, int coStartX, int coStartY, int coEndX, int coEndY){
        this(size);
        assert this.size != size : "Error: this.size != size";
        
        assert !(coStartX < 10 && coStartX >= 0 && coStartY < 10 && coStartY >= 0 ) : "Error: Wrong number in coStartX or coStratY";
        assert !(coEndX < 10 && coEndX >= 0 && coEndY < 10 && coEndY >= 0 ) : "Error: Wrong number in coEndX or coEndY";
        this.coStart = new ArrayList<>();
        this.coStart.add(coStartX);
        this.coStart.add(coStartY);
        assert this.coStart.size() != 2 : "Error: wrong size for coStart";
        assert !(this.coStart.get(0) == coStartX && this.coStart.get(1) == coStartY) : "Error: this.coStart.get(0) != coStartX || this.coEnd.get(1) != coStartY";

        this.coEnd = new ArrayList<>();
        this.coEnd.add(coEndX);
        this.coEnd.add(coEndY);
        assert this.coEnd.size() != 2 : "Error: wrong size for coEnd";
        assert !(this.coEnd.get(0) == coEndX && this.coEnd.get(1) == coEndY) : "Error: this.coEnd.get(0) != coEndX or this.coEnd.get(1) != coEndY";

    }



    /////////////////////////////////////GETTERS


    /**
     * Méthode qui renvoie la taille du bateau
     * @ensures this.size
     * @return taille du bateau
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Méthode qui renvoie le tableau de coordonnées de départ du bateau
     * @ensures resulat == this.coStart
     * @return tableau de coordonnées de départ du bateau
     */
    public ArrayList<Integer> getCoStart(){
        return this.coStart;
    }
    
    /**
     * Méthode qui renvoie le tableau de coordonnées de fin du bateau
     * @ensures resultat == this.coEnd
     * @return le tableau de coordonnées de fin du bateau
     */
    public ArrayList<Integer> getCoEnd(){
        return this.coEnd;
    }




    /////////////////////////////////////SETTERS

    /**
     * Méthode qui modifie l'état du bateau à hited (touché)
     * @ensures super.state == "hited"
     */
    @Override
    public void setHited(){
        super.setState("hited");
    }



/**
     * Méthode qui modifie l'état du bateau à sunked (coulé)
     * @ensures super.state == "sunked"
     */
    public void setSunked(){
        super.setState("sunked");
    }



    /////////////////////////////////////

    /**
     * Méthode qui vérifie si deux objets sont égaux
     */
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Boat copy = (Boat) o;
        return this.getState().equals(copy.getState()) && this.getSize() ==copy.getSize() && this.getCoEnd().equals(copy.getCoEnd()) && this.getCoStart().equals(copy.getCoStart());
    }
    
    /**
     * Méthode qui vérifie si l'objet est un bateau
     * @ensures resultat == true => object of class Boat
     * @ensures resultat == false => object not of class Boat
     * @return boolean true si l'objet est un bateau et false sinon
     */
    @Override
    public boolean isBoat(){
        return true;
    }


}
