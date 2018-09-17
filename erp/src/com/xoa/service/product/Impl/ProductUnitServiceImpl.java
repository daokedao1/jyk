package com.xoa.service.product.Impl;

import com.xoa.dao.product.ProductUnitMapper;
import com.xoa.model.product.ProductUnit;
import com.xoa.service.product.ProductUnitService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * Created by zhuzy on 2017/10/28.
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Resource
    private ProductUnitMapper productUnitMapper;

    @Override
    public Boolean insert(ProductUnit productunit) {
        int res = productUnitMapper.insert(productunit);
        System.out.print(res);
        return res > 0 ? true:false;
    }

    @Override
    public Boolean updateByPrimaryKey(ProductUnit productunit) {
        int res = productUnitMapper.updateByPrimaryKey(productunit);
        return res > 0?true:false;
    }

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
       int res = productUnitMapper.deleteByPrimaryKey(id);
        return res > 0?true:false;
    }

    @Override
    public ProductUnit selectByPrimaryKey(Integer pid) {

        return productUnitMapper.selectByPrimaryKey(pid);
    }

    @Override
    public List<ProductUnit> selectAll(ProductUnit productunit) {

        if(productunit.page != null && productunit.pageSize != null){
            productunit.beginRow = (productunit.page-1)*productunit.pageSize;
        }
        return  productUnitMapper.selectByExample(productunit);
    }

    @Override
    public Integer countByExample(ProductUnit productunit) {
        return productUnitMapper.countByExample(productunit);
    }
}
