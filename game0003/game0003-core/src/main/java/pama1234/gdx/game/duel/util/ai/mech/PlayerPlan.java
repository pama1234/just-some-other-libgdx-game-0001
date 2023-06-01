package pama1234.gdx.game.duel.util.ai.mech;

import pama1234.game.app.server.duel.util.input.AbstractInputDevice;
import pama1234.game.app.server.duel.util.player.ServerPlayerActor;

public abstract class PlayerPlan{
  public abstract void execute(ServerPlayerActor player,AbstractInputDevice input);
  public abstract PlayerPlan nextPlan(ServerPlayerActor player);
}