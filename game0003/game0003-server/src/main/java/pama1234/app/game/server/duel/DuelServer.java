package pama1234.app.game.server.duel;

import pama1234.app.game.server.duel.util.ai.neat.ServerFisheyeVision;
import pama1234.app.game.server.duel.util.input.ServerInputData;
import pama1234.util.UtilServer;

public class DuelServer extends UtilServer{
  public boolean paused;
  public ServerInputData currentInput;
  public Config config;
  public ServerGameSystem system;
  public ServerFisheyeVision player_a,player_b;
  public int timeLimitConst=60*10;
  public int time,timeLimit=timeLimitConst;
  @Override
  public void init() {
    newGame(true);
  }
  @Override
  public void update() {
    if(!paused) {
      system.update();
      //---
      if(config.mode==Config.neat) {
        if(system.stateIndex==ServerGameSystem.play) {
          time++;
          system.myGroup.player.engine.setScore(1,system.currentState.getScore(system.myGroup.id));
          system.otherGroup.player.engine.setScore(1,system.currentState.getScore(system.otherGroup.id));
          if(time>timeLimit) {
            timeLimit=timeLimitConst;
            newGame(true);
          }
        }
        player_a.update(system.myGroup.player);
        player_b.update(system.otherGroup.player);
      }
    }
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
