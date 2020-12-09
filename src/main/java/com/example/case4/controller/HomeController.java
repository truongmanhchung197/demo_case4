package com.example.case4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
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
    @GetMapping("/ministry")
    public String homeMinistry(){
        return "homeMinistry";
    }
    @GetMapping("/page403")
    public String noRedirect(){
        return "noredirect";
    }
}
