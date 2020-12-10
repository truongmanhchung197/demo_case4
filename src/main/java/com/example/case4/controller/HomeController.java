package com.example.case4.controller;

import com.example.case4.service.classroom.IClassService;
import com.example.case4.service.coach.ICoachService;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private ICoachService coachService;
    @Autowired
    private IAppUserService appUserService;
    @GetMapping("/")
    public String index(){
        return "home";
    }
    @GetMapping("/student")
    public String homeStudent(){
        return "homeStudent";
    }
    @GetMapping("/coach")
    public ModelAndView homeCoach(){
        ModelAndView modelAndView = new ModelAndView("homeCoach");
        modelAndView.addObject("listClass",coachService.showListClass(appUserService.getCurrentUserId()));
        return modelAndView;
    }
    @GetMapping("/admin")
    public String homeAdmin(){
        return "homeAdmin";
    }
    @GetMapping("/ministry")
    public String homeMinistry(){
        return "homeMinistry";
    }
    @GetMapping("/page403")
    public String noRedirect(){
        return "noredirect";
    }
}
