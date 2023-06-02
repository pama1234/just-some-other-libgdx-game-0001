package pama1234.app.game.server.duel;

import pama1234.app.game.server.duel.util.actor.ActorGroup;
import pama1234.app.game.server.duel.util.state.ServerGameSystemState;

public class ServerGameSystem{
  public static final int start=1,play=2,result=3;
  //---
  public final ActorGroup myGroup,otherGroup;
  public ServerGameSystemState currentState;
  public int stateIndex;
  public float screenShakeValue;
  public final boolean demoPlay;
  public boolean showsInstructionWindow;
  public ServerGameSystem(boolean demo) {
    myGroup=new ActorGroup(0);
    otherGroup=new ActorGroup(1);
    demoPlay=demo;
  }
}
