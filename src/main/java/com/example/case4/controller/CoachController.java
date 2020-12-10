package com.example.case4.controller;

import com.example.case4.service.classroom.IClassService;
import com.example.case4.service.coach.ICoachService;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private ICoachService coachService;

}
