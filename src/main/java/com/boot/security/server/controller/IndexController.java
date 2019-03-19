package com.boot.security.server.controller;

import com.boot.security.server.dao.ProductLineDao;
import com.boot.security.server.dao.TIndexImgDao;
import com.boot.security.server.model.ProductLine;
import com.boot.security.server.model.TIndexImg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class IndexController {

    @Resource
    private TIndexImgDao indexImgDao;

    @Resource
    private ProductLineDao productLineDao;

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
        view.setViewName("index");
        return view;
    }
}
