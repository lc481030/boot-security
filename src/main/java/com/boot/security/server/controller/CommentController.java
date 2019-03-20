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
import com.boot.security.server.dao.CommentDao;
import com.boot.security.server.model.Comment;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentDao commentDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Comment save(@RequestBody Comment comment) {
        commentDao.save(comment);

        return comment;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Comment get(@PathVariable Long id) {
        return commentDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Comment update(@RequestBody Comment comment) {
        commentDao.update(comment);

        return comment;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return commentDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Comment> list(PageTableRequest request) {
                return commentDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        commentDao.delete(id);
    }
}
