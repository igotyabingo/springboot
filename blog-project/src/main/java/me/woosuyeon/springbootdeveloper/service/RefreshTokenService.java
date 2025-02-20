package me.woosuyeon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.woosuyeon.springbootdeveloper.domain.RefreshToken;
import me.woosuyeon.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new IllegalArgumentException("Unexpected Token"));
    }
}
