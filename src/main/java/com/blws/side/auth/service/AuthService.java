package com.blws.side.auth.service;

import com.blws.side.common.exception.CustomException;
import com.blws.side.config.jwt.TokenProvider;
import com.blws.side.config.jwt.TokenType;
import com.blws.side.user.entity.User;
import com.blws.side.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public String createNewAccessToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new CustomException("Unexpected token", HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserByRefreshToken(refreshToken);
        if (!((UserDetails) SecurityContextHolder.getContext()).getUsername().equals(user.getUsername())) {
            throw new CustomException("Access Denied", HttpStatus.BAD_REQUEST);
        }

        return tokenProvider.generateToken(user, TokenType.ACCESS);
    }

    @Transactional
    public Map<TokenType, String> signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User authUser = userService.getUserByUsername(username);
            String refreshToken = tokenProvider.generateToken(authUser, TokenType.REFRESH);
            String accessToken = tokenProvider.generateToken(authUser, TokenType.ACCESS);

            userService.updateUser(authUser.getId(), authUser.updateRefreshToken(refreshToken));

            return new HashMap<>() {
                {
                    put(TokenType.ACCESS, accessToken);
                    put(TokenType.REFRESH, refreshToken);
                }
            };
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @Transactional
    public User signup(User user) {
        return userService.createUser(user);
    }

}
