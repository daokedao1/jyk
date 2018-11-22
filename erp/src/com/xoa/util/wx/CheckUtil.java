package com.xoa.util.wx;

import com.alibaba.fastjson.JSONObject;
import com.xoa.model.wxChat.TokenThread;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;

public class CheckUtil {

    public static final String tooken = "w9Y4iY3Y9d3YPRwI9briBBRb93CZYcyB"; //开发者自行定义Tooken

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        //1.定义数组存放tooken，timestamp,nonce
        String[] arr = {tooken, timestamp, nonce};
        //2.对数组进行排序
        Arrays.sort(arr);
        //3.生成字符串
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            sb.append(s);
        }
        //4.sha1加密
        String temp = getSha1(sb.toString());
        //5.将加密后的字符串，与微信传来的加密签名比较，返回结果
        return temp.equals(signature);
    }
    public static JSONObject getSignatureByJsticket(String url){
        JSONObject json = new JSONObject();

        String jsapi_ticket = TokenThread.getJsapiTicket().getTicket();
        String noncestr =  getNonceStr();
        String timestamp =  getTimeStamp();
        String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;

        //4.sha1加密
        json.put("signatureJs",getSha1(sign));
        json.put("appId", TokenThread.appId);
        json.put("nonceStr", noncestr);
        json.put("timestamp", timestamp);
        return json;
    }
    public static String getSha1(String str) {

        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public static String getNonceStr() {
        Random random = new Random();
        return DigestUtils.md5DigestAsHex(String.valueOf(random.nextInt(10000)).getBytes());
    }

    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
