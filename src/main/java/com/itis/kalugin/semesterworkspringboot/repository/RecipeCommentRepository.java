package com.itis.kalugin.semesterworkspringboot.repository;

import com.itis.kalugin.semesterworkspringboot.model.RecipeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecipeCommentRepository extends JpaRepository<RecipeComment, Integer> {

    @Query(value = "select u from RecipeComment u where u.recipe.id = :recipeId")
    List<RecipeComment> getAllByRecipeId(@Param("recipeId") Integer recipeId);
}
