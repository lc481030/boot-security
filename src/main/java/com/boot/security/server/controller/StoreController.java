package com.boot.security.server.controller;

import com.boot.security.server.dao.ProductDao;
import com.boot.security.server.dao.ProductTypeDao;
import com.boot.security.server.model.Product;
import com.boot.security.server.model.ProductType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class StoreController {

    @Resource
    private ProductDao productDao;

    @Resource
    private ProductTypeDao productTypeDao;


    @RequestMapping("store.html")
    public ModelAndView pcIndex() {
        ModelAndView view = new ModelAndView();
        //查询四个分类中得显示得子项目

        List<ProductType> productTypes = productTypeDao.getByShow();

        List<ProductType> productTypes1 = productTypes.stream().filter(productType -> productType.getProductMode().equals("1")).collect(Collectors.toList());
        List<ProductType> productTypes2 = productTypes.stream().filter(productType -> productType.getProductMode().equals("2")).collect(Collectors.toList());
        List<ProductType> productTypes3 = productTypes.stream().filter(productType -> productType.getProductMode().equals("3")).collect(Collectors.toList());
        List<ProductType> productTypes4 = productTypes.stream().filter(productType -> productType.getProductMode().equals("4")).collect(Collectors.toList());
        view.addObject("productTypes1",productTypes1);
        view.addObject("productTypes2",productTypes2);
        view.addObject("productTypes3",productTypes3);
        view.addObject("productTypes4",productTypes4);

        List<Product> productList1 = new ArrayList<>();
        if (productTypes1 !=null && productTypes1.size()>0){
            productList1 = productDao.getByProductTypeId(productTypes1.get(0).getId());
            productList1.forEach(product -> {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date= df.parse(product.getStartTime());
                    String Datetime  = df.format(date);
                    product.setStartTime(Datetime);
                }catch (Exception e){

                }
                if (!StringUtils.isEmpty(product.getImgs())){
                    String imgStr = product.getImgs();
                    product.setImgs(imgStr);
                    String[] img = imgStr.split(";");
                    if (img !=null && img.length>0){
                        product.setImgs(img[0]);
                    }
                }
            });
        }
        view.addObject("productList1",productList1);
        view.setViewName("store");
        return view;
    }


    @GetMapping("/getByProductType.html")
    @ResponseBody
    public List<Product> pcIndex(String productType) {

        List<Product> productList1 = productDao.getByProductTypeId(Long.parseLong(productType));
        productList1.forEach(product -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date= df.parse(product.getStartTime());
                String Datetime  = df.format(date);
                product.setStartTime(Datetime);
            }catch (Exception e){

            }
            if (!StringUtils.isEmpty(product.getImgs())){
                String imgStr = product.getImgs();
                product.setImgs(imgStr);
                String[] img = imgStr.split(";");
                if (img !=null && img.length>0){
                    product.setImgs(img[0]);
                }
            }
        });
        return productList1;
    }
}
