package me.woosuyeon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.woosuyeon.springbootdeveloper.domain.Article;
import me.woosuyeon.springbootdeveloper.dto.AddArticleRequest;
import me.woosuyeon.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor    // final이나 @Notnull이 붙은 필드의 생성자 추가 (반드시 입력해야 하는 필드를 받는 생성자)
@Service    // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    // 실제로 블로그에 글을 추가하는 메소드이다.
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // DB에 저장된 모든 글 목록을 가져오는 메소드이다.
    public List<Article> findAll() { return blogRepository.findAll(); }
}
