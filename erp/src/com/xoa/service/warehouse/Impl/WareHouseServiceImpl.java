package com.xoa.service.warehouse.Impl;

import com.xoa.dao.warehouse.WareHouseModelMapper;
import com.xoa.model.warehouse.WareHouseModel;
import com.xoa.service.warehouse.WareHouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhuzy on 2017/12/10.
 */
@Service
public class WareHouseServiceImpl implements WareHouseService {

    @Resource
    private WareHouseModelMapper wareHouseModelMapper;

    @Override
    public Boolean insert(WareHouseModel wareHouseModel) {
        int res = wareHouseModelMapper.insert(wareHouseModel);
        System.out.print(res);
        return res > 0 ? true:false;
    }

    @Override
    public Boolean updateByPrimaryKey(WareHouseModel wareHouseModel) {
        int res = wareHouseModelMapper.updateByPrimaryKey(wareHouseModel);
        return res > 0?true:false;
    }

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        int res = wareHouseModelMapper.deleteByPrimaryKey(id);
        return res > 0?true:false;
    }

    @Override
    public WareHouseModel selectByPrimaryKey(Integer pid) {

        return wareHouseModelMapper.selectByPrimaryKey(pid);
    }

    @Override
    public List<WareHouseModel> selectAll(WareHouseModel wareHouseModel) {

        if(wareHouseModel.page != null && wareHouseModel.pageSize != null){
            wareHouseModel.beginRow = (wareHouseModel.page-1)*wareHouseModel.pageSize;
        }
        return  wareHouseModelMapper.selectByExample(wareHouseModel);
    }

    @Override
    public Integer countByExample(WareHouseModel wareHouseModel)
    {
        return wareHouseModelMapper.countByExample(wareHouseModel);
    }
}
