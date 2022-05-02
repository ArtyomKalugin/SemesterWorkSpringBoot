package com.itis.kalugin.semesterworkspringboot.controller;

import com.cloudinary.utils.ObjectUtils;
import com.itis.kalugin.semesterworkspringboot.dto.RecipeDto;
import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.helper.CloudinaryHelper;
import com.itis.kalugin.semesterworkspringboot.helper.ImageHelper;
import com.itis.kalugin.semesterworkspringboot.model.Recipe;
import com.itis.kalugin.semesterworkspringboot.model.User;
import com.itis.kalugin.semesterworkspringboot.service.inter.RecipeService;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/allRecipes")
    public String getAllRecipes(HttpSession session) {

        List<RecipeDto> allRecipes = recipeService.getAll();
        Collections.reverse(allRecipes);
        session.setAttribute("allRecipes", allRecipes);

        return "allRecipes";
    }

    @GetMapping("/createRecipe")
    public String createRecipe() {

        return "addRecipe";
    }

    @PostMapping("/createRecipe")
    public String saveRecipe(@RequestParam("title") String title, @RequestParam("content") String content,
                             @RequestParam("photo") MultipartFile photo, HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("user");
        User user = userService.getRawUserByEmail(userDto.getEmail());

        if (title.isEmpty() || content.isEmpty() || photo.isEmpty()) {

            return "failureAddRecipe";
        }

        try {
            File recipePhoto = ImageHelper.makeFile(photo);
            String filename = "recipePhoto" + userDto.getId();

            Map upload = CloudinaryHelper.getInstance().uploader()
                    .upload(recipePhoto, ObjectUtils.asMap("public_id", filename));
            String url = (String) upload.get("url");

            Date date = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");

            Recipe recipe = new Recipe(title, content, url, formatForDateNow.format(date), user);

            recipeService.save(recipe);

            recipePhoto.delete();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


        return "redirect:/account";
    }
}
