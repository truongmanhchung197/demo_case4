package com.example.case4.controller;

import com.example.case4.model.Mark;
import com.example.case4.model.Student;
import com.example.case4.service.markstudent.IMarkStudentService;
import com.example.case4.service.student.IStudentService;
import com.example.case4.service.student.StudentService;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IMarkStudentService markStudentService;
    @GetMapping("/sutdent/{id}")
    public ModelAndView showInfostudent(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("homeStudent");
        modelAndView.addObject("student", appUserService.getCurrentUserId());
        Iterable<Mark> listMark= markStudentService.showListMark(id);
        modelAndView.addObject("listMark",listMark);
        Optional<Student>student=studentService.findById(id);
        modelAndView.addObject("student", student.get());
        return modelAndView;
    }
}


