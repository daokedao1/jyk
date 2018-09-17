package com.xoa.global.intercptor;


import com.xoa.util.DateFormat;
import com.xoa.util.common.L;

import com.xoa.util.common.MobileCheck;
import com.xoa.util.common.StringUtils;
import com.xoa.util.common.session.SessionUtils;
import com.xoa.util.dataSource.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by 王曰岐 on 2017/5/19.
 */
@Component
public class CommonInterceptor implements HandlerInterceptor {


    @Autowired
    CommonFunctionMemory commonFunctionMemory;
//    @Autowired
//    SystemInfoServiceImpl systemInfoService;
//
//    @Resource
//    private UsersMapper usersMapper;

    private List<String> ignoreUrls;

    private String loginUrl;

    private String welcomUrl;

    private String redirectUrl;

    private String mainUrl;

    public String getMainUrl() {
        return mainUrl;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getWelcomUrl() {
        return welcomUrl;
    }

    public void setWelcomUrl(String welcomUrl) {
        this.welcomUrl = welcomUrl;
    }

    public List<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
//        L.s(httpServletRequest.getRequestURL());
//        String visitUrl= httpServletRequest.getRequestURI();
//        long startTime = System.currentTimeMillis();
//        httpServletRequest.setAttribute("startTime", startTime);
//      if(handler instanceof HandlerMethod ) {
//        HandlerMethod h = (HandlerMethod) handler;
//        CommonRequestModel model = new CommonRequestModel();
//        model.setUrl(visitUrl);
//        model.setVisitTime(DateFormat.getStrDate(new Date()));
//        model.setController(h.getBean().getClass().getName());
//        model.setMethod(h.getMethod().getName());
////        model.setParamsStr(getParamString(httpServletRequest.getParameterMap()));
////        model.setParams(httpServletRequest.getParameterMap());
//        L.s(model);
//       }
//       Map<String,String> endLecDate= systemInfoService.getEndLecDate(httpServletRequest);
//       String  useEndDate=  endLecDate.get("useEndDate");
//       String  endLecDateStr=   endLecDate.get("endLecDateStr");
//       L.s(useEndDate,"0=||===========>",endLecDateStr);
//        Cookie[] cookies=  httpServletRequest.getCookies();
//       if(checkTimeOut(useEndDate,endLecDateStr)){
//           if(MobileCheck.isMobileDevice(httpServletRequest.getHeader("user-agent"))){
//               httpServletResponse.sendError(403,httpServletRequest.getContextPath() + welcomUrl);
//           }else{
//               httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+redirectUrl);
//           }
//           return false;
//       }
//        CommonSessionContext myContext= CommonSessionContext.getInstance();
//        L.s(loginUrl,"loginUrlloginUrl",visitUrl);
//        if(visitUrl.equals(welcomUrl)){
//            ContextHolder.setConsumerType("");
//            String cookeSessionId="";
//            if(cookies!=null){
//                for(Cookie cookie:cookies){
//                    if(cookie.getName().contains("JSESSIONID1")){
//                        String sessionId=cookie.getValue();
//                        commonFunctionMemory.putReflectSession(sessionId,httpServletRequest.getSession().getId());
//                        HttpSession  session = myContext.getSession(commonFunctionMemory.getSessionIdByJessionId(sessionId));
//                        if(session!=null){
//                            HttpSession sessionNow=   httpServletRequest.getSession();
//                            ContextHolder.setConsumerType("xoa" + (String)session.getAttribute(
//                                    "loginDateSouse"));
//                           Users user=  SessionUtils.getSessionInfo(session, Users.class,new Users());
//                            String  functionIdStr=  SessionUtils.getSessionInfo(session, "functionIdStr",String.class);
//                            SessionUtils.putSession(sessionNow,user);
//                            SessionUtils.putSession(sessionNow, "functionIdStr",functionIdStr);
//                            SessionUtils.putSession(sessionNow,"loginDateSouse",(String)session.getAttribute(
//                                    "loginDateSouse"));
//                            if(user!=null&&user.getUserId()!=null){
//                                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+mainUrl);
//                            }
//                        }
//                        L.s(sessionId);
//                    }
//                }
//
//            }
//            return true;
//        }
//        if(visitUrl.equals(redirectUrl)){
//            ContextHolder.setConsumerType("");
//            return true;
//        }
//        for (String url : ignoreUrls) {
//            if (url.equals(visitUrl)) {
//                return true;
//            }
//        }
//
//        if(visitUrl.equals(loginUrl)){
//            Map<String,String[]> parameterMap=  httpServletRequest.getParameterMap();
//            String userName ="";
//            if(parameterMap==null||parameterMap.get("username")==null){
//
//            }else {
//                if(!MobileCheck.isMobileDevice(httpServletRequest.getHeader("user-agent"))){
//                        String[] value = parameterMap.get("username");
//
//                        if(value != null && value.length == 1){
//                            userName =  value[0];
//                          String sessionId =  commonFunctionMemory.getSession(userName);
//                          String cookeSessionId="";
//                            if(cookies!=null){
//                                for(Cookie cookie:cookies){
//                                    if(cookie.getName().contains("JSESSIONID1")){
//                                        cookeSessionId=cookie.getValue();
//                                    }
//                                }
//                            }
//                          if(StringUtils.checkNull(sessionId)){
//                              //TODO 没有sessionId
//                              commonFunctionMemory.putSession(userName,cookeSessionId);
//                              commonFunctionMemory.putReflectSession(cookeSessionId,httpServletRequest.getSession().getId());
//                          }else{
//                              //TODO 有sessionID
//                              if(!sessionId.equals(cookeSessionId)){
//                                  commonFunctionMemory.putSession(userName,cookeSessionId);
//                                  HttpSession session  = myContext.getSession(commonFunctionMemory.getSessionIdByJessionId(sessionId));
//                                 myContext.DelSession(session);
//
//                              }else{
//                                  commonFunctionMemory.putSession(userName,cookeSessionId
//                                  );
//                              }
//                              Set<String> userSet =  commonFunctionMemory.getUserName(cookeSessionId);
//                              for(String s:userSet){
//                                  if(!s.equals(userName)){
//                                      commonFunctionMemory.putSession(s,"");
//                                  }
//                              }
//                          }
//                        }
//                    }
//
//                }
//
//
//
//            String loginId ="default";
//               /*  httpServletRequest.getSession().invalidate();//让SESSION失效
//            SessionUtils.cleanUserSession(httpServletRequest.getSession());*/
//            Map<String,String[]> param=  httpServletRequest.getParameterMap();
//            if(param==null||param.get("loginId")==null){
////                throw new InterceptorException("请配置数据源信息 ：param: loginId=?");
//            }else{
//                String[] value = param.get("loginId");
//                if(value != null && value.length == 1){
//                    loginId =  value[0];
//                }else{
//                    loginId=Arrays.toString(value);
//                }
//            }
//            String dbsource =  "xoa" + loginId;
//            L.w("数据库切换到：",dbsource);
//            ContextHolder.setConsumerType(dbsource);
//            return true;
//        }
//
//       String testId= httpServletRequest.getHeader("JSESSIONID1");
//        L.s("0=||================>testId");
//        L.s(testId);
//
//        L.s("0=||================>sessionTest");
//        Users   user=null;
//        String functionIdStr=null;
//        if(cookies!=null){
//            for(Cookie cookie:cookies){
//                L.s(cookie);
//
//                HttpSession session=null;
//                if(MobileCheck.isMobileDevice(httpServletRequest.getHeader("user-agent"))){
//                    if(cookie.getName().contains("JSESSIONID")){
//                        String sessionId=cookie.getValue();
//                        commonFunctionMemory.putReflectSession(sessionId,httpServletRequest.getSession().getId());
//                        L.s(sessionId);
//                        session  = myContext.getSession(commonFunctionMemory.getSessionIdByJessionId(sessionId));
//                    }
//                }else{
//                    if(cookie.getName().contains("JSESSIONID1")){
//                        String sessionId=cookie.getValue();
//                        commonFunctionMemory.putReflectSession(sessionId,httpServletRequest.getSession().getId());
//                        session = myContext.getSession(commonFunctionMemory.getSessionIdByJessionId(sessionId));
//                        L.s(sessionId);
//                    }
//                }
//                L.w(session);
//                if(session!=null){
//                    HttpSession sessionNow=   httpServletRequest.getSession();
//                    ContextHolder.setConsumerType("xoa" + (String)session.getAttribute(
//                            "loginDateSouse"));
//                    user=  SessionUtils.getSessionInfo(session, Users.class,new Users());
//                    functionIdStr=  SessionUtils.getSessionInfo(session, "functionIdStr",String.class);
//                    SessionUtils.putSession(sessionNow,user);
//                    SessionUtils.putSession(sessionNow, "functionIdStr",functionIdStr);
//                    SessionUtils.putSession(sessionNow,"loginDateSouse",(String)session.getAttribute(
//                            "loginDateSouse"));
//                }
//
//            }
//        }
//         if(user==null||user.getUid()==null){
//         }else{
//             //模块验证
//            if(functionIdStr==null){
//            }else{
//                L.s("0=||--===============>",functionIdStr);
//                List<SysFunction> noauthUrl=new ArrayList<SysFunction>();
//                List<SysFunction> functions= commonFunctionMemory.getFunctionIds();
//                for(SysFunction function:functions){
//                    if(!functionIdStr.contains(String.valueOf(function.getfId())+",")){
//                        noauthUrl.add(function);
//                    }
//                }
//                for(SysFunction url:noauthUrl){
//                    L.s(url.getUrl());
//                    String eqUrl= commonFunctionMemory.getReflectMaps().get(url.getUrl());
//                    L.s(eqUrl);
//                    if(!StringUtils.checkNull(eqUrl)){
//                        if(visitUrl.contains(eqUrl)){
//                            if(MobileCheck.isMobileDevice(httpServletRequest.getHeader("user-agent"))){
//                                httpServletResponse.sendError(403,httpServletRequest.getContextPath() + welcomUrl);
//                            }else{
//                                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+redirectUrl);
//                            }
//                            return false;
//                        }
//                    }
//                }
//                return true;
//            }
//         }
//        //4、非法请求 即这些请求需要登录后才能访问
//        //重定向到登录页面
//       if(MobileCheck.isMobileDevice(httpServletRequest.getHeader("user-agent"))){
//           httpServletResponse.sendError(403,httpServletRequest.getContextPath() + welcomUrl);
//       }else{
//           httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+redirectUrl);
//       }
        return true;
    }

    private boolean checkTimeOut(String useEndDate, String endLecDateStr) {
        try{
            Date now =new Date();
            if(StringUtils.checkNull(endLecDateStr)){
                return true;
            }else{
                Date timeOut= DateFormat.DateToStr(endLecDateStr);
                if(timeOut.getTime()<now.getTime()){
                    return true;
                }
            }
            if(StringUtils.checkNull(useEndDate)){
                return true;
            }else if(useEndDate.equals("0000-00-00")){
                return false;
            }else{
                Date timeOut= DateFormat.DateToStr(useEndDate);
                if(timeOut.getTime()<now.getTime()){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
       return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

          private String getParamString(Map<String, String[]> map) {
            StringBuilder sb = new StringBuilder();
              for(Map.Entry<String,String[]> e:map.entrySet()){
                     sb.append(e.getKey()).append("=");
                       String[] value = e.getValue();
                        if(value != null && value.length == 1){
                            sb.append(value[0]).append("\t");
                        }else{
                          sb.append(Arrays.toString(value)).append("\t");
                       }
                     }
                  return sb.toString();
       }
}
