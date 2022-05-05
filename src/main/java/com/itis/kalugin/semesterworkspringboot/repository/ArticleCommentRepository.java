package com.itis.kalugin.semesterworkspringboot.repository;

import com.itis.kalugin.semesterworkspringboot.model.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {

    @Query(value = "select u from ArticleComment u where u.article.id = :articleId")
    List<ArticleComment> getAllByArticleId(@Param("articleId") Integer articleId);
}
