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
        String server = "192.168.2.113";
        int instance = 5151; // ArcSDE �������Ķ˿ں�
        String database = "arcse";
        String user = "sde";
        String password = "yll";
        try {
            conn = new SeConnection(server, instance, database, user, password);
        } catch (SeException e) {
            e.printStackTrace();
        }

        //ȡ�ô洢�� ArcSDE ���ݿ��ڵĵ�ͼ���б���Ϣ
        Vector layerList = conn.getLayers();
        for (int index = 0; index < layerList.size(); index++) {
            SeLayer layer = (SeLayer) layerList.elementAt(index);
            //ͼ������
            System.out.println(layer.getName());
            // ��ʾͼ��ID
            System.out.println(layer.getID().longValue());
            //
            System.out.println(layer.getSpatialColumn());
        }
    }
    public static String getAlreadyPuclicService(){

        return "";
    }
}