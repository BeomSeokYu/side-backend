package com.blws.side.config.jwt;

import com.blws.side.user.entity.User;
import com.blws.side.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TokenProviderTest {
    @Autowired private TokenProvider tokenProvider;
    @Autowired private UserRepository userRepository;
    @Autowired private JwtProperties jwtProperties;

    @Test
    @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달해 토큰을 생성")
    void generateToken() {
        // given
        User testUser = userRepository.save(User.builder()
                .email("user@email.com")
                .password("test")
                .nickname("tester")
                .build());
        // when
        String token = tokenProvider.generateToken(testUser, TokenType.ACCESS);
        // then
        Long userId = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getUserId());
    }

    @Test
    @DisplayName("validateToken_invalid(): 만료된 토큰일 때에 유효성 검증에 실패")
    void validateToken_invalid() {
        // given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofMinutes(jwtProperties.getAccessExpirationTime()).toMillis()))
                .build().createToken(jwtProperties);
        // when
        boolean result = tokenProvider.validateToken(token);
        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("validateToken_valid(): 유효한 토큰일 때에 유효성 검증에 성공")
    void validateToken_valid() {
        // given
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);
        // when
        boolean result = tokenProvider.validateToken(token);
        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("getAuthentication(): 토큰 기반으로 인증 정보 가져오기")
    void getAuthentication() {
        // given
        String username = "user";
        String token = JwtFactory.builder()
                .subject(username)
                .build()
                .createToken(jwtProperties);
        // when
        Authentication authentication = tokenProvider.getAuthentication(token);
        // then
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(username);
    }

    @Test
    @DisplayName("getUserId(): 토큰으로 유저 ID 가져오기")
    void getUserId() {
        // given
        Long id = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", id))
                .build()
                .createToken(jwtProperties);
        // when
        Long userIdByToken = tokenProvider.getUserId(token);
        // then
        assertThat(userIdByToken).isEqualTo(id);
    }
}