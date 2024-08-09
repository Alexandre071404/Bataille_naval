package model.seaObjects;

public class SeaObject {
    protected String state;


    /**
     * Méthode constructeur de la class SeaObject
     * @ensures this.state == "floating"
     */
    public SeaObject(){
        this.state = "floating";
        assert this.state != "floating" : "Error: wrong state int the constructor";
    }


    /////////////////////////////////////GETTERS

    /**
     * Méthode qui renvoie l'état de l'objet en mer
     * @ensures resultat == this.state
     * @return l'état de l'objet en mer
     */
    public String getState(){
        return this.state;
    }

    /**
     * Méthode qui renvoie vrai si l'objet en mer est hited (touché) et faux sinon
     * @ensures True -> this.state == "hited"
     * @ensures False -> this.state != "hited"
     * @return True si this.state == "hited" et False si this.state != "hited"
     */
    public boolean getHited(){
        return this.state == "hited" || this.state == "sunked";
    }


    /////////////////////////////////////SETTERS

    /**
     * Méthode qui change l'état de l'objet en mer
     * @param state
     * @ensures this.state == state
     */
    public void setState(String state){
        this.state = state;
        assert this.state != state: "Error: wrong this.state at the end of the method";
    }

    /** 
     * Méthode qui modifie l'état de l'objet en mer à "hited" (touché)
     * @ensures this.state == "hited"
     */
    public void setHited(){
        this.state = "hited";
        assert this.state != "hited": "Error: wrong this.state at the end of the method";
    }


    public boolean isSunked(){
        return this.getState() == "sunked";
    }


    public boolean isBoat(){
        return false;
    }


}
