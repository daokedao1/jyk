package com.xoa.controller.im;

import com.alibaba.fastjson.JSON;
import com.xoa.model.user.UserModel;
import com.xoa.service.user.UserService;
import com.xoa.util.ToJson;
import com.xoa.util.http.HttpClientUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.xoa.util.checkMysql.password;

/**
 * Created by zhuzy on 2017/10/27.
 */
@Controller
@RequestMapping("/im")
public class imController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value ="/getToken", produces = {"application/json;charset=UTF-8"})
    public ToJson<UserModel> login( HttpServletRequest request, HttpServletResponse response) {
        ToJson<UserModel> json = new ToJson<UserModel>(1, null);
        try {
            UserModel user = (UserModel) request.getSession().getAttribute("user");
            if(user != null){

                String url = "http://api.cn.ronghub.com/user/getToken.json";
//
                Map<String, String> querys = new HashMap<String, String>();
                querys.put("userId", user.getUid().toString());
                querys.put("name", user.getUname());
                querys.put("portraitUri", "");
                String res = HttpClientUtil.rongYunHttpDoPost(url,querys,"utf-8");
                    json.setObject((JSON.parseObject(res)));
                    json.setMsg("ok");
                    json.setFlag(0);

            }else{
                json.setMsg("参数为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }

}
