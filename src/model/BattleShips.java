package model;

import java.util.*;
import java.lang.Math;

import controller.Ecoutable;
import controller.player.*;
import model.seaObjects.*;/*Si ne compile pas alors enlever les import model.*/




public class BattleShips extends Ecoutable{

private SeaObject grid[][]=new SeaObject [10][10];
    private Player player;
    private ArrayList<Integer> listBoatRemaining;


	 /**
     * Méthode constructeur de la class BattleShips
     * @param Player 
     * @ensures grid[i][j] = new SeaObject
     */
	public BattleShips(Player Player){
        this.listBoatRemaining = new ArrayList<Integer>();
        this.listBoatRemaining.add(5);
        this.listBoatRemaining.add(4);
        this.listBoatRemaining.add(3);
        this.listBoatRemaining.add(3);
        this.listBoatRemaining.add(2);
        this.player = Player;
        for(int i=0;i<10;i++){
        	for(int j=0;j<10;j++){
        		this.grid[i][j]=new SeaObject();
        		}
        	}
	}


    /// MÉTHODES GETTERS ///

	/**
     * Méthode qui renvoie la grille
     * @ensures resultat == this.grid
     * @return La grille de type SeaObject
     */
	public SeaObject[][] getGrid(){
		return this.grid;
		}

    /**
     * Méthode qui renvoie le joueur courant
     * @ensures resultat == this.player
     * @return Le joueur courant
     */
    public Player getPlayer(){
        return this.player;
        }

    /**
     * Méthode qui renvoie la liste des bateaux Restants
     * @ensures resultat == this.listBoatRemaining
     * @return La liste des bateaux restants
     */
    public ArrayList<Integer> getListBoatRemaining(){
        return this.listBoatRemaining;
    }

    /**
     * Méthode qui renvoie les coordonnées du bateau à partir d'un coup donné
     * @param coup : Represente le coup du joueur en coordonnée
     * @return La liste des bateaux restants
     */
    public SeaObject getBoat(String coup){
        return this.getBoat(moveToCol(coup), moveToRow(coup));
    }

    /**
     * Méthode auxiliaire qui les coordonnées 
     * @param x : Coordonnée x
     * @param y : Coordonnée y 
     * @ensures resultat == this.grid[x][y]
     * @return La liste des bateaux restants
     */
    public SeaObject getBoat(int x, int y){
        return grid[x][y];
    }





    //////////////          MÉTHODES DE SETTERS              ////////////

    /**
     * Méthode qui place le Bateau
     * @param co1 : Represente la coordonnée du début du bateau.
     * @param co2 : Represente la coordonnée de la fin du bateau.
     * @ensures Placement du bateau .
     * @return Place le bateau en appelant la méthode setBoat.
     */
    public int setBoat(String co1, String co2){
        return this.setBoat(moveToCol(co1), moveToRow(co1), moveToCol(co2), moveToRow(co2));
    }

    /**
     * Méthode qui place le Bateau
     * @param co1x : Represente la coordonnée x du début du bateau.
     * @param co1y : Represente la coordonnée y du début du bateau.
     * @param co2x : Represente la coordonnée x de la fin du bateau.
     * @param co2y : Represente la coordonnée y de la fin du bateau.
     * @ensures Placement du bateau .
     * @return La taille du bateau.
     */
    public int setBoat(int co1x, int co1y, int co2x, int co2y){

        int size = Math.abs(co2y - co1y) +1;
        if(size == 1){
            size = Math.abs(co2x - co1x) +1;
        }
        
        

        ArrayList<Integer> coStart = new ArrayList<>();
        ArrayList<Integer> coEnd = new ArrayList<>();
        coStart.add(co1x); coStart.add(co1y); coEnd.add(co2x); coEnd.add(co2y);


        for(int i = co1x; i <= co2x; i++){
            for(int j = co1y; j <= co2y; j++){
                this.grid[i][j] = new Boat(size, coStart, coEnd);
            }
        }

        this.listBoatRemaining.remove(Integer.valueOf(size));

        fireChange();

        return size;
    }

    /**
     * Méthode qui affecte le joueur à la variable 'player' de cette classe.
     * @param player : Represente le joueur
     * @ensures this.player == player
     */
    public void setPlayer(Player player){
        this.player = player;
    }
    






    //////////////          MÉTHODES DE VÉRIFICATION              ////////////




    /**
     * Méthode qui converti la ligne et la colonne en Int et vérifie si elle sont valides. 
     * @param coord : Represente une coordonnée.
     * @return Vrai si le coup est valide.
     * @return Faux si ce n'est pas le cas.
     */
    public boolean isValid(String coord){
        int row = Integer.parseInt(coord.substring(1));
        int col = coord.charAt(0) - 'A' + 1;
        return isValid(row,col);
        }


    /**
     * Méthode qui renvoie Vrai si le coup est possible : entre 0-9 et non touché.
     * @param row : Represente la ligne 
     * @param column : Represente la colonne 
     * @return Vrai si le coup est valide 
     * @return Faux si ce n'est pas le cas.
     */
    public boolean isValid(int row, int column){
        if((row >= 0 && row<10) && (column >= 0 && column<10)){
            return((this.grid[row][column].getHited()==false));
        }
        return false;
        }

    /**
     * Méthode qui renvoie Vrai si les coordonnées sont en diagonal et Faux sinon.
     * @param c1 : Represente une coordonnée
     * @param c2 : Represente une coordonnée 
     * @return Vrai si les coordonnées sont en diagonal 
     * @return Faux si ce n'est pas le cas.
     */
    public  boolean isDiagonal(String c1, String c2) {

        return (!c1.substring(0, 1).equals(c2.substring(0, 1)) && !c1.substring(1).equals(c2.substring(1)));

        }
    /**
     * Méthode qui renvoie Vrai si les coordonnées sont en diagonal et Faux sinon.
     * @param x1 : Represente une coordonnée x
     * @param y1 : Represente une coordonnée y
     * @param x2 : Represente une coordonnée x
     * @param y2 : Represente une coordonnée y 
     * @return Vrai si les coordonnées sont en diagonal 
     * @return Faux si ce n'est pas le cas.
     */
    public boolean isDiagonal(int x1, int y1, int x2, int y2){
        return (x1 != x2 && y1 != y2);
    }


    /**
     * Méthode qui retourne vrai si toute les cases des bateaux ont été touchées et faux sinon ( en parcourant la grille).
     * @return Vrai si toutes les cases des bateaux ont été touchées, Faux sinon.
     */
    public boolean isOver(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(this.grid[i][j].isBoat() && !this.grid[i][j].getHited()){
                    return false;
                    }
                }
            }
            return true;
        }

    /**
     * Cette méthode permet de vérifier si deux bateaux ne se croise pas au niveau des coordonnées
     * @param x1 : Ligne de coordonnée x de la tête du bateau
     * @param y1 : Colonne de coordonnée y de la tête du bateau
     * @param x2 : Ligne de coordonnée x de la queue du bateau
     * @param y2 : Colonne de coordonnée y de la queue du bateau
     * @requires int x1,y1,x2,y2 entre 0-9.
     * @return vrai si les cases (x1,y1),(x2,y2) sont vides.
     * @return faux si il y a au moins une case occupé par un bateau.
     */
    public boolean isValidBoat(int x1, int y1, int x2, int y2){

        for(int x = x1; x <= x2; x++){
            for(int y = y1; y <= y2; y++){
                if(this.grid[x][y].isBoat()){
                    return false;
                }
            }
        }
        return true;
    }



    /**
     * Cette méthode parcours chaque case de la grille et compare les élements des deux joueurs pour chaque cases. 
     * @return Vrai si les deux joueurs ont la même liste de coup valides.
     * @return Faux si ce n'est pas le cas.
     */
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BattleShips copy = (BattleShips) o;
        for (int i = 0;i < grid.length; i++) {
             for (int j = 0;j < grid[i].length;j++) {
                if(!this.grid[i][j].equals(copy.getGrid()[i][j])){
                    return false;
                }
            }
        }
        return this.getPlayer().equals(copy.getPlayer()) && this.validMoves().equals(copy.validMoves());
    }










    //////////////          MÉTHODES DE CONVERTION               ////////////




    
    /**
     * Méthode qui convertie la colonne : String, en colonne : Int
     * @param coord : Chaîne de caractère qui represente les coordonnées
     * @requires La chaîne "coord" doit représenter une coordonnée valide sur la grille de jeu.
     * @return La colonne converti en Int
     */
    public static int moveToCol(String coord){
        int col = coord.charAt(0) - 'A';
        return col;
    }
 
    /**
     * Méthode qui convertie l'entier 'y' en une chaîne de caractère 
     * @param x : Ligne de coordonnée x 
     * @param y : Colonne de coordonnée y 
     * @return Une chaîne de charactère représentant la colonne 
     */
    public static String moveToCol(int x, int y){
        return "" + (y+1);
    }

     
    /**
     * Méthode qui convertie la ligne : String en ligne : Int
     * @param coord : Chaîne de caractère qui represente les coordonnées
     * @requires La chaîne "coord" doit représenter une coordonnée valide sur la grille de jeu.
     * @return La Ligne converti en Int
     */
    public static int moveToRow(String coord){
        int row = Integer.parseInt(coord.substring(1))-1;
        return row;
    }

    /**
     * Méthode qui convertie l'entier 'x' en une chaîne de caractère 
     * @param x : Ligne de coordonnée x 
     * @param y : Colonne de coordonnée y 
     * @return Une chaîne de charactère représentant la ligne 
     */
    public static String moveToRow(int x, int y){
        return String.valueOf((char)(x+64));
    }


    /**
     * Méthode qui convertie les coordonnées sous forme de couple de coordonnées (x,y)
     * @param coord : Chaîne de caractère qui represente les coordonnées
     * @requires  "coord" doit représenter une coordonnée valide sur la grille de jeu.
     * @return Une chaine de caractère "(x,y)" où x est la ligne et y est la colonne.
     */
    public static String moveToCo(String coord){
        int row = Integer.parseInt(coord.substring(1));
        int col = coord.charAt(0) - 'A';

        return "(" + row + "," + col + ")";
    }

     /**
     * Méthode qui convertie l'entier 'x' et l'entier 'y' en une chaîne de caractère representant un couple de coordonnées.
     * On utilise "moveToRow" et "moveToCol" pour obtenir les représentations en String.
     * @param x : Ligne de coordonnée x 
     * @param y : Colonne de coordonnée y 
     * @return Une chaîne de charactère représentant la ligne et la colonne.
     */
    public static String moveToCo(int x, int y){
        return moveToRow(x+1, y) + moveToCol(x+1, y) ;
    }



    //////////////          AFFICHAGE               ////////////



    /**
     * Cette méthode parcours la grille et retourne une chaîne de caractères représentant la grille.
     * @return la chaine representant la grille 
     */
    public String toString(){
		String Str="";
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
        			Str=Str+this.grid[i][j]+"|";
        		}
	        }
		return Str;
	}

    //////////////          MÉTHODES RENVOYANT UNE LISTE               ////////////



    /**
     * Cette méthode parcours chaque case de la grille et trouve toutes les positions valides pour placer les bateaux restants dans la grille.
     * @return Une liste d'objet de type ArrayList<String> qui represente toute les positions valides pour placer les bateaux restants dans la grille.
     */
    public ArrayList<ArrayList<String>> validBoat(){
        ArrayList<ArrayList<String>> listValidBoat = new ArrayList<>();


        for (int x1 = 0;x1 < grid.length; x1++) {
            for (int y1 = 0;y1 < grid[x1].length;y1++) {

                //Analyse des différentes coordonnées sur ma même ligne
                for(int x2 = x1+1; x2 < x1 + Collections.max(this.listBoatRemaining)+1 && x2 < 10; x2++){
                    if(this.listBoatRemaining.contains(x2-x1+1)){
                        if(this.isValidBoat(x1, y1, x2, y1)){
                            listValidBoat.add(new ArrayList<String>());
                            listValidBoat.get(listValidBoat.size()-1).add(moveToCo(x1, y1));
                            listValidBoat.get(listValidBoat.size()-1).add(moveToCo(x2, y1));
                        }
                    }
                }


                //Analys des différentes coordonnées sur la même colonne
                for(int y2 = y1+1; y2 < y1 + Collections.max(this.listBoatRemaining)+1 && y2 < 10; y2++){
                    if(this.listBoatRemaining.contains(y2-y1+1)){
                        if(this.isValidBoat(x1, y1, x1, y2)){
                            listValidBoat.add(new ArrayList<String>());
                            listValidBoat.get(listValidBoat.size()-1).add(moveToCo(x1, y1));
                            listValidBoat.get(listValidBoat.size()-1).add(moveToCo(x1, y2));
                        }
                    }
                }
            }
        }

        return listValidBoat;

   }




   /**
     * Cette méthode parcours chaque case de la grille et vérifie si le coup est valide en appelant 'isValid'.
     * Si c'est le cas les coordonnées sont convertie et ajouté à une liste.
     * @ensures La liste retournée contient toutes les coordonnées valides où le joueur peut attaquer.
     * @return Une liste d'objet de type ArrayList<String> qui represente toute les cases de grilles valide pour une attque.
     */
    public ArrayList<String> validMoves(){
        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0;i < grid.length; i++) {
            for (int j = 0;j < grid[i].length;j++) {
                   if(isValid(i,j)){
                    String coord =  moveToCo(i, j);
                    moves.add(coord);
                   }
           }
       }
       return moves;
    }


    /**
     * Cette méthode parcours chaque case de la grille et récupère toutes les coordonnées des bateaux presents sur la grille.
     * @return Renvoie la list 'result' qui contient toute les coordonnées des bateaux encore présents sur la grille de jeu.
     */
public ArrayList<ArrayList<String>> getAllCoBoat(){
    ArrayList<ArrayList<String>> result = new ArrayList<>();

    for(int i=0;i<grid.length;i++){
        for(int j=0;j<grid[i].length;j++){
            if(grid[i][j].isBoat()){
            Boat test = (Boat) grid[i][j];
            String coStart = moveToCo(test.getCoStart().get(0),test.getCoStart().get(1));
            String coEnd = moveToCo(test.getCoEnd().get(0),test.getCoEnd().get(1));
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(coStart);
            tmp.add(coEnd);
            if(!result.contains(tmp)){
                result.add(tmp);
            }

            }

        }
    }
    return result;
}




    //////////////          MÉTHODE EXECUTABLE (void)               ////////////




    /**
     * Méthode qui convertie les coordonnées en Int et marque la case (correspondant aux coordonnées) touchée.
     * fireChange() envoie un message au controller pour le prévenir 
     * @param coord : Chaîne de caractère qui represente les coordonnées
     * @requires "coord" doit représenter une coordonnée valide sur la grille de jeu.
     */
    public void hit(String coord){
        int row = moveToRow(coord);
        int col = moveToCol(coord);
        this.grid[row][col].setHited();
        fireChange();
    }

    /**
     * Cette méthode convertie la coordonnée : string en coordonnée : int et appelle la méthode execute.
     * @param co representant une coordonnée sous forme d'une lettre pour la colonne et d'un chiffre pour la ligne.
     * @requires "co" doit être sous la forme d'une lettre entre A-J suivi d'un chiffre entre 0-9.
     */
    public void execute(String co){
        this.execute(moveToCol(co), moveToRow(co));
    }

     /**
     * Cette méthode execute l'action de tirer sur une cases modifiant ainsi l'état de la case sur la grille du jeu.
     * @param x : Ligne de coordonnée x 
     * @param y : Colonne de coordonnée y 
     * @requires int x,y entre 0-9.
     * @ensures l'état des cases de la grilles sont modifiés pour indiquer tels cases touchées ou tels bateaux est coulés.
     */
    public void execute(int x, int y){
        grid[x][y].setHited();
        
        if(grid[x][y].isBoat()){
            Boat copy = (Boat) grid[x][y];
            boolean boatSunked = true;

            for(int i = copy.getCoStart().get(0); i <= copy.getCoEnd().get(0); i++){
                for(int j = copy.getCoStart().get(1); j <= copy.getCoEnd().get(1); j++){
                    if(!grid[i][j].getHited()){
                        boatSunked = false;
                        break;
                    }
                }
            }


            if(boatSunked){
                for(int i = copy.getCoStart().get(0); i <= copy.getCoEnd().get(0); i++){
                    for(int j = copy.getCoStart().get(1); j <= copy.getCoEnd().get(1); j++){
                        grid[i][j].setState("sunked");
                    }
                }
            }


        }
        fireChange();
    }

    


      














    


    



}