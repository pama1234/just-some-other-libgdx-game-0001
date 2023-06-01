package pama1234.gdx.game.duel.util.arrow;

import pama1234.app.game.server.duel.util.arrow.AbstractArrowActor;
import pama1234.gdx.game.duel.Duel;
import pama1234.gdx.game.duel.util.graphics.Particle;
import pama1234.math.UtilMath;

public class ShortbowArrow extends AbstractArrowActor{
  public final Duel duel;
  public final float terminalSpeed;
  public final float halfHeadLength=8;
  public final float halfHeadWidth=4;
  public final float halfFeatherWidth=4;
  public final float featherLength=8;
  public ShortbowArrow(Duel duel) {
    super(8,20);
    this.duel=duel;
    terminalSpeed=8;
  }
  @Override
  public void update() {
    xVelocity=speed*UtilMath.cos(directionAngle);
    yVelocity=speed*UtilMath.sin(directionAngle);
    super.update();
    speed+=(terminalSpeed-speed)*0.1f;
  }
  @Override
  public void act() {
    if((duel.random(1)>=0.5f)) return;
    final float particleDirectionAngle=this.directionAngle+UtilMath.PI+duel.random(-UtilMath.QUARTER_PI,UtilMath.QUARTER_PI);
    for(int i=0;i<3;i++) {
      final float particleSpeed=duel.random(0.5f,2);
      final Particle newParticle=duel.system.commonParticleSet.builder
        .type(Particle.square)
        .position(this.xPosition,this.yPosition)
        .polarVelocity(particleDirectionAngle,particleSpeed)
        .particleSize(2)
        .particleColor(Duel.color(192))
        .lifespanSecond(0.5f)
        .build();
      duel.system.commonParticleSet.particleList.add(newParticle);
    }
  }
  @Override
  public void display() {
    duel.strokeWeight(3);
    duel.stroke(0);
    duel.doFill();
    duel.fill(0);
    duel.pushMatrix();
    duel.translate(xPosition,yPosition);
    duel.rotate(rotationAngle);
    duel.line(-halfLength,0,halfLength,0);
    duel.quad(
      halfLength,0,
      halfLength-halfHeadLength,-halfHeadWidth,
      halfLength+halfHeadLength,0,
      halfLength-halfHeadLength,+halfHeadWidth);
    duel.line(-halfLength,0,-halfLength-featherLength,-halfFeatherWidth);
    duel.line(-halfLength,0,-halfLength-featherLength,+halfFeatherWidth);
    duel.line(-halfLength+4,0,-halfLength-featherLength+4,-halfFeatherWidth);
    duel.line(-halfLength+4,0,-halfLength-featherLength+4,+halfFeatherWidth);
    duel.line(-halfLength+8,0,-halfLength-featherLength+8,-halfFeatherWidth);
    duel.line(-halfLength+8,0,-halfLength-featherLength+8,+halfFeatherWidth);
    duel.popMatrix();
  }
  @Override
  public boolean isLethal() {
    return false;
  }
}