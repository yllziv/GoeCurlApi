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
        String server = "192.168.2.113";
        int instance = 5151; // ArcSDE 服务器的端口号
        String database = "arcse";
        String user = "sde";
        String password = "yll";
        try {
            conn = new SeConnection(server, instance, database, user, password);
        } catch (SeException e) {
            e.printStackTrace();
        }

        //取得存储与 ArcSDE 数据库内的的图层列表信息
        Vector layerList = conn.getLayers();
        for (int index = 0; index < layerList.size(); index++) {
            SeLayer layer = (SeLayer) layerList.elementAt(index);
            //图层名称
            System.out.println(layer.getName());
            // 显示图层ID
            System.out.println(layer.getID().longValue());
            //
            System.out.println(layer.getSpatialColumn());
        }
    }
}