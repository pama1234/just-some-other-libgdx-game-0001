package pama1234.gdx.game.duel.util.player;

import pama1234.gdx.game.duel.util.actor.PlayerActor;
import pama1234.gdx.game.duel.util.input.AbstractInputDevice;
import pama1234.gdx.game.duel.util.input.InputDevice;

public abstract class PlayerEngine{
  public final AbstractInputDevice inputDevice;
  public PlayerEngine() {
    inputDevice=new InputDevice();
  }
  public abstract void run(PlayerActor player);
  public abstract void setScore(int scoreType,float score);
  public abstract float getScore(int index);
}