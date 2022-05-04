package com.itis.kalugin.semesterworkspringboot.controller;

import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.model.Recipe;
import com.itis.kalugin.semesterworkspringboot.model.RecipeComment;
import com.itis.kalugin.semesterworkspringboot.model.User;
import com.itis.kalugin.semesterworkspringboot.service.inter.RecipeCommentService;
import com.itis.kalugin.semesterworkspringboot.service.inter.RecipeService;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RecipeCommentController {

    private final RecipeCommentService recipeCommentService;
    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public RecipeCommentController(RecipeCommentService recipeCommentService, RecipeService recipeService,
                                   UserService userService) {
        this.recipeCommentService = recipeCommentService;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @PostMapping("/createRecipeComment/{recipeId}/{isMyRecipes}")
    public String saveRecipeComment(@RequestParam("comment") String comment,
                                    @PathVariable("recipeId") Integer recipeId,
                                    @PathVariable("isMyRecipes") Integer isMyRecipe, HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("user");
        User user = userService.getRawUserByEmail(userDto.getEmail());
        Recipe recipe = recipeService.getRawRecipeById(recipeId);

        RecipeComment recipeComment = new RecipeComment(comment, user, recipe);
        recipeCommentService.save(recipeComment);

        return "redirect:/detailRecipe/" + recipeId + "/" + isMyRecipe;
    }
}
