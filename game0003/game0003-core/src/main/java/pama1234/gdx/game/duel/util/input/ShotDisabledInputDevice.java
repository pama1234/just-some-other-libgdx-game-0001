package pama1234.gdx.game.duel.util.input;

import pama1234.game.app.server.duel.util.input.AbstractInputDevice;

public final class ShotDisabledInputDevice extends AbstractInputDevice{
  @Override
  public void operateShotButton(boolean pressed) {}
  @Override
  public void operateLongShotButton(boolean pressed) {}
}