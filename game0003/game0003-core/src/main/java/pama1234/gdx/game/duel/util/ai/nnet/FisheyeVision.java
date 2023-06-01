package pama1234.gdx.game.duel.util.ai.nnet;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import pama1234.game.app.server.duel.util.Const;
import pama1234.game.app.server.duel.util.actor.AbstractPlayerActor;
import pama1234.gdx.game.duel.Duel;
import pama1234.gdx.util.element.Graphics;

public class FisheyeVision{
  public Duel duel;
  public ShaderProgram shader;
  public Color backgroundColor;
  public Graphics graphics;
  public float camX,camY;
  //---
  public FisheyeVision(Duel duel,ShaderProgram shader,Graphics graphics) {
    this.duel=duel;
    this.shader=shader;
    this.graphics=graphics;
    backgroundColor=Duel.color(191);
  }
  public void update(AbstractPlayerActor player) {
    shader.bind();
    if(Float.isFinite(player.xPosition)&&
      Float.isFinite(player.yPosition)) {
      camX=player.xPosition;
      camY=player.yPosition;
    }
    shader.setUniformf("u_dist",camX/Const.CANVAS_SIZE,1-camY/Const.CANVAS_SIZE);
  }
  public void render() {
    graphics.begin();
    duel.background(backgroundColor);
    duel.image(duel.graphics.texture,0,0,graphics.width(),graphics.height(),shader);
    graphics.end();
  }
}