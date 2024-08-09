package view;

import model.*;

public interface GameView {
    public void situationToString(BattleShips battle);
    public void situationToStringGrille();
    public void situationToStringBoat(BattleShips battle);
    public void print(String text);

}
