package com.xoa.service.doctor;

import com.xoa.model.doctor.Doctor;
import com.xoa.util.ToJson;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;


/**
 * Created by zhuxinyuan on 2018.9.8
 */
public interface DoctorService {

    //获取医生信息列表
    List<Doctor> getDoctor(Doctor doctor);
    //根据id删除医生信息
    Boolean delDoctorById(Integer id);
    //修改医生信息
    Boolean updateDoctor(Doctor doctor);

    Boolean insert(Doctor doctor);

    int count(Doctor doctor);

    Doctor selectByPrimaryKey(Integer id);



}
