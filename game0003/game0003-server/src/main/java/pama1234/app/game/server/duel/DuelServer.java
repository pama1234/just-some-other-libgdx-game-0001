package pama1234.app.game.server.duel;

import pama1234.app.game.server.duel.util.input.ServerInputData;
import pama1234.util.UtilServer;

public class DuelServer extends UtilServer{
  // public int frameCount;
  public ServerInputData currentInput;
  public Config config;
  public ServerGameSystem system;
  public int timeLimitConst=60*10;
  public int time,timeLimit=timeLimitConst;
  @Override
  public void init() {
    newGame(true);
  }
  @Override
  public void update() {
    // frameCount++;
  }
  @Override
  public void dispose() {}
  public void newGame(boolean demo) {
    system=new ServerGameSystem(this,demo,true);
  }
  public void stateChangeEvent(ServerGameSystem system,int stateIndex) {
    if(system.stateIndex==ServerGameSystem.play) time=0;
    else if(system.stateIndex==ServerGameSystem.result) {
      system.myGroup.player.engine.setScore(0,system.currentState.getScore(system.myGroup.id));
      system.otherGroup.player.engine.setScore(0,system.currentState.getScore(system.otherGroup.id));
    }
  }
}
