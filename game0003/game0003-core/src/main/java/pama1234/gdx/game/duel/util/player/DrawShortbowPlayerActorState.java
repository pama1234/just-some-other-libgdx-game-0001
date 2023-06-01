package pama1234.gdx.game.duel.util.player;

import pama1234.game.app.server.duel.util.Const;
import pama1234.game.app.server.duel.util.input.AbstractInputDevice;
import pama1234.game.app.server.duel.util.player.PlayerActorState;
import pama1234.game.app.server.duel.util.player.ServerPlayerActor;
import pama1234.gdx.game.duel.Duel;
import pama1234.gdx.game.duel.util.actor.ShortbowArrow;
import pama1234.math.UtilMath;

public final class DrawShortbowPlayerActorState extends DrawBowPlayerActorState{
  private final Duel duel;
  public final int fireIntervalFrameCount=UtilMath.floor(Const.IDEAL_FRAME_RATE*0.2f);
  public DrawShortbowPlayerActorState(Duel duel) {
    this.duel=duel;
  }
  @Override
  public void aim(ServerPlayerActor parentActor,AbstractInputDevice input) {
    parentActor.aimAngle=getEnemyPlayerActorAngle(parentActor);
  }
  @Override
  public void fire(ServerPlayerActor parentActor) {
    ShortbowArrow newArrow=new ShortbowArrow(this.duel);
    final float directionAngle=parentActor.aimAngle;
    newArrow.xPosition=parentActor.xPosition+24*UtilMath.cos(directionAngle);
    newArrow.yPosition=parentActor.yPosition+24*UtilMath.sin(directionAngle);
    newArrow.rotationAngle=directionAngle;
    newArrow.setVelocity(directionAngle,24);
    parentActor.group.addArrow(newArrow);
  }
  @Override
  public void displayEffect(ServerPlayerActor parentActor) {
    duel.strokeWeight(3);
    duel.line(0,0,70*UtilMath.cos(parentActor.aimAngle),70*UtilMath.sin(parentActor.aimAngle));
    duel.noFill();
    duel.arc(0,0,50,UtilMath.deg(parentActor.aimAngle)-22.5f,45);
  }
  @Override
  public PlayerActorState entryState(ServerPlayerActor parentActor) {
    return this;
  }
  @Override
  public boolean buttonPressed(AbstractInputDevice input) {
    return input.shotButtonPressed;
  }
  @Override
  public boolean triggerPulled(ServerPlayerActor parentActor) {
    return duel.frameCount%fireIntervalFrameCount==0;
  }
}