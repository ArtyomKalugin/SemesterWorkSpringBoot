package com.itis.kalugin.semesterworkspringboot.controller;

import com.itis.kalugin.semesterworkspringboot.dto.RecipeDto;
import com.itis.kalugin.semesterworkspringboot.service.inter.RecipeService;
import com.itis.kalugin.semesterworkspringboot.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class RecipeRestController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeRestController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/allRecipes/{title}")
    public List<RecipeDto> getAllRecipes(@PathVariable("title") String title) {

        List<RecipeDto> recipes = recipeService.getRecipesByTitleLike(title);
        Collections.reverse(recipes);

        return recipes;
    }
}
