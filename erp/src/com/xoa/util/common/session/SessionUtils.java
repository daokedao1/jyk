package com.xoa.util.common.session;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.xoa.util.common.L;
import org.apache.shiro.web.session.HttpServletSession;

import com.xoa.util.common.StringUtils;

/**
 * 
 * @作者 韩东堂
 * @创建日期 2017-4-27 上午11:19:22
 * @类介绍 session工具类
 * @构造参数 无
 * 
 */
public class SessionUtils {

	/**
	 * 
	 * @作者 韩东堂
	 * @创建日期 2017-4-27 下午1:09:33
	 * @方法介绍 session 配置类 Map型
	 * @参数说明 @param session
	 * @参数说明 @param params
	 * @return
	 */
	public static void putSession(HttpSession session,
			Map<String, Object> params) {
		if (session == null)
			throw new SessionException("session is null");
		if (params == null || params.size() == 0)
			return;
		for (Entry<String, Object> entry : params.entrySet()) {
			session.setAttribute(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 
	 * @作者 韩东堂
	 * @创建日期 2017-4-27 下午1:10:19
	 * @方法介绍 session 配置类 Model型
	 * @参数说明 @param session
	 * @参数说明 @param obj
	 * @return
	 */
	public static void putSession(HttpSession session, Object obj) {
		if (session == null)
			throw new SessionException("session is null");
		if (obj == null)
			return;
		try {
			Field[] fields = Class.forName(obj.getClass().getName())
					.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				session.setAttribute(fields[i].getName(), fields[i].get(obj));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SessionException("session is null");
		}
	}

	/**
	 * 
	 * @作者 韩东堂
	 * @创建日期 2017-4-27 下午1:12:47
	 * @方法介绍 session 配置类 单个型
	 * @参数说明 @param session
	 * @参数说明 @param key
	 * @参数说明 @param value
	 * @return
	 */
	public static void putSession(HttpSession session, String key, Object value) {

		if (session == null)
			throw new SessionException("session is null");
		if (StringUtils.checkNull(key))
			throw new SessionException("key is null");
		session.setAttribute(key, value);
	}

	
	/**
	 * 根据单个键值，返回对于的session值
	 * @作者 韩东堂
	 * @创建日期 2017-4-27 下午3:05:00 
	 * @方法介绍 
	 * @参数说明 @param session
	 * @参数说明 @param key
	 * @参数说明 @param clazz
	 * @参数说明 @return
	 * @return
	 */
	public static <T> T getSessionInfo(HttpSession session,String key, Class<T> clazz){
		T ret = null;
		Object value=session.getAttribute(key);
		if(value!=null) ret=clazz.cast(value);
		return ret;
	}
	
	
	
	/**
	 * 
	 * @作者 韩东堂
	 * @创建日期 2017-4-27 下午2:55:28 
	 * @方法介绍 根据set数组获取
	 * @参数说明 @param session
	 * @参数说明 @param keys
	 * @参数说明 @return
	 * @return
	 */
	public static Map<String, Object> getSessionInfo(HttpSession session,
			Set<String> keys) {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = session.getAttribute(key);
			if(value!=null) map.put(key, value);
		}
		return map;
	}

	/**
	 * 
	 * @作者 韩东堂
	 * @创建日期 2017-4-27 下午2:44:18
	 * @方法介绍 根据所给类型返回对于的包装类
	 * @参数说明 @param session
	 * @参数说明 @param clazz
	 * @参数说明 @param deffault
	 * @example getSessionInfo(session,Users.class,new Users);
	 * @参数说明 @return
	 * @return
	 */
	public static <T> T getSessionInfo(HttpSession session, Class<T> clazz,
			T deffault) {
		T ret = null;
		if (deffault == null)
			return null;
		try {
			Field[] fields = Class.forName(clazz.getName()).getDeclaredFields();
			ret = clazz.cast(deffault);//转换当前类型或其子类下的对象
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				Method set_Method = clazz.getMethod("set"
						+ getMethodName(fields[i].getName()),
						fields[i].getType());
				set_Method.setAccessible(true);
				Object value = session.getAttribute(fields[i].getName());
				if (value != null) {
					set_Method.invoke(ret, value);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SessionException("session is null");
		}
		return ret;
	}

	public  static  void cleanUserSession(HttpSession session){
		if(session==null){
			throw new SessionException("session is null");
		}
		//List<String>  stringList= (List<String>) session.getAttributeNames();
		Enumeration<String> enumeration=session.getAttributeNames();
		// 遍历enumeration中的
		while (enumeration.hasMoreElements()) {
             // 获取session键值
			session.removeAttribute(enumeration.nextElement().toString());
		}


	}

	// 把一个字符串的第一个字母大写
	public static String getMethodName(String fildeName) {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

}
