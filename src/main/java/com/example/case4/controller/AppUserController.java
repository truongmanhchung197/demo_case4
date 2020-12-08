package com.example.case4.controller;

import com.example.case4.model.AppUser;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {

    @Autowired
    IAppUserService appUserService;

    @PostMapping("app_user")
    public ResponseEntity<Iterable<AppUser>> addAppUser(@RequestBody AppUser appUser){
        appUserService.save(appUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
