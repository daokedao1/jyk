package com.xoa.model.wxChat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xoa.util.http.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class TokenThread implements Runnable {

    public static String appId = "";

    public static String appSecret= "";

    public static String grant_type = "client_credential";

    public static String tokenurl = "https://api.weixin.qq.com/cgi-bin/token";

    private static AccessToken accessToken = null;

    public static AccessToken getToken(){
        return accessToken;
    }

    @Override
    public void run() {

        while (true){
            try{
                accessToken = this.getAccessToken();
                if(null!=accessToken){
                    System.out.println(accessToken.getAccessToken());
                    Thread.sleep(7000 * 1000); //获取到access_token 休眠7000秒

                }else{
                    Thread.sleep(1000*3); //获取的access_token为空 休眠3秒
                }
            }catch(Exception e){
                System.out.println("发生异常："+e.getMessage());
                e.printStackTrace();
                try{
                    Thread.sleep(1000*10); //发生异常休眠1秒
                }catch (Exception e1){
                    e.printStackTrace();
                }
            }
        }

    }

    private AccessToken getAccessToken(){
        AccessToken token = null;

        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("grant_type", grant_type);
        querys.put("appid", appId);
        querys.put("secret", appSecret);

        try {
            HttpResponse response = HttpUtils.doGet(tokenurl, "", "GET", headers, querys);
            String resData = EntityUtils.toString(response.getEntity());
            JSONObject json = JSON.parseObject(resData);
            token = new AccessToken();
            token.setAccessToken(json.getString("access_token"));
            token.setExpiresin(json.getInteger("expires_in"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
