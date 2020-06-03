package lg.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: LG
 * date: 2019-09-18 10:25
 * desc:
 *
 * 模拟浏览区 get post 请求
 */
public class HttpClientUtils {

    public static String doGet(String url) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        int time = 5000;
        RequestConfig config = RequestConfig.custom().setConnectTimeout(time)
                .setConnectionRequestTimeout(time)
                .setSocketTimeout(time)
                .setRedirectsEnabled(true)
                .build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();

                return  EntityUtils.toString(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String doPost(String url,Map<String,Object> mapData) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        int time = 5000;
        RequestConfig config = RequestConfig.custom().setConnectTimeout(time)
                .setConnectionRequestTimeout(time)
                .setSocketTimeout(time)
                .setRedirectsEnabled(true)
                .build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);

      //  httpPost.addHeader("Content-Type", "application/json;charset=UTF-8,Chunked: true");
      //  httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
      //  httpPost.addHeader("Content-Type","text/html; chartset=UTF-8");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        if(mapData != null && mapData.size() != 0){
            for (String key :mapData.keySet()){
                list.add(new BasicNameValuePair(key, mapData.get(key).toString()));
            }
        }

        try {
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
            httpPost.setEntity(uefEntity);

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();

               return  EntityUtils.toString(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
