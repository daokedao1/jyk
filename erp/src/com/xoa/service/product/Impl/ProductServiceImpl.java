package com.xoa.service.product.Impl;

import com.xoa.dao.product.ProductModelMapper;
import com.xoa.model.product.ProductModel;
import com.xoa.service.product.ProductService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhuzy on 2017/10/28.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductModelMapper ProductModelMapper;

    @Override
    public Boolean insert(ProductModel productModel) {
        int res = ProductModelMapper.insert(productModel);
        System.out.print(res);
        return res > 0 ? true:false;
    }

    @Override
    public Boolean updateByPrimaryKey(ProductModel ProductModel) {
        int res = ProductModelMapper.updateByPrimaryKey(ProductModel);
        return res > 0?true:false;
    }

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
       int res = ProductModelMapper.deleteByPrimaryKey(id);
        return res > 0?true:false;
    }

    @Override
    public ProductModel selectByPrimaryKey(Integer pid) {

        return ProductModelMapper.selectByPrimaryKey(pid);
    }

    @Override
    public List<ProductModel> selectAll(ProductModel ProductModel) {

        if(ProductModel.page != null && ProductModel.pageSize != null){
            ProductModel.beginRow = (ProductModel.page-1)*ProductModel.pageSize;
        }
        return  ProductModelMapper.selectByExample(ProductModel);
    }

    @Override
    public Integer countByExample(ProductModel ProductModel) {
        return ProductModelMapper.countByExample(ProductModel);
    }

}
