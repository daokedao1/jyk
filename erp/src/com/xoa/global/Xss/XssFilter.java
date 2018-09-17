package com.xoa.global.Xss;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 创建时间：2017年10月24日 下午1:34:04
 * @author zlf
 * @version 1.0
 * 文件名称：XssFilter.java
 * 类说明：Xss跨脚本攻击过滤器
 */
public class XssFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
   /*     String path = ((HttpServletRequest) request).getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path + "/";
       *//* String sub = basePath.substring(StringUtils.lastIndexOf(basePath, "/"), basePath.length());
        if (sub.equals("/")){
            chain.doFilter(request, response);
            return;
        }*//*
        // HTTP 头设置 Referer过滤
        String referer = ((HttpServletRequest) request).getHeader("Referer"); // REFRESH
        if (referer != null && referer.indexOf(basePath) < 0) {
            ((HttpServletRequest) request).getRequestDispatcher(
                    ((HttpServletRequest) request).getRequestURI()).forward(
                    ((HttpServletRequest) request), response);

        }*/
        //XssHttpServletRequestWraper xssRequest = new XssHttpServletRequestWraper((HttpServletRequest) request);
        chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}