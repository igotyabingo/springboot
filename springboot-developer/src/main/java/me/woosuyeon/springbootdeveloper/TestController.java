package me.woosuyeon.springbootdeveloper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")    // 사용자의 /test GET요청에 응답하는 메소드임을 나타내는 어노테이션
    public String test() {
        return "Hello, World!";
    }
}
