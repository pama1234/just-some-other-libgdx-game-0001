package pama1234.gdx.game.duel.util.player;

import pama1234.game.app.server.duel.util.player.ServerPlayerActor;
import pama1234.gdx.game.duel.util.input.InputData;

public class AndroidHumanPlayerEngine extends HumanPlayerEngine{
  public AndroidHumanPlayerEngine(InputData keyInput) {
    super(keyInput);
  }
  @Override
  public void run(ServerPlayerActor player) {
    inputDevice.operateMove(currentInput.dx,currentInput.dy);
    inputDevice.operateShotButton(currentInput.isZPressed);
    inputDevice.operateLongShotButton(currentInput.isXPressed);
  }
}