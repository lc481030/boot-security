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
import com.boot.security.server.dao.CustomizedDao;
import com.boot.security.server.model.Customized;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customizeds")
public class CustomizedController {

    @Autowired
    private CustomizedDao customizedDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Customized save(@RequestBody Customized customized) {
        customizedDao.save(customized);

        return customized;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Customized get(@PathVariable Long id) {
        return customizedDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Customized update(@RequestBody Customized customized) {
        customizedDao.update(customized);

        return customized;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return customizedDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Customized> list(PageTableRequest request) {
                return customizedDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        customizedDao.delete(id);
    }
}
