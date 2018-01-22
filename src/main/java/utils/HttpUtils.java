package utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;
import java.util.logging.Logger;


/**
 * http请求工具类
 * Created by wqp on 2018/1/15.
 */
public class HttpUtils {

    private static final Logger logger = Logger.getLogger(String.valueOf(HttpUtils.class));

    /**
     * 发送get请求
     * @param url 请求地址
     * @return 响应字符串
     */
    public static JSONObject doGet(String url) {
        String result = null;
        HttpGet request = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            logger.info("get请求出错");
            e.printStackTrace();
        }
        JSONObject jsonData = JSONObject.fromObject(result);
        return jsonData;
}

    /**
     *  发送post请求
     * @param url 请求地址
     * @param map 参数
     * @param charset 编码
     * @return 响应字符串
     */
    public static JSONObject doPost(String url, Map<String,String> map, String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
            logger.info("postUrl:"+url);
        }catch(Exception ex){
            logger.info("post请求出错");
            ex.printStackTrace();
        }
        JSONObject jsonData = JSONObject.fromObject(result);
        return jsonData;
    }

}