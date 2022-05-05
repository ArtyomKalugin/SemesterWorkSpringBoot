package com.itis.kalugin.semesterworkspringboot.service.inter;

import com.itis.kalugin.semesterworkspringboot.dto.ArticleCommentDto;
import com.itis.kalugin.semesterworkspringboot.model.ArticleComment;

import java.util.List;

public interface ArticleCommentService {

    ArticleCommentDto save(ArticleComment comment);

    List<ArticleCommentDto> getAllByArticleId(int articleId);
}
