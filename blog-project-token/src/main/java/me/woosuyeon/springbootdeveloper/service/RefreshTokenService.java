package me.woosuyeon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.woosuyeon.springbootdeveloper.config.TokenProvider;
import me.woosuyeon.springbootdeveloper.domain.RefreshToken;
import me.woosuyeon.springbootdeveloper.domain.User;
import me.woosuyeon.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;


@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
    public RefreshToken createRefreshToken(User user) {
        return refreshTokenRepository.save(new RefreshToken(user.getId(), tokenProvider.generateToken(user, Duration.ofHours(10))));
    }
}
