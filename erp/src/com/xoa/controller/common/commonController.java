package com.xoa.controller.common;

import com.xoa.model.product.ProductModel;
import com.xoa.model.product.ProductUnit;
import com.xoa.model.user.UserModel;
import com.xoa.model.warehouse.WareHouseModel;
import com.xoa.service.product.ProductService;
import com.xoa.service.product.ProductUnitService;
import com.xoa.service.user.UserService;
import com.xoa.service.warehouse.WareHouseService;
import com.xoa.util.ToJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhuzy on 2017/10/27.
 */
@Controller
@RequestMapping("/common")
public class commonController {

    @Resource
    private UserService userService;

    @Resource
    private ProductUnitService productUnitService;

    @Resource
    private ProductService productService;

    @Resource
    private WareHouseService wareHouseService;

    @ResponseBody
    @RequestMapping(value ="/getSelectObj", produces = {"application/json;charset=UTF-8"})
    public ToJson<UserModel> getSelectObj(String moudule, HttpServletRequest request, HttpServletResponse response) {
        ToJson<UserModel> json = new ToJson<UserModel>(1, null);
        try {
            HashMap map = new HashMap();
            String paramList [] ;
            if (moudule != null){
                if(moudule.contains(",")){
                    paramList = moudule.split(",");
                }else{
                    paramList = new String []{moudule};
                }
                for (String param : paramList){
                    switch (param){
                        case "productUnit":
                            List<ProductUnit> ProductUnitList = productUnitService.selectAll(new ProductUnit());
                            if(ProductUnitList.size() > 0){
                                map.put("productUnit",ProductUnitList);
                            }
                            break;
                        case "productBrand":

                            //map.put("productBrand","")
                            break;
                        case "product":
                            List<ProductModel> productModels = productService.selectAll(new ProductModel());
                            map.put("product",productModels);
                            break;

                        case "warehouse":
                            List<WareHouseModel> wareHouseModel = wareHouseService.selectAll(new WareHouseModel());
                            map.put("warehouse",wareHouseModel);
                            break;

                        default:

                    }
                }
                json.setObject(map);
                json.setMsg("ok");
                json.setFlag(0);

            }else{
                json.setMsg("参数为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @RequestMapping("/main")
    public String Pageindex(HttpServletRequest request, HttpServletResponse response) {

        return "app/main/index";
    }
}
