package com.xoa.service.user;


import com.xoa.model.user.UserModel;

import java.util.List;

/**
 * Created by zhuzy on 2017/11/19.
 */
public interface UserService {
    public UserModel selectByPrimaryKey(Integer pid);

    public UserModel checkPassByUserName(String uname,String password);

    public List<UserModel> selectAll(UserModel UserModel);

    public Boolean insert(UserModel UserModel);

    public Boolean updateByPrimaryKey(UserModel UserModel);

    public Boolean deleteByPrimaryKey(Integer id);

    public Integer countByExample(UserModel UserModel);
}
