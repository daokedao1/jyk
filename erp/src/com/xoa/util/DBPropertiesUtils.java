package com.xoa.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @创建作者: 高亚峰
 * @创建日期: 2017/7/18 17:06
 * @类介绍: 获取数据库配置文件信息。
 * @构造参数:
 **/
public class DBPropertiesUtils {


    /**
     * @创建作者: 高亚峰
     * @创建日期: 2017/7/18 17:06
     * @函数介绍: 获取数据库配置信息，连接ip,用户名，密码
     * @参数说明: request
     * @return: map，三个key是：password，username，ip
     **/
    public static Map<String, String> getDbMsg(HttpServletRequest request) {
        //用户选的是哪个库
        String db =
                (String) request.getSession().getAttribute("loginDateSouse");
        String ip = "";
        String username = "";
        String password = "";
        ResourceBundle rb =  ResourceBundle.getBundle("jdbc-sql");
        String string = rb.getString("url" + db);
        String[] urlArr = string.split(":");
        String url = urlArr[2];
        url = url.substring(2, url.length());
        ip = url;
         username = rb.getString("uname" + db);
         password = rb.getString("password" + db);
        Map<String,String> map =new HashMap<String,String>();
        map.put("ip", ip);
        map.put("username", username);
        map.put("password", password);
    return map;
    }


}



