package me.woosuyeon.springbootdeveloper.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import me.woosuyeon.springbootdeveloper.dto.ArticleListViewResponse;
import me.woosuyeon.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


}
