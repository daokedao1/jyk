package com.xoa.service.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xoa.util.ToJson;
import com.xoa.util.http.HttpUtils;
import com.xoa.util.wx.CheckUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.xoa.model.wxChat.TokenThread.*;


@Service
public class WechatService {

    public JSONObject getTokenByCode(String code,String grantType) {
        JSONObject json = null;
        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> querys = new HashMap<String, String>();

        querys.put("appid", appId);
        querys.put("secret", appSecret);
        querys.put("code", code);
        querys.put("grant_type", "authorization_code");
        try{
            HttpResponse response = HttpUtils.doGet("https://api.weixin.qq.com/sns/oauth2/access_token", "", "GET", headers, querys);
            String resData = EntityUtils.toString(response.getEntity());
            json = JSON.parseObject(resData);

        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    public JSONObject getUserInfoByOpenid(String access_token,String openid,String lang) {

        JSONObject json = null;
        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> querys = new HashMap<String, String>();

        querys.put("access_token", access_token);
        querys.put("openid", openid);
        querys.put("lang", lang);
        try{
            HttpResponse response = HttpUtils.doGet("https://api.weixin.qq.com/sns/userinfo", "", "GET", headers, querys);
            String resData = EntityUtils.toString(response.getEntity());
            resData = new String(resData.getBytes("ISO-8859-1"),"UTF-8") ;

            json = JSON.parseObject(resData);

        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    public JSONObject getJsApiSignature(String url){

        return  CheckUtil.getSignatureByJsticket(url);
    }
}
