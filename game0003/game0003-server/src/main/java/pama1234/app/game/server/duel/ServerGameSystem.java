package pama1234.app.game.server.duel;

import pama1234.app.game.server.duel.util.actor.ActorGroup;

public class ServerGameSystem{
  public static final int start=1,play=2,result=3;
  //---
  public final ActorGroup myGroup,otherGroup;
  public ServerGameSystem() {
    myGroup=new ActorGroup(0);
    otherGroup=new ActorGroup(1);
  }
}
