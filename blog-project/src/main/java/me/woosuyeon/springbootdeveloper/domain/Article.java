package me.woosuyeon.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 엔티티(DB 테이블과 매핑)에 해당하는 클래스이다.
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키에 auto_increment 옵션을 설정한 것임
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder    // 빌더 패턴으로 객체를 생성할 수 있게 함
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
