package com.xoa.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.xoa.model.wxChat.TokenThread;
import com.xoa.service.wx.WechatService;
import com.xoa.util.ToJson;
import com.xoa.util.http.HttpUtils;
import com.xoa.model.wxChat.wxMessageutil;
import com.xoa.util.wx.CheckUtil;
import com.xoa.util.wx.WeChatUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Controller
@RequestMapping("/wx")
public class wxController {

    @Resource
    private WechatService wxService;

    @ResponseBody
    @RequestMapping(value ="/check", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public String add(String signature, String timestamp, String nonce,String echostr,HttpServletRequest request, HttpServletResponse response) throws IOException {

                if(CheckUtil.checkSignature(signature, timestamp, nonce)) {
                    //如果校验成功，将得到的随机字符串原路返回
                    System.out.println("签名校验通过 "+echostr);
                    response.getOutputStream().println(echostr);
                  return echostr;
                }
        System.out.println("签名校验失败");
        return"";
    }

    /***
     * post方法用于接收微信服务端消息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String  DoPost(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("系统消息接收");
        Map<String, String> requestMap = null;
        String respMessage = null;
        try {
            requestMap = WeChatUtil.xmlToMap(request);// xml请求解析
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 发送方帐号（open_id）
        String fromUserName = requestMap.get("FromUserName");
        // 公众帐号
        String toUserName = requestMap.get("ToUserName");
        // 消息类型
        String msgType = requestMap.get("MsgType");
        // 消息内容
        String content = requestMap.get("Content");
        System.out.println("FromUserName is:" + fromUserName + ", ToUserName is:" + toUserName + ", MsgType is:" + msgType);
        switch(msgType){
            case wxMessageutil.REQ_MESSAGE_TYPE_TEXT: // 文本消息
                System.out.println("0");
                break;
            case wxMessageutil.REQ_MESSAGE_TYPE_EVENT://事件
                System.out.println("事件");
                break;
            case wxMessageutil.REQ_MESSAGE_TYPE_VOICE://音频
                System.out.println("音频");
                break;
            case wxMessageutil.EVENT_TYPE_UNSUBSCRIBE://取消订阅
                System.out.println("取消订阅");
                break;
            case wxMessageutil.EVENT_TYPE_CLICK: // 自定义菜单点击事件

                break;
            default:
                System.out.println("default");
        }
        return respMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/getTokenByCode", method = RequestMethod.GET)
    public ToJson<wxMessageutil> getTokenByCode (String code,String grantType,HttpServletRequest request,HttpServletResponse response){
        ToJson json = new ToJson(1, null);
            JSONObject wxuserinfo = wxService.getTokenByCode(code,grantType);
            json.setObject(wxuserinfo);
            if(wxuserinfo != null && wxuserinfo.get("errcode") == null){

                json.setFlag(0);
            }
        return json;
    }


    @ResponseBody
    @RequestMapping(value = "/getUserInfoByOpenid", method = RequestMethod.GET)
    public ToJson<wxMessageutil> getUserInfoByOpenid (String access_token,String openid,String lang,HttpServletRequest request,HttpServletResponse response){
        ToJson json = new ToJson(1, null);
        JSONObject wxuserinfo = wxService.getUserInfoByOpenid(access_token,openid,lang);
        json.setObject(wxuserinfo);
        if(wxuserinfo != null && wxuserinfo.get("errcode") == null){
            json.setFlag(0);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getJsApiSignature", method = RequestMethod.GET)
    public ToJson<wxMessageutil> getJsApiSignature (String url,HttpServletRequest request,HttpServletResponse response){
        ToJson json = new ToJson(1, null);

        JSONObject  data = wxService.getJsApiSignature(url);

        json.setObject(data);
        if(data != null){
            json.setFlag(0);
        }
        return json;
    }


}
