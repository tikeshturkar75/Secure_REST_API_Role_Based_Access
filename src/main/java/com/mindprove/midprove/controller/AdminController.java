package com.mindprove.midprove.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin") 
public class AdminController {

    @GetMapping("/dashboard") 
    public String adminDashboard() {
        return "Welcome Admin! This is secure admin dashboard.";
    }

    public String listUsers() {
        return "List of all users: admin, john.";
    }
}