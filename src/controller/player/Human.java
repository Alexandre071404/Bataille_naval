package controller.player;

import java.util.*;
import model.*;


public class Human implements Player{
    private String name;
    private Scanner scanner;



    /**
     * Méthode constructeur de la class Human
     * @ensures name == this.name
     * @ensures this.scanner != null
     */
    public Human(String name){
        this.name = name;
        assert this.name != name: "Error: wrong this.name at the end of the constructor";
        this.scanner=new Scanner(System.in);
        assert this.scanner == null: "Error: wrong this.scanner at the end of the constructor";
    }

    public Human(){
        this("Joueur Humain");
    }
    

    /// MÉTHODES GETTERS ///


    /**
     * Méthode qui permet d'obtenir le texte entré dans le terminal
     * @return l'entrée du terminal
     */
    public String getScannerNext(){
        String str = scanner.next();
        return str;
    }



    /**
     * Méthode qui renvoie le nom du joueur
     * @return this.name
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode qui renvoie le scanner du joueur
     * @return this.scanner
     */
    public Scanner getScanner() {
        return scanner;
    }


    /// MÉTHODES SETTERS ///


    /**
     * Méthode qui définie le nom du joueur
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        assert this.name != name: "Error: wrong name at the end of the method";
    }

    
    /**
     * Méthode qui définie le scanner du joueur
     * @param scanner
     * @requires scanner != null
     */
    public void setScanner(Scanner scanner) {
        assert scanner != null: "Error: wrong scanner entry";
        this.scanner = scanner;
    }



    /**
     * Méthode qui place le bateau du joueur selon les coordonnées entrées dans le scanner
     * 
     * @return les coordonnées de début et de fin choisies par le joueur
     */
    public ArrayList<String> setBoat(BattleShips nav){
        while(true){
            
            //Récuperation des coordonnées données par l'utilisateur
            System.out.println("Veuillez mettre les coordonnées du début de votre bateau.");
            String debut=getScannerNext();
            System.out.println("Veuillez mettre les coordonnées de la fin de votre bateau.");
            String fin=getScannerNext();

            int co1x = BattleShips.moveToCol(debut);
            int co1y = BattleShips.moveToRow(debut);
            int co2x = BattleShips.moveToCol(fin);
            int co2y = BattleShips.moveToRow(fin);

            int minx=0,maxx=0,miny=0,maxy=0;

            //TRI DES VALEURS POUR LE FOR
            if(co1x == co2x){
                minx = co2x; maxx = co1x;
            }else {
                if(co1x > co2x){
                    minx = co2x; maxx = co1x;
                } else {
                    minx = co1x; maxx = co2x;
                }
            }
            
            assert minx > maxx: "Error: wrong sort of minX and maxX";
    
            if(co1y == co2y){
                miny = co1y; maxy = co2y;
            } else {
                if(co1y > co2y){
                    miny = co2y; maxy = co1y;
                } else {
                    miny = co1y; maxy = co2y;
                }
                
            }

            assert miny > maxy: "Error: wrong sort of minY and maxY";

            if(setBoatVerif(co1x, co1y, co2x, co2y, nav)){
                //VERIFICATION DE LA POSSIBILITE DES BATEAUX
                ArrayList<ArrayList<String>> validPlace = nav.validBoat();
                for(int i=0; i < validPlace.size(); i++){

                    if(validPlace.get(i).contains(BattleShips.moveToCo(minx, miny)) && validPlace.get(i).contains(BattleShips.moveToCo(maxx, maxy))){
                        //Placement du bateau possible
                        return validPlace.get(i);
                    }
                }
            }
            

        }
    }

    
    /**
     * Méthode qui renvoie les coordonnées de l'attaque du joueur
     * @param nav
     * @requires nav != null
     * @return une chaine de caractère représentant l'attaque d'un joueur
     */
    public String attackCase(BattleShips nav){
        String coup=getScannerNext();
        
        while(nav.validMoves().contains(coup)==false){
            System.out.println("Votre coup n'est pas valide");
            System.out.println("Retentez un coup !");
            coup=getScannerNext();

        }
        return coup;
    }


    /// MÉTHODES AUTRES ///


    /**
     * Méthode qui renvoie vrai si la position du bateau est possible et faux sinon
     * @param co1x
     * @param co1y
     * @param co2x
     * @param co2y
     * @param nav
     * @requires nav != null
     * @return vrai si les coordonnées sont possibles et faux sinon
     */
    public boolean setBoatVerif(int co1x, int co1y, int co2x, int co2y, BattleShips nav){

        boolean valid = false; //Placement invalide de base
        if(!nav.isDiagonal(co1x, co1y, co2x, co2y)){ //Un bateau ne peut pas être placé en diagonal

            //Calcul la taill du bateau
            int size = Math.abs(co2y - co1y) +1;
            if(size == 1){ //bateau de taille 1 impossible
                size = Math.abs(co2x - co1x) +1; //Donc le bateau est sur la ligne et non sur la colonne
            }

            //Si la taille du bateau est dans la liste des bateaux restants a placer
            if(size <= Collections.max(nav.getListBoatRemaining()) && size > 0 && nav.getListBoatRemaining().contains(size)){
                valid = true;
            }


        }

        if(valid == false){ 
            System.out.println("Valeurs incorrectes !");
        }
        return valid;
    }


    /**
     * Méthode qui affiche le joueur
     */
    @Override
    public String toString(){
        return name;
    }


    /**
     * Méthode qui renvoie vrai si l'objet est égal à la class et faux sinon
     * @return boolean
     */
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Human copy = (Human) o;
        return this.toString().equals(copy.toString());
    }

    

    





}
