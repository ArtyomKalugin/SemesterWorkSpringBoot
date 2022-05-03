package com.itis.kalugin.semesterworkspringboot.repository;

import com.itis.kalugin.semesterworkspringboot.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> getAllByTitleContaining(String title);

    @Query(value = "select u from Recipe u where u.user.id = :userId")
    List<Recipe> getAllByUserId(@Param("userId") Integer userId);

    void deleteRecipeById(Integer id);
}
