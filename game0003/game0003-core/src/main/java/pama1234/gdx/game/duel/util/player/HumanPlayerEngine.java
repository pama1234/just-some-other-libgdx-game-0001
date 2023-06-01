package pama1234.gdx.game.duel.util.player;

import pama1234.app.game.server.duel.util.player.PlayerEngine;
import pama1234.app.game.server.duel.util.player.ServerPlayerActor;
import pama1234.gdx.game.duel.util.input.ClientInputData;

public class HumanPlayerEngine extends PlayerEngine{
  public final ClientInputData currentInput;
  public HumanPlayerEngine(ClientInputData keyInput) {
    currentInput=keyInput;
  }
  @Override
  public void run(ServerPlayerActor player) {
    final int intUp=currentInput.isUpPressed?-1:0;
    final int intDown=currentInput.isDownPressed?1:0;
    final int intLeft=currentInput.isLeftPressed?-1:0;
    final int intRight=currentInput.isRightPressed?1:0;
    inputDevice.operateMoveButton(intLeft+intRight,intUp+intDown);
    inputDevice.operateShotButton(currentInput.isZPressed);
    inputDevice.operateLongShotButton(currentInput.isXPressed);
  }
  @Override
  public void setScore(int scoreType,float score) {
    //TODO nop
  }
  @Override
  public float getScore(int index) {
    return 0; //TODO nop
  }
}