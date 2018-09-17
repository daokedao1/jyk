package com.xoa.service.product;

import com.xoa.model.product.ProductUnit;
import java.util.List;


/**
 * Created by zhuzy on 2017/10/28.
 */

public interface ProductUnitService {

    public ProductUnit selectByPrimaryKey(Integer pid);

    public List<ProductUnit> selectAll(ProductUnit productunit);

    public Boolean insert(ProductUnit productunit);

    public Boolean updateByPrimaryKey(ProductUnit productunit);

    public Boolean deleteByPrimaryKey(Integer id);

    public Integer countByExample(ProductUnit productunit);

}
