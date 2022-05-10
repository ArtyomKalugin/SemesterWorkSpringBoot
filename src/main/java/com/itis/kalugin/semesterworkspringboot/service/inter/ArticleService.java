package com.itis.kalugin.semesterworkspringboot.service.inter;

import com.itis.kalugin.semesterworkspringboot.dto.ArticleDto;
import com.itis.kalugin.semesterworkspringboot.model.Article;

import java.util.List;

public interface ArticleService {

    ArticleDto save(Article article);

    ArticleDto getArticleById(int articleId);

    Article getRawArticleById(int articleId);

    List<ArticleDto> getAll();

    List<ArticleDto> getArticlesByTitleLike(String title);

    List<ArticleDto> getArticlesByTitleLikeAndUserId(String title, Integer id);

    List<ArticleDto> getAllByUserId(int userId);

    void deleteArticleById(int id);
}
