package pama1234.gdx.game.duel.util.state;

import pama1234.game.app.server.duel.util.state.ServerGameSystemState;
import pama1234.gdx.game.duel.Duel;
import pama1234.gdx.game.duel.ClientGameSystem;

public abstract class ClientGameSystemState extends ServerGameSystemState{
  public final Duel duel;
  public ClientGameSystem system;
  //---
  public ClientGameSystemState(Duel duel,ClientGameSystem system) {
    super(system);
    this.duel=duel;
    this.system=system;
  }
  public void display() {
    displaySystem();
  }
  public void displayScreen() {
    displayMessage();
  }
  public abstract void displaySystem();
  public abstract void displayMessage();
}