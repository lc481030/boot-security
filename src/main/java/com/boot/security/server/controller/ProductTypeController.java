package com.boot.security.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.boot.security.server.dao.ProductTypeDao;
import com.boot.security.server.model.ProductType;

import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

@RestController
@RequestMapping("/productTypes")
public class ProductTypeController {

    @Resource
    private ProductTypeDao productTypeDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ProductType save(@RequestBody ProductType productType) {
        productTypeDao.save(productType);

        return productType;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ProductType get(@PathVariable Long id) {
        return productTypeDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ProductType update(@RequestBody ProductType productType) {
        productTypeDao.update(productType);

        return productType;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return productTypeDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ProductType> list(PageTableRequest request) {
                return productTypeDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        productTypeDao.delete(id);
    }
}
