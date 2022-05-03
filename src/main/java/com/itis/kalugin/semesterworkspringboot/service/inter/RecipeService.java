package com.itis.kalugin.semesterworkspringboot.service.inter;

import com.itis.kalugin.semesterworkspringboot.dto.RecipeDto;
import com.itis.kalugin.semesterworkspringboot.model.Recipe;
import java.util.List;

public interface RecipeService {

    RecipeDto save(Recipe recipe);

    RecipeDto getRecipeById(int recipeId);

    List<RecipeDto> getAll();

    List<RecipeDto> getRecipesByTitleLike(String title);

    List<RecipeDto> getAllByUserId(int userId);

    void deleteRecipeById(int id);
}
