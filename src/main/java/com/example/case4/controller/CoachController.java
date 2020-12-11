package com.example.case4.controller;

import com.example.case4.model.Classroom;
import com.example.case4.model.Student;
import com.example.case4.service.classroom.IClassService;
import com.example.case4.service.coach.ICoachService;
import com.example.case4.service.student.IStudentService;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private ICoachService coachService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassService classService;

    @GetMapping("/classlist/{id}")
    public ModelAndView showListClass(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("classroom");
        modelAndView.addObject("listStudent", studentService.getListClass(id));
        modelAndView.addObject("listClass",coachService.showListClass(appUserService.getCurrentUserId()));
        Optional<Classroom> classroom = classService.findById(id);
        modelAndView.addObject("classname",classroom.get().getName());
        return modelAndView;
    }
    @GetMapping("/student/{id}")
    public ModelAndView showInfoStudent(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("coachStudent");
        modelAndView.addObject("listClass",coachService.showListClass(appUserService.getCurrentUserId()));
        Optional<Student> student = studentService.findById(id);
        modelAndView.addObject("student",student.get());
        return modelAndView;
    }

}
