package com.example.case4.controller;

import com.example.case4.model.Classroom;
import com.example.case4.model.DiaryClass;
import com.example.case4.model.DiaryStudent;
import com.example.case4.model.Student;
import com.example.case4.service.classroom.IClassService;
import com.example.case4.service.coach.ICoachService;
import com.example.case4.service.diaryClass.IDiaryClassService;
import com.example.case4.service.diaryStudent.IDiaryStudentService;
import com.example.case4.service.markstudent.IMarkStudentService;
import com.example.case4.service.student.IStudentService;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static java.lang.Math.random;

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
    @Autowired
    private IDiaryStudentService diaryStudentService;
    @Autowired
    private IDiaryClassService diaryClassService;

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
        Long idCoach = appUserService.getCurrentUserId();
        modelAndView.addObject("listClass", coachService.showListClass(idCoach));
        Optional<Student> student = studentService.findById(id);
        modelAndView.addObject("student", student.get());
        modelAndView.addObject("listDiary",diaryStudentService.getAllByStudentId(id));
        modelAndView.addObject("listMark", markStudentService.showListMark(id));
        modelAndView.addObject("diary", new DiaryStudent());
        return modelAndView;
    }

    @PostMapping("/student/{id}")
    public void saveDiary(@ModelAttribute DiaryStudent diary, @PathVariable Long id, HttpServletResponse response) {
        diary.setId(1000L);
        diary.setDate(LocalDate.now());
        Optional<Student> student = studentService.findById(id);
        diary.setStudent(student.get());
        diaryStudentService.save(diary);
        try {
            response.sendRedirect("/coach/student/"+id+"#3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/diary/{id}")
    public ModelAndView showDiaryClass(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("diaryClass");
        modelAndView.addObject("listClass", coachService.showListClass(appUserService.getCurrentUserId()));
        modelAndView.addObject("listDiary",diaryClassService.getAllByClassId(id));
        modelAndView.addObject("idClass",id);
        modelAndView.addObject("diary",new DiaryClass());
        return modelAndView;
    }
    @PostMapping("/diary/{id}")
    public void saveDiaryClass(@PathVariable Long id,@ModelAttribute DiaryClass diaryClass,HttpServletResponse response){
        diaryClass.setId(1000L);
        diaryClass.setDate(LocalDate.now());
        Optional<Classroom> classroom = classService.findById(id);
        diaryClass.setClassroom(classroom.get());
        diaryClassService.save(diaryClass);
        try {
            response.sendRedirect("/coach/diary/"+id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
