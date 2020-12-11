package com.example.case4.controller;

import com.example.case4.model.Classroom;
import com.example.case4.model.Diary;
import com.example.case4.model.Student;
import com.example.case4.service.classroom.IClassService;
import com.example.case4.service.coach.ICoachService;
import com.example.case4.service.markstudent.IMarkStudentService;
import com.example.case4.service.student.IStudentService;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private IMarkStudentService markStudentService;

    @GetMapping("/classlist/{id}")
    public ModelAndView showListClass(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("classroom");
        modelAndView.addObject("listStudent", studentService.getListClass(id));
        modelAndView.addObject("listClass", coachService.showListClass(appUserService.getCurrentUserId()));
        Optional<Classroom> classroom = classService.findById(id);
        modelAndView.addObject("classname", classroom.get().getName());
        return modelAndView;
    }

    @GetMapping("/student/{id}")
    public ModelAndView showInfoStudent(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("coachStudent");
        Long idStudent = appUserService.getCurrentUserId();
        modelAndView.addObject("listClass", coachService.showListClass(idStudent));
        Optional<Student> student = studentService.findById(id);
        modelAndView.addObject("student", student.get());
        modelAndView.addObject("listMark", markStudentService.showListMark(id));
        modelAndView.addObject("diary", new Diary());
        return modelAndView;
    }

    @PostMapping("/student/{id}")
    public void saveDiary(@ModelAttribute Diary diary, @PathVariable Long id, HttpServletResponse response) {
        diary.setDate(LocalDate.now());
        Student student = studentService.findById(id).get();
        studentService.save(student);
        try {
            response.sendRedirect("/coach/student/"+id+"#3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
