package me.woosuyeon.springbootdeveloper.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import me.woosuyeon.springbootdeveloper.domain.Article;
import me.woosuyeon.springbootdeveloper.dto.ArticleListViewResponse;
import me.woosuyeon.springbootdeveloper.dto.ArticleViewResponse;
import me.woosuyeon.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles", articles);

        // 생성한 모델 객체를 "가지고" articleList.html로 간다.
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }


}
