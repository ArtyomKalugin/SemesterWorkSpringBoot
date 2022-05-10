package com.itis.kalugin.semesterworkspringboot.controller;

import com.cloudinary.utils.ObjectUtils;
import com.itis.kalugin.semesterworkspringboot.dto.*;
import com.itis.kalugin.semesterworkspringboot.helper.CloudinaryHelper;
import com.itis.kalugin.semesterworkspringboot.helper.ImageHelper;
import com.itis.kalugin.semesterworkspringboot.helper.TextHelper;
import com.itis.kalugin.semesterworkspringboot.model.Article;
import com.itis.kalugin.semesterworkspringboot.model.User;
import com.itis.kalugin.semesterworkspringboot.service.inter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final ArticleCommentService articleCommentService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService,
                             ArticleCommentService articleCommentService) {
        this.articleService = articleService;
        this.userService = userService;
        this.articleCommentService = articleCommentService;
    }


    @GetMapping("/allArticles")
    public String getAllArticles(HttpSession session) {

        List<ArticleDto> allArticles = articleService.getAll();
        Collections.reverse(allArticles);

        allArticles = allArticles.stream()
                .map(article -> new ArticleDto(article.getId(), article.getTitle(), TextHelper.editText(article.getText()),
                        article.getPhoto(), article.getData(), article.getUserId(), article.getUserNickname()))
                .collect(Collectors.toList());

        session.setAttribute("allArticles", allArticles);
        session.removeAttribute("isMyArticle");

        return "allArticles";
    }

    @GetMapping("/allFindArticles")
    @ResponseBody
    public List<ArticleDto> getAllArticlesByTitle(@RequestParam(value = "title",required = false) String title) {
        List<ArticleDto> articles = articleService.getArticlesByTitleLike(title);
        articles = articles.stream()
                .peek(article -> article.setText(TextHelper.editText(article.getText())))
                .collect(Collectors.toList());

        Collections.reverse(articles);

        return articles;
    }

    @GetMapping("/allMyFindArticles")
    @ResponseBody
    public List<ArticleDto> getAllMyArticlesByTitle(@RequestParam(value = "title",required = false) String title,
                                                  HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        List<ArticleDto> articles = articleService.getArticlesByTitleLikeAndUserId(title, user.getId());

        articles = articles.stream()
                .peek(article -> article.setText(TextHelper.editText(article.getText())))
                .collect(Collectors.toList());

        Collections.reverse(articles);

        return articles;
    }

    @GetMapping("/myArticles")
    public String getMyArticles(HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("user");
        List<ArticleDto> allArticles = articleService.getAllByUserId(user.getId());
        Collections.reverse(allArticles);

        allArticles = allArticles.stream()
                .map(article -> new ArticleDto(article.getId(), article.getTitle(), TextHelper.editText(article.getText()),
                        article.getPhoto(), article.getData(), article.getUserId(), article.getUserNickname()))
                .collect(Collectors.toList());

        session.setAttribute("myArticles", allArticles);
        session.removeAttribute("isMyArticle");

        return "myArticles";
    }

    @GetMapping("/deleteArticle/{articleId}")
    public String deleteArticle(@PathVariable("articleId") Integer articleId) {
        articleService.deleteArticleById(articleId);

        return "redirect:/myArticles";
    }

    @GetMapping("/detailArticle/{articleId}/{isMyArticle}")
    public String getDetailArticle(@PathVariable("articleId") Integer articleId,
                                  @PathVariable("isMyArticle") Integer isMyArticle, HttpSession session) {

        ArticleDto article = articleService.getArticleById(articleId);
        UserDto author = userService.getUserById((article.getUserId()));
        List<ArticleCommentDto> comments = articleCommentService.getAllByArticleId(article.getId());

        if (isMyArticle == 1) {
            session.setAttribute("isMyArticle", true);
        }

        if (comments.size() != 0) {
            session.setAttribute("articleComments", comments);
        }

        session.setAttribute("author", author);
        session.setAttribute("detailArticle", article);

        return "detailArticle";
    }

    @GetMapping("/createArticle")
    public String createArticle() {

        return "addArticle";
    }

    @PostMapping("/createArticle")
    public String saveArticle(@RequestParam("title") String title, @RequestParam("content") String content,
                             @RequestParam("photo") MultipartFile photo, HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("user");
        User user = userService.getRawUserByEmail(userDto.getEmail());

        if (title.isEmpty() || content.isEmpty() || photo.isEmpty()) {

            return "failureAddArticle";
        }

        try {
            File recipePhoto = ImageHelper.makeFile(photo);
            String filename = "articlePhoto" + userDto.getId();

            Map upload = CloudinaryHelper.getInstance().uploader()
                    .upload(recipePhoto, ObjectUtils.asMap("public_id", filename));
            String url = (String) upload.get("url");

            Date date = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");

            Article article = new Article(title, content, url, formatForDateNow.format(date), user);

            articleService.save(article);

            recipePhoto.delete();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


        return "redirect:/account";
    }
}
