package com.xoa.controller.wx;


import com.xoa.model.WeChatUserMappingModel.WeChatUserMapping;
import com.xoa.model.wxUser.wxUser;
import com.xoa.service.wx.WeChatUserMappingService;
import com.xoa.service.wx.WechatService;
import com.xoa.service.wx.wxUserService;
import com.xoa.util.ToJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wxuser")
public class wxUserController {


    @Resource
    private wxUserService wxUserService;

    @Resource
    private WeChatUserMappingService weChatUserMappingService;

    @ResponseBody
    @RequestMapping(value ="/insert", produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public  ToJson<wxUser> add(wxUser wxUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ToJson json = new ToJson(1, null);
        try {
            int index = wxUserService.insert(wxUser);
            if(index != 0){
                wxUser.setId(index);
                json.setObject(wxUser);
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
    @RequestMapping(value ="/update", produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public  ToJson<wxUser> update(wxUser wxUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ToJson json = new ToJson(1, null);
        if(wxUser.getOpenid() == null ){
            return json;
        }
        try {
            wxUser res = wxUserService.selectByopenId(wxUser.getOpenid());
            if(res != null){
                if(wxUser.getNickname() != null || wxUser.getLocation() != null){
                    int index = wxUserService.update(wxUser);
                    if(index != 0){
                        json.setMsg("ok");
                        json.setFlag(0);
                    }
                }else{
                    json.setMsg("ok");
                    json.setFlag(0);
                }
            }else{
                int index = wxUserService.insert(wxUser);
                if(index != 0){
                    wxUser.setId(index);
                    json.setObject(wxUser);
                    json.setMsg("ok");
                    json.setFlag(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }


    @ResponseBody
    @RequestMapping(value ="/getAll", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public  ToJson<wxUser> getAll(wxUser wxUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ToJson json = new ToJson(1, null);
        try {
            List wxUsersList = wxUserService.selectAll(wxUser);
                json.setObj(wxUsersList);
                json.setMsg("ok");
                json.setFlag(0);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }


    @ResponseBody
    @RequestMapping(value ="/addusermapping", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public  ToJson<wxUser> addusermapping(WeChatUserMapping weChatUserMapping, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ToJson json = new ToJson(1, null);
        try {
            weChatUserMapping.setDate(new Date());
            List list =  weChatUserMappingService.selectOneByToid(weChatUserMapping.getToid());
            if(list.size() == 0){
                int id = weChatUserMappingService.insert(weChatUserMapping);
                if(id > 0){
                    json.setMsg("ok");
                    json.setFlag(0);

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @ResponseBody
    @RequestMapping(value ="/getusermapping", produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public  ToJson<wxUser> getusermapping(WeChatUserMapping weChatUserMapping, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ToJson json = new ToJson(1, null);
        try {
            List list = weChatUserMappingService.selectAll(weChatUserMapping);
            if(list.size() > 0){
                json.setObj(list);
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

}
