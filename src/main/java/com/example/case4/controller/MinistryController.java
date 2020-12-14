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

    @GetMapping("/classlist/{id}")
    public ModelAndView showListClass(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("classroom");
        modelAndView.addObject("listStudent", studentService.getListClass(id));
        modelAndView.addObject("listClass",ministryService.findAll());
        Iterable<Classroom> classroom = classService.findAll();
        modelAndView.addObject("classname",classroom);
        return modelAndView;
    }
//    @GetMapping("/student/{id}")
//    public ModelAndView showInfoStudent(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView("homeMinistry");
//        Long idStudent = appUserService.getCurrentUserId();
//        modelAndView.addObject("listClass",ministryService.findAll());
//        Optional<Student> student = studentService.findById(id);
//        modelAndView.addObject("student",student.get());
//        modelAndView.addObject("listMark",markStudentService.showListMark(id));
//        return modelAndView;
//    }
}
