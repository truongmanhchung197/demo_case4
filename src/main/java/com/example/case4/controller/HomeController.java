package com.example.case4.controller;

import com.example.case4.service.classroom.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private IClassService classService;
    @GetMapping("/")
    public String index(){
        return "home";
    }
    @GetMapping("/student")
    public String homeStudent(){
        return "homeStudent";
    }
    @GetMapping("/coach")
    public String homeCoach(){
        return "homeCoach";
    }
    @GetMapping("/admin")
    public String homeAdmin(){
        return "homeAdmin";
    }
    @GetMapping("/page403")
    public String noRedirect(){
        return "noredirect";
    }
}
