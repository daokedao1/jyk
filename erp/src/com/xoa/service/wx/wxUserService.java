package com.xoa.service.wx;

import com.xoa.dao.user.UserMapper;
import com.xoa.dao.wxUser.wxUserMapper;
import com.xoa.model.wxUser.wxUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class wxUserService {

    @Resource
    private wxUserMapper  wxUserMapper;

    public int insert(wxUser wxuser){
        return wxUserMapper.insert(wxuser);
    }

    public int update(wxUser wxuser){
        return wxUserMapper.updateByPrimaryKey(wxuser);
    }

    public wxUser selectByopenId(String openid){
        return wxUserMapper.selectByOpenId(openid);
    }

    public List<wxUser> selectAll (wxUser wxuser){
        return wxUserMapper.selectByExample(new wxUser());
    }
}
