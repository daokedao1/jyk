package com.xoa.controller.doctor;


import com.xoa.model.doctor.Doctor;
import com.xoa.service.doctor.DoctorService;
import com.xoa.util.ToJson;
import com.xoa.util.dataSource.ContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doctor")
public class DoctorController {


    @Resource
    private DoctorService doctorService;

    @RequestMapping("/search")
    public String search(HttpServletRequest request, HttpServletResponse response) {
        return "app/doctor/search";
    }

    @RequestMapping("/searching")
    public String searching(HttpServletRequest request, HttpServletResponse response) {
        return "app/doctor/searching";
    }

    /**
     * 创建作者:   zhuxinyuan
     * 创建日期:   2018.9.8
     * 方法介绍:  查询医生信息
     * @param doctor
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDoctor")
    public ToJson<Doctor> getDoctor(HttpServletResponse response,HttpServletRequest request, Doctor doctor){
        ToJson<Doctor> toJson = new ToJson<Doctor>();
        try{

            //查询全部医生信息
            List<Doctor> list = doctorService.getDoctor(doctor);
            if (list !=null && list.size()>0) {
                toJson.setObj(list);
                //返回总条数
                toJson.setTotleNum(doctorService.count(doctor));
                toJson.setMsg("ok");
            }else {
                toJson.setMsg("数据为空");
            }
            toJson.setFlag(0);
        }catch (Exception e){
            toJson.setFlag(1);
            toJson.setMsg(e.toString());
            e.printStackTrace();
        }
        return toJson;
    }
    /**
     * 创建作者:   zhuxinyuan
     * 创建日期:   2018.9.8
     * 方法介绍:   根据id删除医生信息
     * @param id
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/delDoctorById")
    public ToJson<Doctor> delDoctorById(HttpServletRequest request,HttpServletResponse httpServletResponse,Integer id){
        ToJson<Doctor> toJson = new ToJson<>();
        try {
            if(doctorService.delDoctorById(id)){
                toJson.setMsg("OK ");
                toJson.setFlag(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            toJson.setMsg(e.getMessage());
            toJson.setFlag(1);
        }
        return toJson;
    }

    /**
     * 创建作者:   zhuxinyuan
     * 创建日期:   2018.9.8
     * 方法介绍:   修改学校信息
     * @param doctor
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateDoctor")
    public ToJson<Doctor> updateDoctor(HttpServletRequest request,HttpServletResponse servletResponse, Doctor doctor){
        ToJson<Doctor> toJson = new ToJson<Doctor>(1, null);
        try {
            if(doctorService.updateDoctor(doctor)){
                toJson.setMsg("OK ");
                toJson.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            toJson.setMsg(e.getMessage());
            toJson.setFlag(1);
        }
        return toJson;
    }


    /**
     * 创建作者:   zhuxinyuan
     * 创建日期:   2018.9.8
     * 方法介绍:   add
     * @param doctor
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", produces = {"application/json;charset=UTF-8"})
    public ToJson<Doctor> add(Doctor doctor, HttpServletRequest request, HttpServletResponse response) {
        ToJson<Doctor> json = new ToJson<Doctor>(1, null);
        try {
            Boolean res = doctorService.insert(doctor);
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
    /**
     * 创建作者:   zhuxinyuan
     * 创建日期:   2018.9.8
     * 方法介绍:   根据id获取详情
     * @param id
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/getOneById", produces = {"application/json;charset=UTF-8"})
    public ToJson<Doctor> selectByPrimaryKey(Integer id, HttpServletRequest request, HttpServletResponse response) {
        ToJson<Doctor> json = new ToJson<Doctor>(1, null);
        try {
            Doctor res = doctorService.selectByPrimaryKey(id);
            if(res != null){
                json.setObject(res);
                json.setMsg("OK");
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
