package com.boot.security.server.controller;

import com.boot.security.server.dao.*;
import com.boot.security.server.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController {

    @Resource
    private TIndexImgDao indexImgDao;

    @Resource
    private ProductLineDao productLineDao;
    @Resource
    private CommentDao commentDao;

    @Resource
    private FootPointDao footPointDao;

    @Resource
    private TResourceSupportImgDao tResourceSupportImgDao;

    /*================================================================*
    * =========================   首页   =============================*
    * ================================================================*
    * ================================================================*
    * */
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        List<TIndexImg> imgList = indexImgDao.getAllList();
        view.addObject("imgList",imgList);
        List<ProductLine> productList = productLineDao.getAllList();
        view.addObject("productList",productList);

        Map<String, Object> params = new HashMap<>();
        List<Comment> commentList = commentDao.list(params,0,20);
        commentList.forEach(comment -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            comment.setCommentTimeStr(DateFormat.getDateInstance().format(comment.getCommentTime()));
        });

        view.addObject("commentList",commentList);

        //查询所有的分类
        List<FootPoint> pointList = footPointDao.list(params,0,12);;
        view.addObject("pointList",pointList);



        view.setViewName("index");
        return view;
    }

    @RequestMapping("index.html")
    public ModelAndView pcIndex() {
        ModelAndView view = new ModelAndView();
        List<TIndexImg> imgList = indexImgDao.getAllList();
        List<ProductLine> productList = productLineDao.getAllList();
        view.addObject("productList",productList);
        view.addObject("imgList",imgList);

        Map<String, Object> params = new HashMap<>();
        List<Comment> commentList = commentDao.list(params,0,20);
        commentList.forEach(comment -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            comment.setCommentTimeStr(DateFormat.getDateInstance().format(comment.getCommentTime()));
        });

        view.addObject("commentList",commentList);

        //查询所有的分类
        List<FootPoint> pointList = footPointDao.list(params,0,12);;
        view.addObject("pointList",pointList);

        //资源支持
        List<TResourceSupportImg> tResourceSupportImgList = tResourceSupportImgDao.imgList();
        view.addObject("tResourceSupportImgList",tResourceSupportImgList);

        view.setViewName("index");
        return view;
    }
}
