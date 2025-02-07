package me.woosuyeon.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.woosuyeon.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity() {
        // DTO -> entity로 변환한다.
        return Article.builder().title(title).content(content).build();
    }
}
