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
import com.boot.security.server.dao.FootPointDao;
import com.boot.security.server.model.FootPoint;

import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

@RestController
@RequestMapping("/footPoints")
public class FootPointController {

    @Resource
    private FootPointDao footPointDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public FootPoint save(@RequestBody FootPoint footPoint) {
        footPoint.setDeleted(0);
        footPointDao.save(footPoint);

        return footPoint;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public FootPoint get(@PathVariable Long id) {
        return footPointDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public FootPoint update(@RequestBody FootPoint footPoint) {
        footPoint.setDeleted(0);
        footPointDao.update(footPoint);

        return footPoint;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return footPointDao.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<FootPoint> list(PageTableRequest request) {
                return footPointDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        footPointDao.delete(id);
    }
}
