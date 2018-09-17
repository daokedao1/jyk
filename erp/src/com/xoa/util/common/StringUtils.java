package com.xoa.util.common;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @作者 韩东堂
 * @创建日期 2017-4-24 下午4:01:18
 * @类介绍 字符串常用工具类
 * @构造参数 无
 * 
 */
public class StringUtils {
    
	/**
	 * 
	 * @作者 韩东堂
	 * @创建日期 2017-4-24 下午5:36:35 
	 * @方法介绍  检查元素是否为空
	 * @参数说明 @param callBack 外部比较器，通用于所有类型
	 * @参数说明 @param args 参数，前一位是待比较信息，后一位是为空时的错误信息（必须是成对出现）
	 * @参数说明 @return 返回null证明类型安全，不为null有字段为空
	 * @return
	 */
	public static String checkNullUtils(CheckCallBack callBack, Object... args) {
		if (args == null || args.length == 0) {
			L.e("be sure you param is not null");
			throw new RuntimeException("be sure you param is not null");
		}

		if (args.length % 2 != 0) {
			L.e("params must be pair");
			throw new RuntimeException("params must be pair");
		}

		for (int i = 0; i < args.length - 1; i += 2) {
			if (callBack.isNull(args)) {
				return args[i + 1].toString();
			}
			;
			if (args[i] == null) {
				return args[i + 1].toString();
			}
		}
		return null;
	}
	 
	public static Boolean checkNull(String str){
		if(str==null) return true;
		if("".equals(str.trim())||str.trim().length()==0) return true;
	    return false;
	}
	/**
	 * 下划线转驼峰法
	 * @param line 源字符串
	 * @param smallCamel 大小驼峰,是否为小驼峰
	 * @return 转换后的字符串
	 */
	public static String underline2Camel(String line,boolean smallCamel){
		if(line==null||"".equals(line)){
			return "";
		}
		StringBuffer sb=new StringBuffer();
		Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher=pattern.matcher(line);
		while(matcher.find()){
			String word=matcher.group();
			sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
			int index=word.lastIndexOf('_');
			if(index>0){
				sb.append(word.substring(1, index).toLowerCase());
			}else{
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}
	/**
	 * 驼峰法转下划线
	 * @param line 源字符串
	 * @return 转换后的字符串
	 */
	public static String camel2Underline(String line){
		if(line==null||"".equals(line)){
			return "";
		}
		line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
		StringBuffer sb=new StringBuffer();
		Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher=pattern.matcher(line);
		while(matcher.find()){
			String word=matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end()==line.length()?"":"_");
		}
		return sb.toString();
	}
 
}
