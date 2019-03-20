package com.boot.security.server.controller;

import com.boot.security.server.dao.DictDao;
import com.boot.security.server.dao.FootPointDao;
import com.boot.security.server.dao.ProductDao;
import com.boot.security.server.dao.ProductTypeDao;
import com.boot.security.server.model.Dict;
import com.boot.security.server.model.FootPoint;
import com.boot.security.server.model.Product;
import com.boot.security.server.model.ProductType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class PcFootPointController {

    @Resource
    private DictDao dictDao;
    @Resource
    private FootPointDao footPointDao;


    @RequestMapping("footPoint.html")
    public ModelAndView footPoint() {
        ModelAndView view = new ModelAndView();
        //查询所有的分类
        List<Dict> dictList = dictDao.listByType("footpointType");
        view.addObject("dictList",dictList);
        FootPoint footPoint = new FootPoint();
        List<FootPoint> pointList = footPointDao.getByPcFootPoint(footPoint);
        view.addObject("pointList",pointList);
        view.setViewName("footPoint");
        return view;
    }

    @GetMapping("/getByFootPointType.html")
    @ResponseBody
    public List<FootPoint> pcIndex(String footPointType) {
        FootPoint footPoint = new FootPoint();
        footPoint.setFootPointType(Integer.parseInt(footPointType));
        List<FootPoint> pointList = footPointDao.getByPcFootPoint(footPoint);
        return pointList;
    }
}
