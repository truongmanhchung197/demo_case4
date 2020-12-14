package com.example.case4.controller;

import com.example.case4.model.*;
import com.example.case4.service.classroom.IClassService;
import com.example.case4.service.coach.ICoachService;
import com.example.case4.service.ministry.IMinistryService;
import com.example.case4.service.student.IStudentService;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ICoachService coachService;
    @Autowired
    IStudentService studentService;
    @Autowired
    IClassService classService;
    @Autowired
    IAppUserService appUserService;
    @Autowired
    IMinistryService ministryService;

    @Value("${upload.path}")
    private String fileUpload;

    @ModelAttribute("coachs")
    public Iterable<Coach> coaches(){
        return coachService.findAll();
    }
    @ModelAttribute("classs")
    public Iterable<Classroom> classes(){
        return classService.findAll();
    }

    @GetMapping("/class_manager")
    public ModelAndView showFormClass(){
        ModelAndView modelAndView = new ModelAndView("adminClassManager");
        modelAndView.addObject("listClass",classService.findAll());
        modelAndView.addObject("class",new Classroom());
        return modelAndView;
    }
    @PostMapping("/class_manager_api")
    public ResponseEntity<Iterable<Classroom>> addClassRoom(@RequestBody Classroom classroom){
        classService.save(classroom);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/ministry_manager_api")
    public ResponseEntity<Iterable<Ministry>> addMinistry(@RequestBody Ministry ministry){
        ministryService.save(ministry);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/ministry_manager_api")
    public ResponseEntity<Iterable<Ministry>> getAllMinistry(){
        return new ResponseEntity<>(ministryService.findAll(),HttpStatus.OK);
    }
    @DeleteMapping("/ministry_manager_api/{id}")
    public ResponseEntity<Iterable<Ministry>> deleteMinistry(@PathVariable Long id){
        if(ministryService.findById(id).isPresent()){
            ministryService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/class_manager_api")
    public ResponseEntity<Iterable<Classroom>> showClassRoom(){
        return new ResponseEntity<>(classService.findAll(),HttpStatus.OK);
    }

    @DeleteMapping("/class_manager_api/{id}")
    public ResponseEntity<Iterable<Classroom>> deleteClassRoom(@PathVariable("id") Long id){
        if (classService.findById(id).isPresent()){
            classService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student_account")
    public ModelAndView showFormUser(){
        ModelAndView modelAndView = new ModelAndView("adminAccountStudent");
        modelAndView.addObject("listStudent",studentService.findAll());
        return modelAndView;
    }
    @GetMapping("/student_account/create")
    public ModelAndView showFormCreateStudent(){
        ModelAndView modelAndView = new ModelAndView("createAccountStudent");
        modelAndView.addObject("student",new Student());
        return modelAndView;
    }

    @PostMapping("/student_account/create")
    public void createStudent(@ModelAttribute Student student,HttpServletResponse response, @RequestParam(name = "username") String username,@RequestParam(name = "password") String password){
        AppRole appRole = new AppRole(1L,"ROLE_STUDENT");
        AppUser appUser = new AppUser(username,password,appRole);
        appUserService.save(appUser);

        MultipartFile multipartFile = student.getAvatar();
        String image = multipartFile.getOriginalFilename();
        student.setImage(image);
        try {
            FileCopyUtils.copy(student.getAvatar().getBytes(),new File(this.fileUpload + image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        student.setStatus(true);
        student.setId(appUser.getId());
        studentService.save(student);
        try {
            response.sendRedirect("/admin/student_account");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/student_account_edit/{id}")
    public ModelAndView showFormEditStudent(@PathVariable("id") Long id){
        Optional<Student> student = studentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("editAccountStudent");
        modelAndView.addObject("student",student.get());
        return modelAndView;
    }
    @PostMapping("/student_account_edit/{id}")
    public void EditStudent(@ModelAttribute Student student,HttpServletResponse response){

        MultipartFile multipartFile = student.getAvatar();
        String image = multipartFile.getOriginalFilename();
        if (!image.equals("")) {
            student.setImage(image);
            try {
                FileCopyUtils.copy(student.getAvatar().getBytes(),new File(this.fileUpload + image));
            } catch (IOException e) {
                e.printStackTrace();
            }
            student.setStatus(true);
            studentService.save(student);
        }else {
            Optional<Student> student1 = studentService.findById(student.getId());
            student1.get().setAddress(student.getAddress());
            student1.get().setGender(student.getGender());
            student1.get().setName(student.getName());
            student1.get().setTel(student.getTel());
            student1.get().setAge(student.getAge());
            student1.get().setDiaryList(student.getDiaryList());
            student1.get().setClassroom(student.getClassroom());

            studentService.save(student1.get());
        }
        try {
            response.sendRedirect("/admin/student_account");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/student_account_api/{id}")
        public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id){
        if (studentService.findById(id).isPresent()){
            studentService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/student_account_api")
    public ResponseEntity<Iterable<Student>> getAllStudent(){
        return new ResponseEntity<>(studentService.findAll(),HttpStatus.OK);
    }


    @GetMapping("/coach_account")
    public ModelAndView showFormCoach(){
        ModelAndView modelAndView = new ModelAndView("adminAccountCoach");
        modelAndView.addObject("coach",new Coach());
        modelAndView.addObject("listCoach",coachService.findAll());
        return modelAndView;
    }
    @GetMapping("/coach_account_api")
    public ResponseEntity<Iterable<Coach>> getAll(){
        return new ResponseEntity<>(coachService.findAll(),HttpStatus.OK);
    }
    @PostMapping("/coach_account_api")
    public ResponseEntity<Iterator<Coach>> createCoach(@RequestBody Coach coach ){
        coachService.save(coach);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/coach_account_api/{id}")
    public ResponseEntity<Coach> deleteCoach(@PathVariable("id") Long id){
        Optional<Coach> coach = coachService.findById(id);
        if (!coach.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coachService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/coach_account_edit/{id}")
    public ModelAndView showFormEditCoach(@PathVariable("id") Long id){
        Optional<Coach> coach = coachService.findById(id);
        ModelAndView modelAndView = new ModelAndView("editAccountCoach");
        modelAndView.addObject("coach", coach.get());
        return modelAndView;
    }
    @PostMapping("/coach_account_edit/{id}")
    public void editCoach(@ModelAttribute Coach coach, HttpServletResponse response){
        coachService.save(coach);
        try {
            response.sendRedirect("/admin/coach_account");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/ministry_account")
    public ModelAndView showListMinistry(){
        ModelAndView modelAndView = new ModelAndView("adminAccountMinistry");
        modelAndView.addObject("listMinistry",ministryService.findAll());
        return modelAndView;
    }
    @GetMapping("/student_statistical")
    public ModelAndView showStudentStatistical(){
        ModelAndView modelAndView = new ModelAndView("showStudentStatistical");
        ArrayList<Coach> listCoach = (ArrayList<Coach>) coachService.findAll();
        ArrayList<Integer> listClassNumber = new ArrayList<>();

        for (int i = 0; i< listCoach.size();i++){
            int classNumber = 0;
            List<Classroom> classroomIterator = (List<Classroom>) coachService.showListClass(listCoach.get(i).getId());
            for (int j = 0; j < classroomIterator.size();j++){
                List<Student> studentIterator = (List<Student>) studentService.getListClass(classroomIterator.get(j).getId());
                classNumber += studentIterator.size();
            }
            listClassNumber.add(classNumber);
        }


        modelAndView.addObject("listCoach",coachService.findAll());
        modelAndView.addObject("listStudentCoach",listClassNumber);
        return modelAndView;
    }
    @GetMapping("/class_mark_manager")
    public ModelAndView showChartManager(){
        ModelAndView modelAndView = new ModelAndView("adminMarkManager");
        modelAndView.addObject("listClass",classService.findAll());
        modelAndView.addObject("class",new Classroom());
        return modelAndView;
    }
    @PostMapping("/class_mark_manager")
    public ModelAndView chooseClass(){
        ModelAndView modelAndView = new ModelAndView("adminMarkManager");
        modelAndView.addObject("listClass",classService.findAll());
        modelAndView.addObject("class",new Classroom());
        return modelAndView;
    }
}
