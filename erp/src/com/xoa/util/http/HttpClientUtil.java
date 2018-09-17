package com.xoa.util.http;

import java.util.*;
import java.util.Map.Entry;

import com.xoa.util.CodeUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  

import com.xoa.util.common.L;
/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  
    public static String doPost(String url,Map<String,String> map,Map<String,String> header,String charset){
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);
            //设置header
            if(header!=null){
                for(String key:header.keySet()){
                    httpPost.addHeader(key, header.get(key));
                }
            }
            L.w("url is",url ,"and param is " ,map);
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
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
        }catch(Exception ex){  
            ex.printStackTrace(); 
            L.w("response exception",ex);
        }  
        return result;  
    }

    public static  String rongYunHttpDoPost(String url,Map<String,String> map,String charset){
        Map<String,String> header =  new HashMap<>();
        String appSecret = "850X11lShEECw";
        String appKey = "8brlm7uf8zgs3";
        String nonce = (int)(Math.random()*10000)+"";
        String timestamp = new Date().getTime()+"";
        StringBuilder toSign = new StringBuilder(appSecret).append(nonce).append(timestamp);
        String sign = CodeUtil.hexSHA1(toSign.toString());



        header.put("App-Key",appKey);
        header.put("Nonce",nonce);
        header.put("Timestamp",timestamp);
        header.put("Signature",sign);
        return doPost(url,map,header,charset);

    }
}
