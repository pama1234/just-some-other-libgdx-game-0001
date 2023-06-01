package pama1234.app.game.server.duel;

import pama1234.util.UtilServer;

public class DuelSever extends UtilServer{
  public ServerGameSystem system;
  @Override
  public void init() {
    system=new ServerGameSystem();
  }
  @Override
  public void update() {}
  @Override
  public void dispose() {}
}
