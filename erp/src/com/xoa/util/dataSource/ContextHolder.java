package com.xoa.util.dataSource;

/**
 * 
 * 创建作者:   张勇
 * 创建日期:   2017-4-26 下午5:38:23
 * 类介绍  :    分线程连接数据源
 * 构造参数:   
 *
 */
public class ContextHolder {
//    public static final String mysqlDataSources1="mysqlDataSources";
//    public static final String mysqlDataSources="mysqlDataSources1";
//    public static final String oracleDataSources="oracleDataSources";
	
	

    private static final ThreadLocal<String> context = new ThreadLocal<String>();

    public static void setConsumerType(String consumerType){
        context.set(consumerType);
    }

    public static String getConsumerType(){
        return context.get();
    }

    public static void clearConsumerType(){
        context.remove();
    }
}
