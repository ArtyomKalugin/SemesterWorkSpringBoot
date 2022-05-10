package com.itis.kalugin.semesterworkspringboot.service.inter;

import com.itis.kalugin.semesterworkspringboot.dto.RecipeDto;
import com.itis.kalugin.semesterworkspringboot.model.Recipe;
import java.util.List;

public interface RecipeService {

    RecipeDto save(Recipe recipe);

    RecipeDto getRecipeById(int recipeId);

    Recipe getRawRecipeById(int recipeId);

    List<RecipeDto> getAll();

    List<RecipeDto> getRecipesByTitleLike(String title);

    List<RecipeDto> getAllByUserId(int userId);

    List<RecipeDto> getRecipesByTitleLikeAndUserId(String title, Integer id);

    void deleteRecipeById(int id);
}
