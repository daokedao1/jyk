package com.xoa.controller.login;

import com.xoa.model.user.UserModel;
import com.xoa.service.user.UserService;
import com.xoa.util.ToJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzy on 2017/10/27.
 */
@Controller
public class loginController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value ="/login", produces = {"application/json;charset=UTF-8"})
    public ToJson<UserModel> login(String username,String password, HttpServletRequest request, HttpServletResponse response) {
        ToJson<UserModel> json = new ToJson<UserModel>(1, null);
        try {
            if(username != "" && username != null &&  password != null && password != null){
                UserModel user = userService.checkPassByUserName(username,password);

                if(user.getUname() != null){
                    request.getSession().setAttribute("user",user);
                    json.setMsg("ok");
                    json.setFlag(0);
                }
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
    @RequestMapping("/main")
    public String Pageindex(HttpServletRequest request, HttpServletResponse response) {

        return "app/main/index";
    }
}
