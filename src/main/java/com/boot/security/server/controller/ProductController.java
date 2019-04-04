package com.boot.security.server.controller;

import java.util.Date;
import java.util.List;

import com.boot.security.server.dao.ProductTypeDao;
import com.boot.security.server.model.ProductType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.ProductDao;
import com.boot.security.server.model.Product;

import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Resource
    private ProductDao productDao;

    @Resource
    private ProductTypeDao productTypeDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Product save(@RequestBody Product product) {
        product.setRecommendIndex(0);
        ProductType productType = productTypeDao.getById(Long.parseLong(product.getProductType()));
        product.setProductMode(productType.getProductMode());
        productDao.save(product);
        return product;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Product get(@PathVariable Long id) {
        return productDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Product update(@RequestBody Product product) {
        product.setRecommendIndex(null);

        ProductType productType = productTypeDao.getById(Long.parseLong(product.getProductType()));
        product.setProductMode(productType.getProductMode());
        productDao.update(product);

        return product;
    }


    @PutMapping("productsRecommend/{id}")
    @ApiOperation(value = "推荐")
    public Product productsRecommend(@PathVariable Long id) {
        Product product = productDao.getById(id);
        Integer re = product.getRecommendIndex();
        if (StringUtils.isEmpty(re) || product.getRecommendIndex().toString().equals("0")){
            product.setRecommendIndex(1);
        }else if ( product.getRecommendIndex().toString().equals("1")){
            product.setRecommendIndex(0);
        }
        product.setUpdateTime(new Date());
        productDao.update(product);
        return product;

    }


    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return productDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Product> list(PageTableRequest request) {
                return productDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        productDao.delete(id);
    }
}
