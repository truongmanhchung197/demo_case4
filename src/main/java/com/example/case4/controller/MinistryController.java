package com.example.case4.controller;

import com.example.case4.model.Classroom;
import com.example.case4.model.Student;
import com.example.case4.service.classroom.IClassService;
import com.example.case4.service.coach.ICoachService;
import com.example.case4.service.markstudent.IMarkStudentService;
import com.example.case4.service.ministry.IMinistryService;
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
@RequestMapping("/ministry")
public class MinistryController {
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IMinistryService ministryService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IMarkStudentService markStudentService;

    @GetMapping("/classlist")
    public ModelAndView showListClass(){
        ModelAndView modelAndView = new ModelAndView("classroom");
        Long id = appUserService.getCurrentUserId();
        modelAndView.addObject("listClass",classService.findAll());
        return modelAndView;
    }
//    @GetMapping("/classlist/{id}")
//    public ModelAndView showStudent(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView("listStudent");
//        modelAndView.addObject("listClass", classService.findById(id));
//        Optional<Student>student=studentService.findById(id);
//        modelAndView.addObject("student", student.get());
//        return modelAndView;
//    }
}
