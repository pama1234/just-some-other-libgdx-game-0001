package pama1234.gdx.game.duel;

import pama1234.gdx.game.duel.util.actor.ActorGroup;
import pama1234.gdx.game.duel.util.actor.PlayerActor;
import pama1234.gdx.game.duel.util.ai.mech.ComputerPlayerEngine;
import pama1234.gdx.game.duel.util.ai.nnet.ComputerLifeEngine;
import pama1234.gdx.game.duel.util.graphics.DemoInfo;
import pama1234.gdx.game.duel.util.graphics.GameBackground;
import pama1234.gdx.game.duel.util.graphics.Particle;
import pama1234.gdx.game.duel.util.graphics.ParticleBuilder;
import pama1234.gdx.game.duel.util.graphics.ParticleSet;
import pama1234.gdx.game.duel.util.player.AndroidHumanPlayerEngine;
import pama1234.gdx.game.duel.util.player.DamagedPlayerActorState;
import pama1234.gdx.game.duel.util.player.DrawBowPlayerActorState;
import pama1234.gdx.game.duel.util.player.DrawLongbowPlayerActorState;
import pama1234.gdx.game.duel.util.player.DrawShortbowPlayerActorState;
import pama1234.gdx.game.duel.util.player.HumanPlayerEngine;
import pama1234.gdx.game.duel.util.player.MovePlayerActorState;
import pama1234.gdx.game.duel.util.player.PlayerEngine;
import pama1234.gdx.game.duel.util.state.GameSystemState;
import pama1234.gdx.game.duel.util.state.StartGameState;
import pama1234.math.UtilMath;

public final class GameSystem{
  public static final int start=1,play=2,result=3;
  //---
  public final Duel duel;
  public final ActorGroup myGroup,otherGroup;
  public final ParticleSet commonParticleSet;
  public GameSystemState currentState;
  public int stateIndex;
  public float screenShakeValue;
  public final DamagedPlayerActorState damagedState;
  public final GameBackground currentBackground;
  public final boolean demoPlay;
  public boolean showsInstructionWindow;
  public GameSystem(Duel duel,boolean demo,boolean instruction) {
    this.duel=duel;
    // prepare ActorGroup
    myGroup=new ActorGroup(0);
    otherGroup=new ActorGroup(1);
    myGroup.enemyGroup=otherGroup;
    otherGroup.enemyGroup=myGroup;
    // prepare PlayerActorState
    final MovePlayerActorState moveState=new MovePlayerActorState();
    final DrawBowPlayerActorState drawShortbowState=new DrawShortbowPlayerActorState(duel);
    final DrawBowPlayerActorState drawLongbowState=new DrawLongbowPlayerActorState(duel);
    damagedState=new DamagedPlayerActorState(duel);
    moveState.drawShortbowState=drawShortbowState;
    moveState.drawLongbowState=drawLongbowState;
    drawShortbowState.moveState=moveState;
    drawLongbowState.moveState=moveState;
    damagedState.moveState=moveState;
    // prepare PlayerActor
    PlayerEngine myEngine;
    if(demo) myEngine=createComputerEngine(true);
    else {
      if(duel.isAndroid) myEngine=new AndroidHumanPlayerEngine(duel.currentInput);
      else myEngine=new HumanPlayerEngine(duel.currentInput);
    }
    PlayerActor myPlayer=new PlayerActor(duel,myEngine,duel.config.mode==Config.neat?Duel.color(0):Duel.color(255));
    myPlayer.xPosition=Duel.CANVAS_SIZE*0.5f;
    myPlayer.yPosition=Duel.CANVAS_SIZE-100;
    myPlayer.state=moveState;
    myGroup.setPlayer(myPlayer);
    PlayerEngine otherEngine=createComputerEngine(false);
    PlayerActor otherPlayer=new PlayerActor(duel,otherEngine,Duel.color(0));
    otherPlayer.xPosition=Duel.CANVAS_SIZE*0.5f;
    otherPlayer.yPosition=100;
    otherPlayer.state=moveState;
    otherGroup.setPlayer(otherPlayer);
    // other
    commonParticleSet=new ParticleSet(duel,2048);
    currentState(new StartGameState(duel,this));
    currentBackground=new GameBackground(duel,Duel.color(224),0.1f);
    demoPlay=demo;
    showsInstructionWindow=instruction;
  }
  public PlayerEngine createComputerEngine(boolean side) {
    if(duel.config.mode==Config.neat) {
      // if(type) return new ComputerPlayerEngine(duel::random);
      // else return new ComputerLifeEngine((type?duel.player_a:duel.player_b).graphics,duel.neatCenter.getNext());
      return new ComputerLifeEngine((side?duel.player_a:duel.player_b).graphics,duel.neatCenter.getNext(),side);
    }else return new ComputerPlayerEngine(duel::random);
  }
  public GameSystem(Duel duel) {
    this(duel,false,false);
  }
  public void run() {
    update();
    display();
  }
  public void update() {
    if(demoPlay) {
      if(duel.currentInput.isZPressed) {
        duel.system=new GameSystem(duel); // stop demo and start game
        return;
      }
    }
    currentBackground.update();
    currentState.update();
  }
  public void display() {
    duel.pushMatrix();
    if(screenShakeValue>0) {
      duel.translate(duel.random(screenShakeValue,screenShakeValue),duel.random(-screenShakeValue,screenShakeValue));
      screenShakeValue-=50f/Duel.IDEAL_FRAME_RATE;
    }
    currentBackground.display();
    currentState.display();
    duel.popMatrix();
  }
  public void displayScreen() {
    currentState.displayScreen();
    if(demoPlay&&showsInstructionWindow) DemoInfo.displayDemo(duel);
  }
  public void addSquareParticles(float x,float y,int particleCount,float particleSize,float minSpeed,float maxSpeed,float lifespanSecondValue) {
    final ParticleBuilder builder=duel.system.commonParticleSet.builder
      .type(Particle.square)
      .position(x,y)
      .particleSize(particleSize)
      .particleColor(Duel.color(0))
      .lifespanSecond(lifespanSecondValue);
    for(int i=0;i<particleCount;i++) {
      final Particle newParticle=builder
        .polarVelocity(duel.random(UtilMath.PI2),duel.random(minSpeed,maxSpeed))
        .build();
      duel.system.commonParticleSet.particleList.add(newParticle);
    }
  }
  public void currentState(GameSystemState currentState) {
    this.currentState=currentState;
    duel.stateChangeEvent(this,stateIndex);
  }
}