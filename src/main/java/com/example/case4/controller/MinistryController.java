package com.example.case4.controller;

import com.example.case4.model.Classroom;
import com.example.case4.model.Mark;
import com.example.case4.model.Student;
import com.example.case4.service.classroom.IClassService;
import com.example.case4.service.coach.ICoachService;
import com.example.case4.service.markstudent.IMarkStudentService;
import com.example.case4.service.ministry.IMinistryService;
import com.example.case4.service.module.IModuleService;
import com.example.case4.service.student.IStudentService;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    private IModuleService moduleService;

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
        modelAndView.addObject("listClass", classService.findAll());
        Iterable<Student> listStudent = studentService.getListClass(id);
        modelAndView.addObject("listStudent", listStudent);
        Optional<Classroom> classroom = classService.findById(id);
        modelAndView.addObject("classname", classroom.get().getName());
        return modelAndView;
    }

    @GetMapping("/student/{id}")
    public ModelAndView showInfoStudent(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("infoStudent");
        Iterable<Mark> listMark= markStudentService.showListMark(id);
        modelAndView.addObject("listMark",listMark);
        Optional<Student> student = studentService.findById(id);
        modelAndView.addObject("student", student.get());
        return modelAndView;
    }

    @GetMapping("/student/edit/{idClass}/{idModule}")
    public ModelAndView showFormEditMark(@PathVariable Long idClass,@PathVariable Long idModule){
        ModelAndView modelAndView = new ModelAndView("editMark");
        Mark mark = markStudentService.getByStudentIdAndModuleId(idClass,idModule);
        modelAndView.addObject("mark",mark);
        return modelAndView;
    }
    @PostMapping("/student/edit/{idClass}/{idModule}")
    public void editMark(@ModelAttribute Mark mark, HttpServletResponse response,@PathVariable Long idClass,@PathVariable Long idModule){

        mark.setModule(moduleService.findById(idModule).get());
        mark.setStudent(studentService.findById(idClass).get());
        markStudentService.save(mark);
        try {
            response.sendRedirect("/ministry/student/" + idClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
