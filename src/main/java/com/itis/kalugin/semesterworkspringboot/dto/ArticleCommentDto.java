package com.itis.kalugin.semesterworkspringboot.dto;

import com.itis.kalugin.semesterworkspringboot.model.ArticleComment;
import com.itis.kalugin.semesterworkspringboot.model.RecipeComment;
import com.itis.kalugin.semesterworkspringboot.model.User;

public class ArticleCommentDto {

    private int id;
    private String text;
    private User user;
    private int articleId;

    public ArticleCommentDto(int id, String text, User user, int articleId) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.articleId = articleId;
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

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public static ArticleCommentDto fromModel(ArticleComment comment) {
        return new ArticleCommentDto(comment.getId(), comment.getText(), comment.getUser(),
                comment.getArticle().getId());
    }
}
