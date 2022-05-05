package com.itis.kalugin.semesterworkspringboot.controller;

import com.itis.kalugin.semesterworkspringboot.dto.UserDto;
import com.itis.kalugin.semesterworkspringboot.model.*;
import com.itis.kalugin.semesterworkspringboot.service.inter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;
    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleCommentController(ArticleCommentService articleCommentService, ArticleService articleService,
                                   UserService userService) {
        this.articleCommentService = articleCommentService;
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping("/createArticleComment/{articleId}/{isMyArticle}")
    public String saveArticleComment(@RequestParam("comment") String comment,
                                    @PathVariable("articleId") Integer articleId,
                                    @PathVariable("isMyArticle") Integer isMyArticle, HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("user");
        User user = userService.getRawUserByEmail(userDto.getEmail());
        Article article = articleService.getRawArticleById(articleId);

        ArticleComment articleComment = new ArticleComment(comment, user, article);
        articleCommentService.save(articleComment);

        return "redirect:/detailArticle/" + articleId + "/" + isMyArticle;
    }
}
