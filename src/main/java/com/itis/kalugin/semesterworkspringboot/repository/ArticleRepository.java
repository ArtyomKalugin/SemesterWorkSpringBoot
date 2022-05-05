package com.itis.kalugin.semesterworkspringboot.repository;

import com.itis.kalugin.semesterworkspringboot.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> getAllByTitleContaining(String title);

    @Query(value = "select u from Article u where u.user.id = :userId")
    List<Article> getAllByUserId(@Param("userId") Integer userId);

    void deleteArticleById(Integer id);
}
