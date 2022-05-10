package com.itis.kalugin.semesterworkspringboot.controller;

import com.cloudinary.utils.ObjectUtils;
import com.itis.kalugin.semesterworkspringboot.dto.RecipeCommentDto;
import com.itis.kalugin.semesterworkspringboot.dto.RecipeDto;
import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.helper.CloudinaryHelper;
import com.itis.kalugin.semesterworkspringboot.helper.ImageHelper;
import com.itis.kalugin.semesterworkspringboot.helper.TextHelper;
import com.itis.kalugin.semesterworkspringboot.model.Recipe;
import com.itis.kalugin.semesterworkspringboot.model.User;
import com.itis.kalugin.semesterworkspringboot.service.inter.RecipeCommentService;
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
import java.util.stream.Collectors;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;
    private final RecipeCommentService recipeCommentService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService,
                            RecipeCommentService recipeCommentService) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.recipeCommentService = recipeCommentService;
    }

    @GetMapping("/allRecipes")
    public String getAllRecipes(HttpSession session) {

        List<RecipeDto> allRecipes = recipeService.getAll();
        Collections.reverse(allRecipes);
        allRecipes = allRecipes.stream()
                .peek(recipe -> recipe.setText(TextHelper.editText(recipe.getText())))
                .collect(Collectors.toList());

        session.setAttribute("allRecipes", allRecipes);
        session.removeAttribute("isMyRecipe");

        return "allRecipes";
    }

    @GetMapping("/allFindRecipes")
    @ResponseBody
    public List<RecipeDto> getAllRecipesByTitle(@RequestParam(value = "title",required = false) String title) {
        List<RecipeDto> recipes = recipeService.getRecipesByTitleLike(title);
        recipes = recipes.stream()
                .peek(recipe -> recipe.setText(TextHelper.editText(recipe.getText())))
                .collect(Collectors.toList());

        Collections.reverse(recipes);

        return recipes;
    }

    @GetMapping("/allMyFindRecipes")
    @ResponseBody
    public List<RecipeDto> getAllMyRecipesByTitle(@RequestParam(value = "title",required = false) String title,
                                                  HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        List<RecipeDto> recipes = recipeService.getRecipesByTitleLikeAndUserId(title, user.getId());

        recipes = recipes.stream()
                .peek(recipe -> recipe.setText(TextHelper.editText(recipe.getText())))
                .collect(Collectors.toList());

        Collections.reverse(recipes);

        return recipes;
    }

    @GetMapping("/myRecipes")
    public String getMyRecipes(HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("user");
        List<RecipeDto> allRecipes = recipeService.getAllByUserId(user.getId());
        Collections.reverse(allRecipes);

        allRecipes = allRecipes.stream()
                .map(recipe -> new RecipeDto(recipe.getId(), recipe.getTitle(), TextHelper.editText(recipe.getText()),
                        recipe.getPhoto(), recipe.getData(), recipe.getUserId(), recipe.getUserNickname()))
                .collect(Collectors.toList());

        session.setAttribute("myRecipes", allRecipes);
        session.removeAttribute("isMyRecipe");

        return "myRecipes";
    }

    @GetMapping("/deleteRecipe/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") Integer recipeId) {
        recipeService.deleteRecipeById(recipeId);

        return "redirect:/myRecipes";
    }

    @GetMapping("/detailRecipe/{recipeId}/{isMyRecipes}")
    public String getDetailRecipe(@PathVariable("recipeId") Integer recipeId,
                                  @PathVariable("isMyRecipes") Integer isMyRecipe, HttpSession session) {

        RecipeDto recipe = recipeService.getRecipeById(recipeId);
        UserDto author = userService.getUserById((recipe.getUserId()));
        List<RecipeCommentDto> comments = recipeCommentService.getAllByRecipeId(recipe.getId());

        if (isMyRecipe == 1) {
            session.setAttribute("isMyRecipe", true);
        }

        if (comments.size() != 0) {
            session.setAttribute("recipeComments", comments);
        }

        session.setAttribute("author", author);
        session.setAttribute("detailRecipe", recipe);

        return "detailRecipe";
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
