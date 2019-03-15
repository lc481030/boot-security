package com.boot.security.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {

    @RequestMapping("/adminlogin.html")
    public ModelAndView adminLogin() {
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        return view;
    }
}
