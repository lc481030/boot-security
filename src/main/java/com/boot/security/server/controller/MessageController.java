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
import com.boot.security.server.dao.MessageDao;
import com.boot.security.server.model.Message;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Message save(@RequestBody Message message) {
        messageDao.save(message);

        return message;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Message get(@PathVariable Long id) {
        return messageDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Message update(@RequestBody Message message) {
        messageDao.update(message);

        return message;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return messageDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Message> list(PageTableRequest request) {
                return messageDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        messageDao.delete(id);
    }


    @PutMapping("/cl/{id}")
    @ApiOperation(value = "处理")
    public Message updateIsContact(@PathVariable Long id) {
        Message message = new Message();
        message.setId(id);
        message.setIsContact(1);
        messageDao.update(message);
        return message;
    }
}
