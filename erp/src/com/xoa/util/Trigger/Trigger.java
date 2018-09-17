package com.xoa.util.Trigger;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzy on 2017/7/11.
 */
public class Trigger {
    public static Map plugin;
    public Trigger(String data){
        System.out.print(data);
        plugin = new HashMap();
    }
    public void registePlugin(String PluginName){
        plugin.put(PluginName,"");
    }

    public static void main(String [] args){
        Trigger tt = new Trigger("222222222222222");

    }

}
