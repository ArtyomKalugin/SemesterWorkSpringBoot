package com.itis.kalugin.semesterworkspringboot.controller;

import com.itis.kalugin.semesterworkspringboot.dto.CreateUserDto;
import com.itis.kalugin.semesterworkspringboot.dto.RecipeDto;
import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.helper.TextHelper;
import com.itis.kalugin.semesterworkspringboot.service.inter.RecipeService;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final RecipeService recipeService;

    @Autowired
    public UserController(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
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

    @GetMapping("/allFindUsers")
    @ResponseBody
    public List<UserDto> getAllUsersByTitle(@RequestParam(value = "nickname",required = false) String nickname) {
        List<UserDto> users = userService.getUsersByNicknameLike(nickname);
        Collections.reverse(users);

        return users;
    }

    @GetMapping("/detailUser/{userId}")
    public String getDetailUser(@PathVariable("userId") Integer id, HttpSession session) {
        UserDto userNow = (UserDto) session.getAttribute("user");
        UserDto detailUser = userService.getUserById(id);

        if (userNow != null) {
            if (userNow.getEmail().equals(detailUser.getEmail())) {
                return "redirect:/account";
            }
        }

        int count = recipeService.getAllByUserId(id).size();

        session.setAttribute("detailUser", detailUser);
        session.setAttribute("count", count);

        return "detailUser";
    }

    @GetMapping("/allUsers")
    public String getAllUsers(HttpSession session) {
        List<UserDto> allUsers = userService.getAllUsers();
        Collections.reverse(allUsers);
        session.setAttribute("allUsers", allUsers);

        return "allUsers";
    }
}
