package com.itis.kalugin.semesterworkspringboot.controller;

import com.itis.kalugin.semesterworkspringboot.dto.CreateUserDto;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signIn")
    public String signIn() {

        return "signIn";
    }

    @GetMapping("/signUp")
    public String signUp() {

        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto createUserDto) {
        userService.save(createUserDto);

        return "info";
    }
}
