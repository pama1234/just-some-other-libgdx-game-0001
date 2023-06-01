package pama1234.gdx.game.duel;

public class Config{
  public static final int game=0,neat=1;
  public int mode;
  public Config init(Duel p) {
    mode=p.isAndroid?game:neat;
    return this;
  }
}
