package com.xoa.global.proxy.TriggerUtils;

import com.xoa.global.proxy.ProxyHandle;
import com.xoa.global.proxy.SpringContextUtil;
import com.xoa.global.proxy.TriggerSubject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pfl on 2017/7/29.
 */
public class Trigger {
    public static  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    public static void run(final String clazzName,final String company,final Object ...agrs){
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Class clazz = Class.forName(clazzName);
                    Constructor cons = clazz.getDeclaredConstructor();
//                    String conf = "config\\spring\\spring-mvc.xml";
                    ApplicationContext ac = SpringContextUtil.getApplicationContext();
                    TriggerSubject a=(TriggerSubject) Proxy.newProxyInstance(TriggerSubject.class.getClassLoader(),
                            new Class[]{TriggerSubject.class},
                            new ProxyHandle(ac.getBean(clazz)));
                    a.doRun(company,agrs);
                }catch (Exception e){
                    e.printStackTrace();
                }
          }
        });
    }
}
