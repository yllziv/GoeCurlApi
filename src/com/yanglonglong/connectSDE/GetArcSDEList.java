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
 * 通过调用ArcSDE的java接口，读取数据库图层信息
 */
public class GetArcSDEList {

    private static String sdeServer = "192.168.2.113";
    private static int sdeInstance = 5151; // ArcSDE 服务器的端口号
    private static String sdeDatabase = "arcse";
    private static String sdeUser = "sde";
    private static String sdePassword = "yll";


    public static void main(String[] args) throws Exception {
        ArrayList<String> allLayers = getAllLayers();
        ArrayList<String> publishLayers = getAlreadyPuclicService();
        ArrayList<String> unPublishLayers = new ArrayList<>();
        for(String tempLayers : allLayers){
            if(publishLayers.contains(tempLayers)){}
            else{
                unPublishLayers.add(tempLayers);
            }
        }
        System.out.println(unPublishLayers.toString());
    }


    /*获取所有图层*/
    public static ArrayList<String> getAllLayers() throws Exception {
        ArrayList<String> allLayers = new ArrayList<String>();
        //建立一个 ArcSDE 服务器连接
        SeConnection conn = null;
        String server = sdeServer;
        int instance = sdeInstance; // ArcSDE 服务器的端口号
        String database = sdeDatabase;
        String user = sdeUser;
        String password = sdePassword;
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
            allLayers.add(user.toUpperCase()+"."+layer.getName());
        }
//        System.out.println(allLayers);
        return allLayers;
    }


    /*获取ArcSDE中已经发布的服务*/
    public static ArrayList<String> getAlreadyPuclicService(){
        ArrayList<String> alreadyLayers = new ArrayList<String>();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            // curl -u
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 8080),
                    new UsernamePasswordCredentials("admin", "geoserver"));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //abcd为工作区名称，formSDE为数据存储的名称
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