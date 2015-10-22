package com.yanglonglong.connectSDE;

import com.esri.sde.sdk.client.SeConnection;
import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeLayer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * ͨ������ArcSDE��java�ӿڣ���ȡ���ݿ�ͼ����Ϣ
 */
public class GetArcSDEList {

    private static String sdeServer = "192.168.2.142";
    private static int sdeInstance = 5151; // ArcSDE �������Ķ˿ں�
    private static String sdeDatabase = "arcse";
    private static String sdeUser = "sde";
    private static String sdePassword = "yll";


    public static void main(String[] args) throws Exception {
        ArrayList<String> allLayers = getAllLayers();
        System.out.println(allLayers.toString());
//        ArrayList<String> publishLayers = getAlreadyPuclicService();
//        ArrayList<String> unPublishLayers = new ArrayList<>();
//        for(String tempLayers : allLayers){
//            if(publishLayers.contains(tempLayers)){}
//            else{
//                unPublishLayers.add(tempLayers);
//            }
//        }

    }


    /*��ȡ����ͼ��*/
    public static ArrayList<String> getAllLayers() throws Exception {
        ArrayList<String> allLayers = new ArrayList<String>();
        //����һ�� ArcSDE ����������
        SeConnection conn = null;
        String server = sdeServer;
        int instance = sdeInstance; // ArcSDE �������Ķ˿ں�
        String database = sdeDatabase;
        String user = sdeUser;
        String password = sdePassword;
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
            allLayers.add(user.toUpperCase()+"."+layer.getName());
        }
//        System.out.println(allLayers);
        return allLayers;
    }


    /*��ȡArcSDE���Ѿ������ķ���*/
    public static ArrayList<String> getAlreadyPuclicService(){
        ArrayList<String> alreadyLayers = new ArrayList<String>();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            // curl -u
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 8080),
                    new UsernamePasswordCredentials("admin", "geoserver"));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //abcdΪ���������ƣ�formSDEΪ���ݴ洢������
            HttpGet httpget = new HttpGet("http://localhost:8080/geoserver/rest/workspaces/abcd/datastores/fromSDE.html");
            // curl -H
            httpget.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
            // curl -v
//            System.out.println("executing request" + httpget.getRequestLine());
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity responseEntity = response.getEntity();
//            System.out.println("----------------------------------------");
            String pageHTML = EntityUtils.toString(responseEntity);
            Document doc = Jsoup.parse(pageHTML);
            Elements lis = doc.select("ul>li");
            for(org.jsoup.nodes.Element li : lis){
                String liText = li.text();
                alreadyLayers.add(liText);
            }
            if (responseEntity != null) {
//                System.out.println("Response content: " + response.toString());
            }
            EntityUtils.consume(responseEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
            return alreadyLayers;
        }
    }
}