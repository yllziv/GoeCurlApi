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

/**
 * 实现在Geoserver中 添加工作区
 * curl -v -u admin:geoserver -XPOST -H "Content-type: text/xml" -d "<workspace><name>abcd</name></workspace>" http://localhost:8080/geoserver/rest/workspaces
 * -v 显示get请求全过程解析
 * -u/--user <user[:password]>设置服务器的用户和密码
 * -XPOST post请求
 * -H/--header <header> 指定请求头参数
 * -d/--data <data>   HTTP POST方式传送数据
 */
public class AddWorkSpace {

    public static void main(String[] args) throws Exception {
        DefaultHttpClient httpclient = new  DefaultHttpClient();
        try {
            // curl -u
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 8080),
                    new UsernamePasswordCredentials("admin", "geoserver"));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            HttpPost httpost = new HttpPost("http://localhost:8080/geoserver/rest/workspaces");
            // curl -H
            httpost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            // curl -d
            String transData = "<workspace><name>abcd</name></workspace>";
            httpost.setEntity(new StringEntity(transData));

            // curl -v
            System.out.println("executing request" + httpost.getRequestLine());
            HttpResponse response = httpclient.execute(httpost);
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
}
