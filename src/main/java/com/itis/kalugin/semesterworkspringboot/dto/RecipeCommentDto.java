package com.itis.kalugin.semesterworkspringboot.dto;

import com.itis.kalugin.semesterworkspringboot.model.RecipeComment;
import com.itis.kalugin.semesterworkspringboot.model.User;


public class RecipeCommentDto {

    private int id;
    private String text;
    private User user;
    private int recipeId;

    public RecipeCommentDto(int id, String text, User user, int recipeId) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public static RecipeCommentDto fromModel(RecipeComment comment) {
        return new RecipeCommentDto(comment.getId(), comment.getText(), comment.getUser(), comment.getRecipe().getId());
    }
}
