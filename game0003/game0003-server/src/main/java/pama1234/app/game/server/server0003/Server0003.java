package pama1234.app.game.server.server0003;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;

import pama1234.util.UtilServer;
import pama1234.util.protobuf.PointUpdateProto.PointUpdate;
import pama1234.util.protobuf.PointUpdateProto.PointUpdateList;

public class Server0003 extends UtilServer{
  @Override
  public void init() { // PointUpdateList listBuild=pointUpdateListBuilder.build();
    PointUpdate.Builder pointUpdate1=PointUpdate.newBuilder();
    pointUpdate1.setX(10);
    pointUpdate1.setY(20);
    pointUpdate1.setType(1);
    PointUpdate.Builder pointUpdate2=PointUpdate.newBuilder();
    pointUpdate2.setX(30);
    pointUpdate2.setY(40);
    pointUpdate2.setType(2);
    PointUpdate.Builder pointUpdate3=PointUpdate.newBuilder();
    pointUpdate3.setX(50);
    pointUpdate3.setY(60);
    pointUpdate3.setType(3);
    List<PointUpdate> pointUpdates=new ArrayList<>();
    pointUpdates.add(pointUpdate1.build());
    pointUpdates.add(pointUpdate2.build());
    pointUpdates.add(pointUpdate3.build());
    PointUpdateList.Builder pointUpdateListBuilder=PointUpdateList.newBuilder();
    pointUpdateListBuilder.addAllUpdates(pointUpdates);
    PointUpdateList pointUpdateList=pointUpdateListBuilder.build();
    byte[] serialized=pointUpdateList.toByteArray();
    try {
      PointUpdateList parsedPointUpdateList=PointUpdateList.parseFrom(serialized);
      for(PointUpdate update:parsedPointUpdateList.getUpdatesList()) {
        System.out.println("x: "+update.getX()+", y: "+update.getY()+", type: "+update.getType());
      }
    }catch(InvalidProtocolBufferException e) {
      e.printStackTrace();
    }
  }
  @Override
  public void update() {}
  @Override
  public void dispose() {}
}