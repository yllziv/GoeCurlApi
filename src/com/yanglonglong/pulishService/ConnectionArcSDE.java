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
 * ʵ����Geoserver�����ArcSDE�����ݴ洢
 * curl -v -u admin:geoserver -XPOST -T arcseDatastore.xml -H "Content-type: text/xml"
 * http://localhost:8080/geoserver/rest/workspaces/abcd/datastores
 */
public class ConnectionArcSDE {
    public static void connectionArcSDE(HashMap dataStoryPara)throws Exception{
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            // curl -u
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 8080),
                    new UsernamePasswordCredentials("admin", "geoserver"));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            // abcdΪ������
            HttpPost httppost = new HttpPost("http://localhost:8080/geoserver/rest/workspaces/abcd/datastores");
            // curl -H
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            // curl -d
//            String xml = "<?xml version="
//                    + "\"1.0\""
//                    + " encoding="
//                    + "\"UTF-8\""
//                    + "?><dataStore>" +
//                    "<name>fromSDE</name>" +
//                    "<connectionParameters>" +
//                    "<server>192.168.2.142</server>" +
//                    "<port>5151</port>" +
//                    "<instance>arcse</instance>" +
//                    "<user>sde</user>" +
//                    "<password>yll</password>" +
//                    "<dbtype>arcsde</dbtype>" +
//                    "</connectionParameters></dataStore>";

            String xml = "<?xml version="
                    + "\"1.0\""
                    + " encoding="
                    + "\"UTF-8\""
                    + "?><dataStore>" +
                    "<name>"+dataStoryPara.get("name")+"</name>" +
                    "<connectionParameters>" +
                    "<server>"+dataStoryPara.get("ip")+"</server>" +
                    "<port>"+dataStoryPara.get("port")+"</port>" +
                    "<instance>"+dataStoryPara.get("instance")+"</instance>" +
                    "<user>"+dataStoryPara.get("user")+"</user>" +
                    "<password>"+dataStoryPara.get("passwd")+"</password>" +
                    "<dbtype>"+dataStoryPara.get("dbtype")+"</dbtype>" +
                    "</connectionParameters></dataStore>";
            String transData = xml;
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
            httpclient.getConnectionManager().shutdown();
        }
    }
    public static void main(String[] args) throws Exception {
        HashMap<String,String> dataStoryPara = new HashMap<String,String>();
        dataStoryPara.put("name","fromSDE");
        dataStoryPara.put("ip","192.168.2.142");
        dataStoryPara.put("port","5151");
        dataStoryPara.put("instance","arcse");
        dataStoryPara.put("user","sde");
        dataStoryPara.put("passwd","yll");
        dataStoryPara.put("dbtype","arcsde");

        connectionArcSDE(dataStoryPara);
    }
}