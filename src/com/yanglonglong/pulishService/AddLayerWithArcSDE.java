package com.yanglonglong.pulishService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;

/**
 * 实现在Geoserver中添加ArcSDE的图层
 * curl -v -u admin:geoserver -XPOST -H "Content-type: text/xml"
 * -d "<featureType><name>SDE.LUWANG</name></featureType>"
 * http://localhost:8080/geoserver/rest/workspaces/abcd/datastores/fromSDE/featuretypes
 */
public class AddLayerWithArcSDE {
    public static void addLayer(HashMap addLayerPara) throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            // curl -u
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope((String) addLayerPara.get("ip"), Integer.parseInt(addLayerPara.get("port").toString())),
                    new UsernamePasswordCredentials((String) addLayerPara.get("user"), (String) addLayerPara.get("passwd")));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            // abcd为工作区，fromSDE为数据存储的名称
            HttpPost httppost = new HttpPost("http://localhost:8080/geoserver/rest/workspaces/"+addLayerPara.get("workspace")+"/datastores/"+addLayerPara.get("datastory")+"/featuretypes");
            // curl -H
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            // curl -d
            String transData = "<featureType><name>"+addLayerPara.get("name")+"</name></featureType>";
            httppost.setEntity(new StringEntity(transData));

            // curl -v
            System.out.println("executing request" + httppost.getRequestLine());
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("----------------------------------------");
            String pageHTML = EntityUtils.toString(responseEntity);
            System.out.println(pageHTML);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("Response content: " + response.toString());
            }
            EntityUtils.consume(responseEntity);
        } finally {
            int a = 1;
            int b = 1;
            int s = 1;
            httpclient.getConnectionManager().shutdown();
        }
    }
    public static void main(String[] args) throws Exception {
        HashMap<String,String> addLayerPara =  new HashMap<String,String>();
        addLayerPara.put("name","SDE.LUWANG");
        addLayerPara.put("ip","localhost");
        addLayerPara.put("port","8080");
        addLayerPara.put("user","admin");
        addLayerPara.put("passwd","geoserver");
        addLayerPara.put("workspace","abcd");
        addLayerPara.put("datastory","fromSDE");
        addLayer(addLayerPara);
    }
}