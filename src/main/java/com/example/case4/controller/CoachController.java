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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
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
        Iterable<DiaryStudent> listDiary = diaryStudentService.getAllByStudentId(id);
        Collections.reverse((List<?>) listDiary);
        modelAndView.addObject("listDiary", listDiary);
        modelAndView.addObject("listMark", markStudentService.showListMark(id));
        modelAndView.addObject("newDiary", new DiaryStudent());
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
            response.sendRedirect("/coach/student/" + id + "#3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/diary/{id}")
    public ModelAndView showDiaryClass(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("diaryClass");
        modelAndView.addObject("listClass", coachService.showListClass(appUserService.getCurrentUserId()));
        Iterable<DiaryClass> diaryClasses = diaryClassService.getAllByClassId(id);
        Collections.reverse((List<?>) diaryClasses);
        modelAndView.addObject("listDiary", diaryClasses);
        modelAndView.addObject("idClass", id);
        modelAndView.addObject("newDiary", new DiaryClass());
        return modelAndView;
    }

    @PostMapping("/diary/{id}")
    public void saveDiaryClass(@PathVariable Long id, @ModelAttribute DiaryClass diaryClass, HttpServletResponse response) {
        diaryClass.setId(1000L);
        diaryClass.setDate(LocalDate.now());
        Optional<Classroom> classroom = classService.findById(id);
        diaryClass.setClassroom(classroom.get());
        diaryClassService.save(diaryClass);
        try {
            response.sendRedirect("/coach/diary/" + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/diary/edit/{idDiary}")
    public void editDiaryClass(@PathVariable Long idDiary, @ModelAttribute DiaryClass diaryClass, HttpServletResponse response) {
        DiaryClass diaryClass1 = diaryClassService.findById(idDiary).get();
        diaryClass1.setContent(diaryClass.getContent());
        diaryClassService.save(diaryClass1);
        Long idClass = diaryClass1.getClassroom().getId();
        try {
            response.sendRedirect("/coach/diary/" + idClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/diaryStudent/edit/{idDiary}")
    public void editDiaryStudent(@PathVariable Long idDiary, @ModelAttribute DiaryStudent diaryStudent, HttpServletResponse response) {
        DiaryStudent diaryStudent1 = diaryStudentService.findById(idDiary).get();
        diaryStudent1.setContent(diaryStudent.getContent());
        diaryStudentService.save(diaryStudent1);
        Long idStudent = diaryStudent1.getStudent().getId();
        try {
            response.sendRedirect("/coach/student/" + idStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
