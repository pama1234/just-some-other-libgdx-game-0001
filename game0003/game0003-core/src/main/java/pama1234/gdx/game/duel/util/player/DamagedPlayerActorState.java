package pama1234.gdx.game.duel.util.player;

import pama1234.app.game.server.duel.util.Const;
import pama1234.app.game.server.duel.util.player.PlayerActorState;
import pama1234.app.game.server.duel.util.player.ServerPlayerActor;
import pama1234.gdx.game.duel.Duel;
import pama1234.math.UtilMath;

public final class DamagedPlayerActorState extends PlayerActorState{
  private final Duel duel;
  public PlayerActorState moveState;
  public DamagedPlayerActorState(Duel duel) {
    this.duel=duel;
  }
  final int durationFrameCount=UtilMath.floor(0.75f*Const.IDEAL_FRAME_RATE);
  @Override
  public void act(ServerPlayerActor parentActor) {
    parentActor.damageRemainingFrameCount--;
    if(parentActor.damageRemainingFrameCount<=0) parentActor.state=moveState.entryState(parentActor);
  }
  @Override
  public void displayEffect(ServerPlayerActor parentActor) {
    duel.noFill();
    // duel.beginBlend();
    duel.stroke(192,64,64,UtilMath.floor(256*(float)parentActor.damageRemainingFrameCount/durationFrameCount));
    duel.circle(0,0,32);
    // duel.endBlend();
  }
  @Override
  public PlayerActorState entryState(ServerPlayerActor parentActor) {
    parentActor.damageRemainingFrameCount=durationFrameCount;
    return this;
  }
  @Override
  public boolean isDamaged() {
    return true;
  }
}