package pama1234.gdx.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Game;

import pama1234.gdx.game.app.Screen0001;
import pama1234.gdx.game.app.Screen0019;
import pama1234.gdx.util.app.UtilScreen;

public class MainApp extends Game{
  public static final String[] typeName=new String[] {"通用版","Taptap版","Pico4-VR版"};
  public static final int defaultType=0,taptap=1,pico=2;
  public static int type;
  public List<Class<? extends UtilScreen>> screenList;
  public int screenType=2;
  public MainApp() {
    screenList=Arrays.asList(null,
      Screen0001.class,//游戏本体            <---------# √
      Screen0019.class);
  }
  @Override
  public void create() {
    try {
      setScreen(screenList.get(screenType).getDeclaredConstructor().newInstance());
    }catch(InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException|NoSuchMethodException|SecurityException e) {
      e.printStackTrace();
    }
  }
  @Override
  public void dispose() {
    super.dispose();
    screen.dispose();
  }
}