package com.boot.security.server.controller;

import com.boot.security.server.dao.CommentDao;
import com.boot.security.server.model.Comment;
import com.boot.security.server.model.Dict;
import com.boot.security.server.model.FootPoint;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PcCommentController {

    @Resource
    private CommentDao commentDao;

    @RequestMapping("comment.html")
    public ModelAndView footPoint() {
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
}
