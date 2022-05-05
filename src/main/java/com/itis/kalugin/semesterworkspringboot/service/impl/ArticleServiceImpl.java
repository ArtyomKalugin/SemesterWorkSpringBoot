package com.itis.kalugin.semesterworkspringboot.service.impl;

import com.itis.kalugin.semesterworkspringboot.dto.ArticleDto;
import com.itis.kalugin.semesterworkspringboot.model.Article;
import com.itis.kalugin.semesterworkspringboot.repository.ArticleRepository;
import com.itis.kalugin.semesterworkspringboot.service.inter.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(Article article) {
        return ArticleDto.fromModel(articleRepository.save(article));
    }

    @Override
    public ArticleDto getArticleById(int articleId) {
        return ArticleDto.fromModel(articleRepository.findById(articleId).get());
    }

    @Override
    public Article getRawArticleById(int articleId) {
        return articleRepository.findById(articleId).get();
    }

    @Override
    public List<ArticleDto> getAll() {
        List<Article> allArticles = articleRepository.findAll();

        return allArticles.stream()
                .map(ArticleDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> getArticlesByTitleLike(String title) {
        List<Article> allArticles = articleRepository.getAllByTitleContaining(title);

        return allArticles.stream()
                .map(ArticleDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> getAllByUserId(int userId) {
        List<Article> allArticles = articleRepository.getAllByUserId(userId);

        return allArticles.stream()
                .map(ArticleDto::fromModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteArticleById(int id) {
        articleRepository.deleteArticleById(id);
    }
}
