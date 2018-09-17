package com.xoa.service.product;

import com.xoa.model.product.ProductModel;

import java.util.List;


/**
 * Created by zhuzy on 2017/10/28.
 */

public interface ProductService {

    public ProductModel selectByPrimaryKey(Integer pid);

    public List<ProductModel> selectAll(ProductModel productModel);

    public Boolean insert(ProductModel product);

    public Boolean updateByPrimaryKey(ProductModel productModel);

    public Boolean deleteByPrimaryKey(Integer id);

    public Integer countByExample(ProductModel productModel);

}
