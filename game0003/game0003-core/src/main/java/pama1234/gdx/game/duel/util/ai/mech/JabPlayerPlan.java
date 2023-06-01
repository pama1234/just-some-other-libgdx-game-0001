package pama1234.gdx.game.duel.util.ai.mech;

import pama1234.app.game.server.duel.util.input.AbstractInputDevice;
import pama1234.app.game.server.duel.util.player.ServerPlayerActor;
import pama1234.gdx.game.duel.GetRandom;

public final class JabPlayerPlan extends DefaultPlayerPlan{
  public JabPlayerPlan(GetRandom rng) {
    super(rng);
  }
  @Override
  public void execute(ServerPlayerActor player,AbstractInputDevice input) {
    super.execute(player,input);
    input.operateShotButton(true);
  }
}