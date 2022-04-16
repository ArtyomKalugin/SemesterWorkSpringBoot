package com.itis.kalugin.semesterworkspringboot.controller;

import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class InfoController {

    @GetMapping("/info")
    public String getInfo() {

        return "info";
    }
}
