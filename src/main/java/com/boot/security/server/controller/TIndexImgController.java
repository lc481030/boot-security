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
import com.boot.security.server.dao.TIndexImgDao;
import com.boot.security.server.model.TIndexImg;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/indexImgs")
public class TIndexImgController {

    @Autowired
    private TIndexImgDao tIndexImgDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public TIndexImg save(@RequestBody TIndexImg tIndexImg) {
        tIndexImgDao.save(tIndexImg);

        return tIndexImg;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public TIndexImg get(@PathVariable Long id) {
        return tIndexImgDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public TIndexImg update(@RequestBody TIndexImg tIndexImg) {
        tIndexImgDao.update(tIndexImg);

        return tIndexImg;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return tIndexImgDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<TIndexImg> list(PageTableRequest request) {
                return tIndexImgDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        tIndexImgDao.delete(id);
    }
}
