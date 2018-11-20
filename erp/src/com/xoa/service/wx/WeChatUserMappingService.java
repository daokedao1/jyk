package com.xoa.service.wx;


import com.xoa.dao.WeChatUserMappingDao.WeChatUserMappingMapper;
import com.xoa.model.WeChatUserMappingModel.WeChatUserMapping;
import com.xoa.model.wxUser.wxUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WeChatUserMappingService {

    @Resource
    private WeChatUserMappingMapper weChatUserMappingMapper;

    public int insert(WeChatUserMapping weChatUserMapping){
        return weChatUserMappingMapper.insert(weChatUserMapping);
    }

    public int update(WeChatUserMapping weChatUserMapping){
        return weChatUserMappingMapper.updateByPrimaryKey(weChatUserMapping);
    }
    public List<WeChatUserMapping> selectOneByToid(String toId){
        return weChatUserMappingMapper.selectByToid(toId);
    }
    public List<WeChatUserMapping> selectAll (WeChatUserMapping weChatUserMapping){
        return weChatUserMappingMapper.selectByExample(new WeChatUserMapping());
    }
}
