package pama1234.app.game.server.server0003;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import pama1234.util.UtilServer;
import pama1234.util.protobuf.PointUpdateProto.PointUpdate;
import pama1234.util.wrapper.Center;

public class Server0004 extends UtilServer{
  Center<PointUpdate> pointCenter=new Center<>();
  @Override
  public void init() {}
  @Override
  public void update() {
    try {
      Socket socket=new Socket("localhost",8080);
      InputStream in=socket.getInputStream();
      byte[] received=new byte[1024];
      int len=in.read(received);
      OutputStream out=socket.getOutputStream();
      // 将接收到的字节数组直接发送回发送方
      out.write(received,0,len);
      out.flush();
      // 关闭网络连接
      in.close();
      out.close();
      socket.close();
    }catch(IOException e) {
      e.printStackTrace();
    }
  }
  @Override
  public void dispose() {}
}
