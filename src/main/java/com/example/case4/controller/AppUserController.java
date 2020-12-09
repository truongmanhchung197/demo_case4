package com.example.case4.controller;

import com.example.case4.model.AppUser;
import com.example.case4.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {

    @Autowired
    IAppUserService appUserService;

    @PostMapping("/app_user")
    public ResponseEntity<Iterable<AppUser>> addAppUser(@RequestBody AppUser appUser){
        appUserService.save(appUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/app_user")
    public ResponseEntity<Iterable<AppUser>> getAppUser(){
        return new ResponseEntity<>(appUserService.findAll(),HttpStatus.OK);
    }
    @DeleteMapping("/app_user/{id}")
    public ResponseEntity<Iterable<AppUser>> deleteAppUser(@PathVariable("id") Long id){
        if (appUserService.findById(id).isPresent()){
            appUserService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
