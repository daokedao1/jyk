package com.xoa.service.inventory;

import com.xoa.model.inventory.InventoryModel;

import java.util.List;


/**
 * Created by zhuzy on 2017/10/28.
 */

public interface InventoryService {

    public InventoryModel selectByPrimaryKey(Integer pid);

    public List<InventoryModel> selectAll(InventoryModel inventoryModel);

    public Boolean insert(InventoryModel inventoryModel);

    public Boolean updateByPrimaryKey(InventoryModel inventoryModel);

    public Boolean deleteByPrimaryKey(Integer id);

    public Integer countByExample(InventoryModel inventoryModel);

}
