package com.itis.kalugin.semesterworkspringboot.controller;

import com.itis.kalugin.semesterworkspringboot.dto.CreateUserDto;
import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signIn")
    public String signIn(@RequestParam(value = "reason", required = false) String reason) {

        if (reason != null && reason.equals("error")) {
            return "failureSignIn";
        }

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

        String result;
        UserDto user = userService.getUserByEmail(email);

        if (user == null) {
            result = "free";
        } else {
            result = "taken";
        }

        httpServletResponse.getWriter().write(result);
    }

    @GetMapping("/account")
    public String getAccount() {

        return "account";
    }

    @PostMapping("/changeAvatar")
    public String changeAvatar(HttpSession session, @RequestParam("avatar") MultipartFile avatar) {

        UserDto user = (UserDto) session.getAttribute("user");
        UserDto userDto = userService.updateAvatar(avatar, user);
        session.setAttribute("user", userDto);

        return "redirect:/account";
    }

    @GetMapping("/deleteUser/{user-id}")
    public String deleteUser(@PathVariable("user-id") Integer id) {
        userService.deleteUser(id);

        return "redirect:/logout";
    }
}
