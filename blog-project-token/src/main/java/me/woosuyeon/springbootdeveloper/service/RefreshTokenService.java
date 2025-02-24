package me.woosuyeon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.woosuyeon.springbootdeveloper.config.TokenProvider;
import me.woosuyeon.springbootdeveloper.domain.RefreshToken;
import me.woosuyeon.springbootdeveloper.domain.User;
import me.woosuyeon.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void delete() {
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Long userId = tokenProvider.getUserId(token);
        refreshTokenRepository.deleteByUserId(userId);
    }

    public void save(Long userId, String token) {
        refreshTokenRepository.save(new RefreshToken(userId, token));
    }
}
