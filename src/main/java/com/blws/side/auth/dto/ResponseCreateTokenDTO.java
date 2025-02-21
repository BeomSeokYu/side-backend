package com.blws.side.auth.dto;

import com.blws.side.config.jwt.TokenType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ResponseCreateTokenDTO {
    private String accessToken;
    private String refreshToken;

    public static ResponseCreateTokenDTO of(Map<TokenType, String> tokens) {
        return builder()
                .accessToken(tokens.get(TokenType.ACCESS))
                .refreshToken(tokens.get(TokenType.REFRESH))
                .build();
    }
}
