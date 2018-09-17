package com.xoa.service.product.Impl;

import com.xoa.dao.product.ProductBrandMapper;
import com.xoa.model.product.ProductBrandModel;
import com.xoa.service.product.ProductBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhuzy on 2017/12/1.
 */
@Service
public class ProductBrandServiceImpl implements ProductBrandService {
    @Resource
    private ProductBrandMapper productBrandMapper;

    @Override
    public Boolean insert(ProductBrandModel productBrandModel) {
        int res = productBrandMapper.insert(productBrandModel);
        System.out.print(res);
        return res > 0 ? true:false;
    }

    @Override
    public Boolean updateByPrimaryKey(ProductBrandModel productBrandModel) {
        int res = productBrandMapper.updateByPrimaryKey(productBrandModel);
        return res > 0?true:false;
    }

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        int res = productBrandMapper.deleteByPrimaryKey(id);
        return res > 0?true:false;
    }

    @Override
    public ProductBrandModel selectByPrimaryKey(Integer pid) {

        return productBrandMapper.selectByPrimaryKey(pid);
    }

    @Override
    public List<ProductBrandModel> selectAll(ProductBrandModel ProductModel) {

        if(ProductModel.page != null && ProductModel.pageSize != null){
            ProductModel.beginRow = (ProductModel.page-1)*ProductModel.pageSize;
        }
        return  productBrandMapper.selectByExample(ProductModel);
    }

    @Override
    public Integer countByExample(ProductBrandModel productBrandModel)
    {
        return productBrandMapper.countByExample(productBrandModel);
    }

}
