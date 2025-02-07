package me.woosuyeon.springbootdeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import me.woosuyeon.springbootdeveloper.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
