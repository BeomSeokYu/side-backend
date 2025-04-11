package com.blws.side.config.jwt;

import com.blws.side.user.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;

    /**
     * JWT 생성 메서드
     * @param user          UserDetails User
     * @param tokenType     ACCEESS, REFRESH
     * @return              JWT
     */
    public String generateToken(User user, TokenType tokenType) {
        Date now = new Date();
        return makeToken(tokenType.compareTo(TokenType.ACCESS) == 0
                ? new Date(now.getTime() + Duration.ofMinutes(jwtProperties.getAccessExpirationTime()).toMillis())
                : new Date(now.getTime() + Duration.ofDays(jwtProperties.getRefreshExpirationTime()).toMillis())
                , user);
    }

    private String makeToken(Date expiry, User user) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)   // 헤더 typ: JWT
                .setIssuer(jwtProperties.getIssuer())           // issuer
                .setIssuedAt(now)                               // 내용 iat: 현재 시간
                .setExpiration(expiry)                          // 내용 exp: expiry 멤버 변숫값
                .setSubject(user.getEmail())                 // 내용 sub: 유저 username
                .claim("id", user.getUserId())                   // 클래임 id: 유저 ID
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * JWT 토큰 유효성 검증 메서드
     * @param token JWT
     * @return      파싱 성공 true, 실패 CustomException
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // HMAC 키 사용
    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtProperties.getSecretKey().getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 토큰 기반으로 인증 정보를 가져오는 메서드
     * @param token JWT
     * @return      Authentication
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("USER_ROLE"));
        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities), token, authorities);
    }

    /**
     * 토큰 기반으로 유저 ID를 가져오는 메서드
     * @param token jwt
     * @return      username
     */
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    /**
     * 토큰 기반으로 토큰의 만료시간을 가져오는 메서드
     * @param token jwt
     * @return      Expiration
     */
    public LocalDateTime getExpiryDate(String token) {
        Claims claims = getClaims(token);
        return LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
               .setSigningKey(getSigningKey())
               .build()
               .parseClaimsJws(token)
               .getBody();
    }

}
