package com.xoa.dao.doctor;

import com.xoa.model.doctor.Doctor;
import java.util.List;

public interface DoctorMapper {

    //获取医生信息
    List<Doctor> getDoctor(Doctor doctor);
    //返回总条数
    Integer countNum(Doctor doctor);
    //删除医生信息
    int delDoctorById(Integer id);
    //修改医生接口
    int updateDoctor(Doctor doctor);

    int insert(Doctor doctor);

    Doctor selectByPrimaryKey(Integer id);

}