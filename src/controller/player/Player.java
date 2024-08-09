package controller.player;

import java.util.*;
import model.*;

public interface Player {
    public String attackCase(BattleShips nav);
    public ArrayList<String> setBoat(BattleShips nav);
    public boolean equals(Object o);
}
