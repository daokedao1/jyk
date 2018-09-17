package com.xoa.service.product;

import com.xoa.model.product.ProductBrandModel;

import java.util.List;


/**
 * Created by zhuzy on 2017/10/28.
 */

public interface ProductBrandService {

    public ProductBrandModel selectByPrimaryKey(Integer pid);

    public List<ProductBrandModel> selectAll(ProductBrandModel productunit);

    public Boolean insert(ProductBrandModel productunit);

    public Boolean updateByPrimaryKey(ProductBrandModel productunit);

    public Boolean deleteByPrimaryKey(Integer id);

    public Integer countByExample(ProductBrandModel productunit);

}
