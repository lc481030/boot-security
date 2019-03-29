package com.boot.security.server.controller;

import com.boot.security.server.dao.NewsDao;
import com.boot.security.server.model.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdvisoryActivitiesController {
    /**
     * 活动咨询
     * @return
      */
    @Resource
    private  NewsDao newsDao;

    @RequestMapping("news.html")
    public ModelAndView news(){
        //查询所有不为空的图片
        List<News> list = newsDao.getByAllImg();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("news");
        //查询前六条内容数据
      /*  List<News> contList = newsDao.getByAllCont();
        modelAndView.addObject("contList",contList);*/
        return modelAndView;
    }

    @RequestMapping("newslist.html")
    public ModelAndView newList(){
        ModelAndView view = new ModelAndView();
        List<News> newsLists = newsDao.querTen();
       /* List<News>  newsss = new ArrayList<>();
        for(int i =0;i<newsLists.size();i++){
            int dex = newsLists.get(i).getDetail().indexOf(">");
            String  aa = newsLists.get(i).getDetail().substring(dex+1);
        }*/
        view.addObject("newsLists",newsLists);
        view.setViewName("newslist");
        return view;
    }

    /**
     * 查询六条数据
     * @return
     */
    @GetMapping("/getSixlist.html")
    @ResponseBody
    public List<News>  getSixlist(){
        List<News> sixLists = newsDao.getByAllCont();
        return  sixLists;
    }
    /**
     * 查看更多
     */
    @GetMapping("/getNewslist.html")
    @ResponseBody
    public List<News>  getNewslist(){
        List<News> newsLists = newsDao.querAll();
        return  newsLists;
    }
}
