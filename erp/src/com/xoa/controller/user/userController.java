package com.xoa.controller.user;

import com.xoa.model.base.Columns;
import com.xoa.model.user.UserModel;
import com.xoa.service.user.UserService;
import com.xoa.util.ToJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by zhuzy on 2017/11/19.
 */

@Controller
@RequestMapping("/user")
public class userController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value ="/add", produces = {"application/json;charset=UTF-8"})
    public ToJson<UserModel> add(UserModel user, HttpServletRequest request, HttpServletResponse response) {
        ToJson<UserModel> json = new ToJson<UserModel>(1, null);
        try {
            Boolean res = userService.insert(user);
            if(res){
                json.setMsg("ok");
                json.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value ="/getOneById", produces = {"application/json;charset=UTF-8"})
    public ToJson<UserModel> selectByPrimaryKey(Integer uid, HttpServletRequest request, HttpServletResponse response) {
        ToJson<UserModel> json = new ToJson<UserModel>(1, null);
        try {
            System.out.println(uid);
            UserModel res = userService.selectByPrimaryKey(uid);
            json.setObject(res);
            json.setMsg("OK ");
            json.setFlag(0);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value ="/getAll", produces = {"application/json;charset=UTF-8"})
    public ToJson<UserModel> selectAll(Integer pagesize, Integer start, Integer page, UserModel user, HttpServletRequest request, HttpServletResponse response) {
        ToJson<UserModel> json = new ToJson<UserModel>(1, null);
        try {
            user.setPagesize(pagesize);
            user.setBeginRow(start);
            List<UserModel> res = userService.selectAll(user);
            Integer count = userService.countByExample(user);
            json.setCount(count);
            json.setObject(res);
            json.setMsg("OK ");
            json.setFlag(0);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value ="/deleteById", produces = {"application/json;charset=UTF-8"})
    public ToJson<UserModel> deleteById(Integer uid, HttpServletRequest request, HttpServletResponse response) {
        ToJson<UserModel> json = new ToJson<UserModel>(1, null);
        try {
            if(userService.deleteByPrimaryKey(uid)){
                json.setMsg("OK ");
                json.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @ResponseBody
    @RequestMapping(value ="/updateById", produces = {"application/json;charset=UTF-8"})
    public ToJson<UserModel> updateById(UserModel user, HttpServletRequest request, HttpServletResponse response) {
        ToJson<UserModel> json = new ToJson<UserModel>(1, null);
        try {
            if(userService.updateByPrimaryKey(user)){
                json.setMsg("OK ");
                json.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @RequestMapping("/index")
    public String Pageindex( HttpServletRequest request, HttpServletResponse response) {

        return "app/user/index";
    }
}
