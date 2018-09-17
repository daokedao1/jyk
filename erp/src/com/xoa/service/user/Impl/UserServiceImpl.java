package com.xoa.service.user.Impl;

import com.xoa.dao.user.UserMapper;
import com.xoa.model.user.UserModel;
import com.xoa.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhuzy on 2017/11/19.
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean insert(UserModel user) {
        int res = userMapper.insert(user);
        System.out.print(res);
        return res > 0 ? true:false;
    }

    @Override
    public Boolean updateByPrimaryKey(UserModel user) {
        int res = userMapper.updateByPrimaryKey(user);
        return res > 0 ? true:false;
    }

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        int res = userMapper.deleteByPrimaryKey(id);
        return res > 0?true:false;
    }

    @Override
    public UserModel selectByPrimaryKey(Integer pid) {

        return userMapper.selectByPrimaryKey(pid);
    }

    @Override
    public UserModel checkPassByUserName(String uname,String password) {
        UserModel user = userMapper.selectByUserName(uname);
        if(user.getPassword().equals(password)){
            return user;
        }
        return new UserModel();
    }

    @Override
    public List<UserModel> selectAll(UserModel user) {
        if(user.beginRow == null){
            if(user.page != null && user.pageSize != null){
                user.beginRow = (user.page-1)*user.pageSize;
            }
        }

        return  userMapper.selectByExample(user);
    }

    @Override
    public Integer countByExample(UserModel user) {

        return userMapper.countByExample(user);
    }
}
