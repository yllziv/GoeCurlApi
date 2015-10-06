package com.yanglonglong.connectSDE;

import com.esri.sde.sdk.client.SeConnection;
import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeLayer;

import java.util.Vector;

/**
 * ͨ������ArcSDE��java�ӿڣ���ȡ���ݿ�ͼ����Ϣ
 */
public class GetArcSDEList {

    public static void main(String[] args) throws Exception {
        //����һ�� ArcSDE ����������
        SeConnection conn = null;
        String server     = "192.168.2.113";
        int instance      = 5151; // ArcSDE �������Ķ˿ں�
        String database   = "arcse";
        String user       = "sde";
        String password   = "yll";
        try {
            conn = new SeConnection(server, instance, database, user, password);
        }catch (SeException e) {
            e.printStackTrace();
        }

        //ȡ�ô洢�� ArcSDE ���ݿ��ڵĵ�ͼ���б���Ϣ
        Vector layerList = conn.getLayers();
        for( int index = 0 ; index < layerList.size() ; index++ ) {
            SeLayer layer = (SeLayer)layerList.elementAt(index);
            // Displays the layer��s name
            System.out.println( layer.getName() );
            // Displays the layer��s ID
            System.out.println( layer.getID().longValue() );
            // Displays the layer��s spatial column name
            System.out.println( layer.getSpatialColumn() );
        }
    }
}