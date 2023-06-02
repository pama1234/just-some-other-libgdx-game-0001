package pama1234.app.game.server.duel;

import pama1234.util.UtilServer;

public class DuelSever extends UtilServer{
  public ServerGameSystem system;
  @Override
  public void init() {
    newGame(true);
  }
  @Override
  public void update() {}
  @Override
  public void dispose() {}
  public void newGame(boolean demo) {
    system=new ServerGameSystem(demo);
  }
}
