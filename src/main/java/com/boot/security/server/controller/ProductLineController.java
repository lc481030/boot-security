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
import com.boot.security.server.dao.ProductLineDao;
import com.boot.security.server.model.ProductLine;

import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

@RestController
@RequestMapping("/productLines")
public class ProductLineController {

    @Resource
    private ProductLineDao productLineDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ProductLine save(@RequestBody ProductLine productLine) {
        productLineDao.save(productLine);

        return productLine;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ProductLine get(@PathVariable Long id) {
        return productLineDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ProductLine update(@RequestBody ProductLine productLine) {
        productLineDao.update(productLine);

        return productLine;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return productLineDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ProductLine> list(PageTableRequest request) {
                return productLineDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        productLineDao.delete(id);
    }
}
