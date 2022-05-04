package com.itis.kalugin.semesterworkspringboot.service.inter;

import com.itis.kalugin.semesterworkspringboot.dto.RecipeCommentDto;
import com.itis.kalugin.semesterworkspringboot.model.RecipeComment;

import java.util.List;

public interface RecipeCommentService {

    RecipeCommentDto save(RecipeComment comment);

    List<RecipeCommentDto> getAllByRecipeId(int recipeId);
}
