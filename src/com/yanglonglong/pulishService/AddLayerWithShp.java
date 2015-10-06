package com.yanglonglong.pulishService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * ʵ����Geoserver�����ͼ��
 * curl -v -u admin:geoserver -XPUT -H "Content-type: text/plain"
 * -d "file:C:/Program Files (x86)/GeoServer 2.5.4/data_dir/data/shapefiles/whu_data/shexiangtou.shp"
 *  http://localhost:8080/geoserver/rest/workspaces/abcd/datastores/river/external.shp
 */
public class AddLayerWithShp {

    public static void main(String[] args) throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            // curl -u
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 8080),
                    new UsernamePasswordCredentials("admin", "geoserver"));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            // whudataΪ���ݴ洢���ƣ�external.shp����Ҫ�������ļ������ⲿ�ļ�
            HttpPut httput = new HttpPut("http://localhost:8080/geoserver/rest/workspaces/abcd/datastores/whudata/external.shp");
            // curl -H
            httput.setHeader(HttpHeaders.CONTENT_TYPE, "text/plain");

            // curl -d
            String transData = "file:C:/Program Files (x86)/GeoServer 2.7.2/data_dir/data/whu_data/shexiangtou.shp";
            httput.setEntity(new StringEntity(transData));

            // curl -v
            System.out.println("executing request" + httput.getRequestLine());
            HttpResponse response = httpclient.execute(httput);
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