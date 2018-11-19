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

    private static JsapiTicket jsapiTicket = null;

    public static JsapiTicket getJsapiTicket(){return  jsapiTicket;}

    @Override
    public void run() {

        while (true){
            try{
                accessToken = this.getAccessToken();
                if(null!=accessToken){
                    jsapiTicket = getJsapiTicketByAccessToken(accessToken.getAccessToken());
                    if(null != jsapiTicket){
                        System.out.println(accessToken.getAccessToken());
                        System.out.println(jsapiTicket.getTicket());
                        Thread.sleep(7000 * 1000); //获取到access_token 休眠7000秒
                    }else{
                        Thread.sleep(1000*3); //获取的access_token为空 休眠3秒
                    }


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
            System.out.println(json);
            token = new AccessToken();
            token.setAccessToken(json.getString("access_token"));
            token.setExpiresin(json.getInteger("expires_in"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
    private JsapiTicket getJsapiTicketByAccessToken(String accesstoken){
        JsapiTicket jsapi_ticket  = null;

        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("access_token", accesstoken);
        querys.put("type", "jsapi");

        try {
            HttpResponse response = HttpUtils.doGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", "", "GET", headers, querys);
            String resData = EntityUtils.toString(response.getEntity());
            JSONObject json = JSON.parseObject(resData);
            System.out.println(json);
            jsapi_ticket = new JsapiTicket();
            jsapi_ticket.setTicket(json.getString("ticket"));
            jsapi_ticket.setExpires_in(json.getInteger("expires_in"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsapi_ticket;
    }
}
