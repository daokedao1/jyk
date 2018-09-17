package com.xoa.service.inventory.Impl;

import com.xoa.dao.inventory.InventoryModelMapper;
import com.xoa.model.inventory.InventoryModel;
import com.xoa.service.inventory.InventoryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhuzy on 2017/10/28.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryModelMapper inventoryModelMapper;

    @Override
    public Boolean insert(InventoryModel inventoryModel) {
        int res = inventoryModelMapper.insert(inventoryModel);
        System.out.print(res);
        return res > 0 ? true:false;
    }

    @Override
    public Boolean updateByPrimaryKey(InventoryModel inventoryModel) {
        int res = inventoryModelMapper.updateByPrimaryKey(inventoryModel);
        return res > 0 ? true :false;
    }

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
       int res = inventoryModelMapper.deleteByPrimaryKey(id);
        return res > 0 ?true:false;
    }

    @Override
    public InventoryModel selectByPrimaryKey(Integer pid) {

        return inventoryModelMapper.selectByPrimaryKey(pid);
    }

    @Override
    public List<InventoryModel> selectAll(InventoryModel inventoryModel) {

        if(inventoryModel.page != null && inventoryModel.pageSize != null){
            inventoryModel.beginRow = (inventoryModel.page-1)*inventoryModel.pageSize;
        }
        return  inventoryModelMapper.selectByExample(inventoryModel);
    }

    @Override
    public Integer countByExample(InventoryModel inventoryModel) {
        return inventoryModelMapper.countByExample(inventoryModel);
    }

}
