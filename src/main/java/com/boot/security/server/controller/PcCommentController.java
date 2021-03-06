package com.boot.security.server.controller;

import com.boot.security.server.dao.CommentDao;
import com.boot.security.server.dao.MessageDao;
import com.boot.security.server.model.Comment;
import com.boot.security.server.model.Dict;
import com.boot.security.server.model.FootPoint;
import com.boot.security.server.model.Message;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PcCommentController {

    @Resource
    private CommentDao commentDao;

    @Resource
    private MessageDao messageDao;

    @RequestMapping("comment.html")
    public ModelAndView comment() {
        ModelAndView view = new ModelAndView();
        List<Comment> list = commentDao.getByAll();
        list.forEach(comment -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            comment.setCommentTimeStr(DateFormat.getDateInstance().format(comment.getCommentTime()));
        });

        view.addObject("list",list);
        view.setViewName("comment");
        return view;
    }

    @RequestMapping("message.html")
    public ModelAndView message() {
        ModelAndView view = new ModelAndView();
        view.setViewName("message");
        return view;
    }

    @PostMapping("messageAdd.html")
    @ResponseBody
    @ApiOperation(value = "保存")
    public Message save(@RequestBody Message message, HttpServletRequest request) {
        message.setIsContact(0);
        messageDao.save(message);
        return message;
    }

}
