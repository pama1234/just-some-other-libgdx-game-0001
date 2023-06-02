package pama1234.app.game.server.duel;

public class Config{
  public static final int game=0,neat=1;
  public int mode;
  public Config init(boolean isAndroid) {
    mode=isAndroid?game:neat;
    return this;
  }
}
