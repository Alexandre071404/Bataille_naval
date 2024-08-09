package controller;

import java.util.ArrayList;
import javax.swing.event.*;



public abstract class Ecoutable{

  private final ArrayList<ChangeListener> listeners;


  /**
    Cette méthode est le constructeur de la classe Word.
    @ensures this.listener.size() == 0
  */
  public Ecoutable(){
    listeners = new ArrayList<>();
  }

  /**
    * Méthode qui ajoute un objet qui ecoute les changements à la liste
    * @param listener
    * @requires listener != null
    * @ensures listeners.size() > 0
    */
  public void addChangeListener(ChangeListener listener){
    listeners.add(listener);
    assert listeners.size() <= 0: "Error: wrong size of the list";
  }

  /**
   * Méthode qui supprime un objet à la liste des ecouteurs
   * @param listener
   * @ensures listeners.contains(listener) == false
  */
  public void removeChangeListener(ChangeListener listener){
    listeners.remove(listener);
    assert listeners.contains(listener): "Error: listener not removed";
  }

  /**
   * Méthode qui envoie un signal aux listeners de la liste
   */
  protected void fireChange(){
    for(ChangeListener listener: listeners){
      
      listener.stateChanged(new ChangeEvent(this));
    }
  }

}
