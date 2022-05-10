package com.itis.kalugin.semesterworkspringboot.service.impl;

import com.itis.kalugin.semesterworkspringboot.dto.RecipeDto;
import com.itis.kalugin.semesterworkspringboot.model.Recipe;
import com.itis.kalugin.semesterworkspringboot.repository.RecipeRepository;
import com.itis.kalugin.semesterworkspringboot.service.inter.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeDto save(Recipe recipe) {
        return RecipeDto.fromModel(recipeRepository.save(recipe));
    }

    @Override
    public RecipeDto getRecipeById(int recipeId) {
        return RecipeDto.fromModel(recipeRepository.findById(recipeId).get());
    }

    @Override
    public Recipe getRawRecipeById(int recipeId) {
        return recipeRepository.findById(recipeId).get();
    }

    @Override
    public List<RecipeDto> getAll() {
        List<Recipe> allRecipes = recipeRepository.findAll();

        return allRecipes.stream()
                .map(RecipeDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> getRecipesByTitleLike(String title) {
        List<Recipe> allRecipes;

        if (title.isEmpty() || title == null) {
            allRecipes = recipeRepository.findAll();
        } else {
            allRecipes = recipeRepository.getAllByTitleContains(title);
        }

        return allRecipes.stream()
                .map(RecipeDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> getRecipesByTitleLikeAndUserId(String title, Integer id) {
        List<Recipe> allRecipes;

        if (title.isEmpty() || title == null) {
            allRecipes = recipeRepository.getAllByUserId(id);
        } else {
            allRecipes = recipeRepository.getAllByTitleContainsAndUserId(title, id);
        }

        return allRecipes.stream()
                .map(RecipeDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> getAllByUserId(int userId) {
        List<Recipe> allRecipes = recipeRepository.getAllByUserId(userId);

        return allRecipes.stream()
                .map(RecipeDto::fromModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteRecipeById(int id) {
        recipeRepository.deleteRecipeById(id);
    }
}
