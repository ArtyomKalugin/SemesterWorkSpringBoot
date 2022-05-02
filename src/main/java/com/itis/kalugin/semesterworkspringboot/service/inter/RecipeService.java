package com.itis.kalugin.semesterworkspringboot.service.inter;

import com.itis.kalugin.semesterworkspringboot.dto.RecipeDto;
import com.itis.kalugin.semesterworkspringboot.model.Recipe;
import java.util.List;

public interface RecipeService {

    RecipeDto save(Recipe recipe);

    List<RecipeDto> getAll();

    List<RecipeDto> getRecipesByTitleLike(String title);
}
