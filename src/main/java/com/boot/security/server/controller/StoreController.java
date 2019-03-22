package com.boot.security.server.controller;

import com.boot.security.server.dao.DictDao;
import com.boot.security.server.dao.ProductDao;
import com.boot.security.server.dao.ProductTypeDao;
import com.boot.security.server.model.*;
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

    @Resource
    private DictDao dictDao;


    /**
     * 旅游商城
     * @return
     */
    @RequestMapping("store.html")
    public ModelAndView storeIndex() {
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
        List<Product> productList2 = new ArrayList<>();
        List<Product> productList3 = new ArrayList<>();
        List<Product> productList4 = new ArrayList<>();

        if (productTypes1 !=null && productTypes1.size()>0){
            productList1 = productDao.getByProductTypeId(productTypes1.get(0).getId());
            productList1 = productFormat(productList1);
        }

        if (productTypes2 !=null && productTypes2.size()>0){
            productList2 = productDao.getByProductTypeId(productTypes2.get(0).getId());
            productList2 = productFormat(productList2);
        }

        if (productTypes3 !=null && productTypes3.size()>0){
            productList3 = productDao.getByProductTypeId(productTypes3.get(0).getId());
            productList3 = productFormat(productList3);
        }

        if (productTypes4 !=null && productTypes4.size()>0){
            productList4 = productDao.getByProductTypeId(productTypes4.get(0).getId());
            productList4 = productFormat(productList4);
        }
        view.addObject("productList1",productList1);
        view.addObject("productList2",productList2);
        view.addObject("productList3",productList3);
        view.addObject("productList4",productList4);

        view.setViewName("store");
        return view;
    }


    public List<Product> productFormat(List<Product> productList1){
        productList1.forEach(product -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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

    /**
     * 获取旅游类型
     * @param productType
     * @return
     */
    @GetMapping("/getByProductType.html")
    @ResponseBody
    public List<Product> pcIndex(String productType) {
        List<Product> productList1 = productDao.getByProductTypeId(Long.parseLong(productType));
        return productFormat(productList1);
    }


    /**
     * 旅游商城
     * @return
     */
    @RequestMapping("customized.html")
    public ModelAndView customized() {
        ModelAndView view = new ModelAndView();
        view.setViewName("customized");
        return view;
    }



    /**
     * 旅游商城
     * @return
     */
    @RequestMapping("storeList.html")
    public ModelAndView storeList(StoreSearch search) {
        ModelAndView view = new ModelAndView();

        if (StringUtils.isEmpty(search.getProductMode())){
            search.setProductMode("");
        }

        if (StringUtils.isEmpty(search.getAfterCity())){
            search.setAfterCity("");
        }

        if (StringUtils.isEmpty(search.getPageSize())){
            search.setPageSize(1);
        }
        if (StringUtils.isEmpty(search.getBeginDay())){
            search.setBeginDay("");
        }

        if (StringUtils.isEmpty(search.getEndDay())){
            search.setEndDay("");
        }
        if (StringUtils.isEmpty(search.getStartCity())){
            search.setStartCity("");
        }



        //查询四种旅游各自的数量
        List<ProductModeNum> productModeNums = productDao.getNumByModeType();


        List<Product> productList = productDao.storeList(search, 0, 10);
        productList = productFormat(productList);
        Integer totalNum = 0;

        for (ProductModeNum modeNums: productModeNums) {
            totalNum +=Integer.parseInt(modeNums.getProductNum());
            if (modeNums.getProductMode().equals("1")){
                modeNums.setProductName("地区游");
            }else if (modeNums.getProductMode().equals("2")){
                modeNums.setProductName("主题游");
            }else if (modeNums.getProductMode().equals("3")){
                modeNums.setProductName("景点游");
            }else if (modeNums.getProductMode().equals("4")){
                modeNums.setProductName("交通游");
            }
        }
        view.addObject("totalNum",totalNum);
        view.addObject("productModeNums",productModeNums);

        /*途径城市*/
        List<Dict> afterCitys = dictDao.listByType("afterCity");
        /*出发城市*/
        List<Dict> startCitys = dictDao.listByType("startCity");
        /*目标国家*/
        List<Dict> targetCountry = dictDao.listByType("targetCountry");

        /*查询符合要求的当前的旅游数据*/

        view.addObject("productList",productList);

        view.addObject("startCitys",startCitys);
        view.addObject("targetCountrys",targetCountry);
        view.addObject("afterCitys",afterCitys);
        view.setViewName("storeList");
        view.addObject("search",search);
        return view;
    }


    /**
     * 旅游商城
     * @return
     */
    @GetMapping("getProductList.html")
    @ResponseBody
    public List<Product> getProductList(StoreSearch search) {
        search.getAfterCity();
        List<Product> productList = productDao.storeList(null, (search.getPageSize()-1)*10, 10);
        return productFormat(productList);
    }

}
