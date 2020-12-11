package com.example.case4.controller;

import com.example.case4.model.Student;
import com.example.case4.service.classroom.IClassService;
import com.example.case4.service.coach.ICoachService;
import com.example.case4.service.student.IStudentService;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private ICoachService coachService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IAppUserService appUserService;
    @GetMapping("/")
    public String index(){
        return "home";
    }
    @GetMapping("/student")
    public ModelAndView homeStudent(){
        ModelAndView modelAndView = new ModelAndView("homeStudent");
        Long id = appUserService.getCurrentUserId();
        Optional<Student> student = studentService.findById(id);
        if(!student.isPresent()){
            return new ModelAndView("noredirect");
        }
        modelAndView.addObject("student",student.get());
        return modelAndView;
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
