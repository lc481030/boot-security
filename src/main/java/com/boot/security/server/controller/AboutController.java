package com.boot.security.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AboutController {




    @RequestMapping("/about.html")
    public ModelAndView about() {
        ModelAndView view = new ModelAndView();
        view.addObject("aaa","12`3123");
        view.setViewName("about");
        return view;
    }

}
