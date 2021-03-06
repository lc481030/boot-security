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
import com.boot.security.server.dao.TResourceSupportImgDao;
import com.boot.security.server.model.TResourceSupportImg;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tResourceSupportImgs")
public class TResourceSupportImgController {

    @Autowired
    private TResourceSupportImgDao tResourceSupportImgDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public TResourceSupportImg save(@RequestBody TResourceSupportImg tResourceSupportImg) {
        tResourceSupportImgDao.save(tResourceSupportImg);
        return tResourceSupportImg;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public TResourceSupportImg get(@PathVariable Long id) {
        return tResourceSupportImgDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public TResourceSupportImg update(@RequestBody TResourceSupportImg tResourceSupportImg) {
        tResourceSupportImgDao.update(tResourceSupportImg);
        return tResourceSupportImg;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return tResourceSupportImgDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<TResourceSupportImg> list(PageTableRequest request) {
                return tResourceSupportImgDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        tResourceSupportImgDao.delete(id);
    }
}
