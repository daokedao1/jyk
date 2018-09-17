package com.xoa.service.doctor.impl;

import com.xoa.dao.doctor.DoctorMapper;
import com.xoa.model.doctor.Doctor;
import com.xoa.service.doctor.DoctorService;
import com.xoa.util.ExcelUtil;
import com.xoa.util.ToJson;
import com.xoa.util.common.log.FileUtils;
import com.xoa.util.page.PageParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DoctorServiceImpl implements DoctorService{



    @Autowired
    private DoctorMapper doctorMapper;

    /**
     * 创建作者:   zhuxinyuan
     * 创建日期:   2018.9.8
     * 方法介绍:  查询医生列表
     * @param doctor
     * @return
     */
    @Override
    public List<Doctor> getDoctor(Doctor doctor) {
        if(doctor.page != null && doctor.pageSize != null){
            doctor.beginRow = (doctor.page-1)*doctor.pageSize;
        }
        List<Doctor> list = doctorMapper.getDoctor(doctor);
        return list;
    }

    /**
     * 创建作者:   zhuxinyuan
     * 创建日期:   2018.9.8
     * 方法介绍:   根据id删除医生详细信息
     * @param id
     * @return
     */
    @Override
    public Boolean delDoctorById(Integer id) {
        int res = doctorMapper.delDoctorById(id);
        return res > 0?true:false;
    }

    /**
     * 创建作者:   zhuxinyuan
     * 创建日期:   2018.9.8
     * 方法介绍:   修改医生信息
     * @param doctor
     * @return
     */
    @Override
    public Boolean updateDoctor(Doctor doctor){
        int res = doctorMapper.updateDoctor(doctor);
        return res>0?true:false;
    }
    //查询总数
    @Override
    public int count(Doctor doctor) {
        return doctorMapper.countNum(doctor);
    }
    //插入数据
    @Override
    public Boolean insert(Doctor doctor) {
        int res = doctorMapper.insert(doctor);
        return res > 0 ? true:false;
    }

    //根据id查询详情
    @Override
    public Doctor selectByPrimaryKey(Integer id) {
        return doctorMapper.selectByPrimaryKey(id);
    }


}
