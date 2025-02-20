package me.woosuyeon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.woosuyeon.springbootdeveloper.config.jwt.TokenProvider;
import me.woosuyeon.springbootdeveloper.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        // 액세스 토큰을 재발급 해달라는 요청을 리프레시 토큰과 함께 보내왔을 때
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
