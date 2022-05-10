package com.itis.kalugin.semesterworkspringboot.model;

import javax.persistence.*;

@Entity
@Table(name = "recipe_comment")
public class RecipeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String text;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeComment(String text, User user, Recipe recipe) {
        this.text = text;
        this.user = user;
        this.recipe = recipe;
    }

    public RecipeComment() {
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
