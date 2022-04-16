package com.itis.kalugin.semesterworkspringboot.controller;

import com.itis.kalugin.semesterworkspringboot.dto.CreateUserDto;
import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

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
    public String signUp(Model model) {
        model.addAttribute("createUserDto", new CreateUserDto());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(CreateUserDto createUserDto) {
        UserDto userDto = userService.save(createUserDto);

        return "redirect:/signIn";
    }

    @GetMapping("/checkEmail")
    public void checkEmail(@RequestParam String email,
                           HttpServletResponse httpServletResponse) throws IOException {

        String result = "free";

        try {
            UserDto userDto = userService.getUserByEmail(email);
            result = "taken";

        } catch (NullPointerException exception) {
            result = "free";
        }

        httpServletResponse.getWriter().write(result);
    }
}
