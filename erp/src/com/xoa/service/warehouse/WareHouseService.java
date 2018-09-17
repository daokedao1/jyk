package com.xoa.service.warehouse;




import com.xoa.model.warehouse.WareHouseModel;

import java.util.List;

/**
 * Created by zhuzy on 2017/11/19.
 */
public interface WareHouseService {
    public WareHouseModel selectByPrimaryKey(Integer pid);

    public List<WareHouseModel> selectAll(WareHouseModel WareHouseModel);

    public Boolean insert(WareHouseModel WareHouseModel);

    public Boolean updateByPrimaryKey(WareHouseModel WareHouseModel);

    public Boolean deleteByPrimaryKey(Integer id);

    public Integer countByExample(WareHouseModel WareHouseModel);
}
