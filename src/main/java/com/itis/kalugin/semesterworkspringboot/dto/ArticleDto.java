package com.itis.kalugin.semesterworkspringboot.dto;

import com.itis.kalugin.semesterworkspringboot.model.Article;

public class ArticleDto {

    private int id;
    private String title;
    private String text;
    private String photo;
    private String data;
    private int userId;
    private String userNickname;

    public ArticleDto(int id, String title, String text, String photo, String data, int userId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.photo = photo;
        this.data = data;
        this.userId = userId;
    }

    public ArticleDto(int id, String title, String text, String photo, String data, int userId, String userNickname) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.photo = photo;
        this.data = data;
        this.userId = userId;
        this.userNickname = userNickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public static ArticleDto fromModel(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getText(), article.getPhoto(),
                article.getData(), article.getUser().getId(), article.getUser().getNickname());
    }
}
