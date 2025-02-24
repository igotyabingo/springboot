package me.woosuyeon.springbootdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.woosuyeon.springbootdeveloper.config.TokenProvider;
import me.woosuyeon.springbootdeveloper.domain.User;
import me.woosuyeon.springbootdeveloper.dto.AddUserRequest;
import me.woosuyeon.springbootdeveloper.dto.LogInRequest;
import me.woosuyeon.springbootdeveloper.service.RefreshTokenService;
import me.woosuyeon.springbootdeveloper.service.TokenService;
import me.woosuyeon.springbootdeveloper.service.UserService;
import me.woosuyeon.springbootdeveloper.util.CookieUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@RequiredArgsConstructor
@Controller
public class UserApiController {
    private final UserService userService;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;
    private final TokenProvider tokenProvider;

    @PostMapping("/user")
    public String signup(AddUserRequest addUserRequest) {
        userService.save(addUserRequest);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    // 로그인을 담당하는 메소드 생성:
    @PostMapping("/login")
    public String login(LogInRequest request, HttpServletResponse response) {
        // 패스에 액세스 토큰을 추가하고, 리프레시 토큰을 쿠키에 담아 응답으로 전달한다.
        // 패스로 리디렉트 한다.
        User user = userService.validateUser(request.getUsername(), request.getPassword());

        String accessToken = tokenProvider.generateToken(user, Duration.ofDays(1));
        String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(14));

        CookieUtil.deleteCookie(response, "refresh_token");
        CookieUtil.addCookie(response, "refresh_token", refreshToken, (int)Duration.ofDays(14).toSeconds());
        refreshTokenService.save(user.getId(), refreshToken);

        String path = UriComponentsBuilder.fromUriString("/articles")
                .queryParam("token", accessToken)
                .build()
                .toUriString();

        return "redirect:"+path;
    }

}
