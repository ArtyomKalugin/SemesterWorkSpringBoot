package com.itis.kalugin.semesterworkspringboot.service.impl;

import com.itis.kalugin.semesterworkspringboot.dto.ArticleCommentDto;
import com.itis.kalugin.semesterworkspringboot.model.ArticleComment;
import com.itis.kalugin.semesterworkspringboot.repository.ArticleCommentRepository;
import com.itis.kalugin.semesterworkspringboot.service.inter.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;

    @Autowired
    public ArticleCommentServiceImpl(ArticleCommentRepository articleCommentRepository) {
        this.articleCommentRepository = articleCommentRepository;
    }

    @Override
    public ArticleCommentDto save(ArticleComment comment) {
        return ArticleCommentDto.fromModel(articleCommentRepository.save(comment));
    }

    @Override
    public List<ArticleCommentDto> getAllByArticleId(int articleId) {
        List<ArticleComment> allComments = articleCommentRepository.getAllByArticleId(articleId);

        return allComments.stream()
                .map(ArticleCommentDto::fromModel)
                .collect(Collectors.toList());
    }
}
