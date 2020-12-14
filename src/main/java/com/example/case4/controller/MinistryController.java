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
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView showListClass() {
        ModelAndView modelAndView = new ModelAndView("classroom");
        Long id = appUserService.getCurrentUserId();
        modelAndView.addObject("listClass", classService.findAll());
        return modelAndView;
    }

    @GetMapping("/classlist/{id}")
    public ModelAndView showListStudent(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("listStudent");
        Iterable<Student> listStudent = studentService.getListClass(id);
        modelAndView.addObject("listStudent", listStudent);
        Optional<Classroom> classroom = classService.findById(id);
        modelAndView.addObject("classname", classroom.get().getName());
        return modelAndView;
    }

    @GetMapping("/student/{id}")
    public ModelAndView showInfoStudent(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("infoStudent");
        Optional<Student> student = studentService.findById(id);
        modelAndView.addObject("student", student.get());
        return modelAndView;
    }

}
