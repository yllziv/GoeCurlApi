package com.yanglonglong.connectSDE;

import com.esri.sde.sdk.client.SeConnection;
import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeLayer;

import java.util.Vector;

/**
 * 通过调用ArcSDE的java接口，读取数据库图层信息
 */
public class GetArcSDEList {

    public static void main(String[] args) throws Exception {
        //建立一个 ArcSDE 服务器连接
        SeConnection conn = null;
        String server     = "192.168.2.113";
        int instance      = 5151; // ArcSDE 服务器的端口号
        String database   = "arcse";
        String user       = "sde";
        String password   = "yll";
        try {
            conn = new SeConnection(server, instance, database, user, password);
        }catch (SeException e) {
            e.printStackTrace();
        }

        //取得存储与 ArcSDE 数据库内的的图层列表信息
        Vector layerList = conn.getLayers();
        for( int index = 0 ; index < layerList.size() ; index++ ) {
            SeLayer layer = (SeLayer)layerList.elementAt(index);
            // Displays the layer’s name
            System.out.println( layer.getName() );
            // Displays the layer’s ID
            System.out.println( layer.getID().longValue() );
            // Displays the layer’s spatial column name
            System.out.println( layer.getSpatialColumn() );
        }
    }
}