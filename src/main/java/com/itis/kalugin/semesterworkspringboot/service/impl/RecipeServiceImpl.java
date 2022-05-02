package com.itis.kalugin.semesterworkspringboot.service.impl;

import com.itis.kalugin.semesterworkspringboot.dto.RecipeDto;
import com.itis.kalugin.semesterworkspringboot.model.Recipe;
import com.itis.kalugin.semesterworkspringboot.repository.RecipeRepository;
import com.itis.kalugin.semesterworkspringboot.service.inter.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public List<RecipeDto> getAll() {
        List<Recipe> allRecipes = recipeRepository.findAll();

        return allRecipes.stream()
                .map(RecipeDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> getRecipesByTitleLike(String title) {
        List<Recipe> allRecipes = recipeRepository.getAllByTitleContaining(title);

        return allRecipes.stream()
                .map(RecipeDto::fromModel)
                .collect(Collectors.toList());
    }
}
