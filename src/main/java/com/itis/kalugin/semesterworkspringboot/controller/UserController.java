package com.itis.kalugin.semesterworkspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/signIn")
    public String signIn() {

        return "signIn";
    }

    @GetMapping("/signUp")
    public String signUp() {

        return "signUp";
    }
}