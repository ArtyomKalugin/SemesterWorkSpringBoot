package com.itis.kalugin.semesterworkspringboot.service.impl;

import com.itis.kalugin.semesterworkspringboot.dto.RecipeCommentDto;
import com.itis.kalugin.semesterworkspringboot.model.RecipeComment;
import com.itis.kalugin.semesterworkspringboot.repository.RecipeCommentRepository;
import com.itis.kalugin.semesterworkspringboot.service.inter.RecipeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeCommentServiceImpl implements RecipeCommentService {

    private final RecipeCommentRepository recipeCommentRepository;

    @Autowired
    public RecipeCommentServiceImpl(RecipeCommentRepository recipeCommentRepository) {
        this.recipeCommentRepository = recipeCommentRepository;
    }

    @Override
    public RecipeCommentDto save(RecipeComment comment) {
        return RecipeCommentDto.fromModel(recipeCommentRepository.save(comment));
    }

    @Override
    public List<RecipeCommentDto> getAllByRecipeId(int recipeId) {
        List<RecipeComment> allComments = recipeCommentRepository.getAllByRecipeId(recipeId);

        return allComments.stream()
                .map(RecipeCommentDto::fromModel)
                .collect(Collectors.toList());
    }
}
