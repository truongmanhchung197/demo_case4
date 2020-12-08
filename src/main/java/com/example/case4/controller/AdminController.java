package com.example.case4.controller;

import com.example.case4.model.Coach;
import com.example.case4.service.coach.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ICoachService coachService;

    @GetMapping("/user_account")
    public ModelAndView showFormUser(){
        ModelAndView modelAndView = new ModelAndView("adminAccountStudent");
        return modelAndView;
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
}
