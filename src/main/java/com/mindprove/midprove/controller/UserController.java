package com.mindprove.midprove.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")   
public class UserController {

    @GetMapping("/profile") 
    public String userProfile() {
        return "Your profile: This is secure user profile.";
    }

    @GetMapping("/info")     
    public String userInfo() {
        return "User info: Basic details here.";
    }
}