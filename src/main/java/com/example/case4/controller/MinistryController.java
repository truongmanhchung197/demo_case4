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
import java.util.ArrayList;
import java.util.List;
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
        modelAndView.addObject("modules",moduleService.findAll());
        return modelAndView;
    }

    @PostMapping("/classlist/{idClass}")
    public ModelAndView average_mark(@PathVariable Long idClass,@RequestParam Long idModule) {
        ModelAndView modelAndView = new ModelAndView("listStudent");
        ArrayList<Student> listStudent = (ArrayList<Student>) studentService.getListClass(idClass);
        ArrayList<Mark> markArrayList = new ArrayList<>();
        Float averagePractice = 0F;
        Float averageTheory = 0F;
        for (int i = 0; i < listStudent.size();i++){
            Mark mark = markStudentService.getByStudentIdAndModuleId(listStudent.get(i).getId(),idModule);
            averagePractice += mark.getPractice_point();
            averageTheory += mark.getTheory_point();
        }
        averagePractice = averagePractice/listStudent.size();
        averageTheory = averageTheory/listStudent.size();
        modelAndView.addObject("averagePractice",averagePractice);
        modelAndView.addObject("averageTheory",averageTheory);
        modelAndView.addObject("listClass", classService.findAll());
        Iterable<Student> listStudent2 = studentService.getListClass(idClass);
        modelAndView.addObject("listStudent", listStudent2);
        Optional<Classroom> classroom = classService.findById(idClass);
        modelAndView.addObject("classname", classroom.get().getName());
        modelAndView.addObject("modules",moduleService.findAll());

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

    @GetMapping("/student/edit/{idStudent}/{idModule}")
    public ModelAndView showFormEditMark(@PathVariable Long idStudent, @PathVariable Long idModule){
        ModelAndView modelAndView = new ModelAndView("editMark");
        Mark mark = markStudentService.getByStudentIdAndModuleId(idStudent,idModule);
        modelAndView.addObject("mark",mark);
        return modelAndView;
    }
    @PostMapping("/student/edit/{idStudent}/{idModule}")
    public void editMark(@ModelAttribute Mark mark, HttpServletResponse response,@PathVariable Long idStudent,@PathVariable Long idModule){

        mark.setModule(moduleService.findById(idModule).get());
        mark.setStudent(studentService.findById(idStudent).get());
        markStudentService.save(mark);
        try {
            response.sendRedirect("/ministry/student/" + idStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/student/create/{idStudent}")
    public ModelAndView showFormCreateMark(@PathVariable Long idStudent){
        ModelAndView modelAndView = new ModelAndView("createMark");

        modelAndView.addObject("modules",moduleService.findAll());
        modelAndView.addObject("mark", new Mark());
        return modelAndView;
    }
    @PostMapping("/student/create/{idStudent}")
    public void createMark(@ModelAttribute Mark mark, HttpServletResponse response, @PathVariable Long idStudent, @RequestParam Long idModule){
        mark.setModule(moduleService.findById(idModule).get());
        mark.setStudent(studentService.findById(idStudent).get());
        markStudentService.save(mark);
        try {
            response.sendRedirect("/ministry/student/" + idStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
