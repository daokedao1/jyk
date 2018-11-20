package com.xoa.dao.WeChatUserMappingDao;

import com.xoa.model.WeChatUserMappingModel.WeChatUserMapping;
import com.xoa.model.WeChatUserMappingModel.WeChatUserMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeChatUserMappingMapper {

    int insert(WeChatUserMapping weChatUserMapping);

    int updateByPrimaryKey(WeChatUserMapping weChatUserMapping);

    List<WeChatUserMapping> selectByExample(WeChatUserMapping weChatUserMapping);

    List<WeChatUserMapping> selectByToid(String toId);
}