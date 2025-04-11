package com.blws.side.user.service;

import com.blws.side.auth.entity.RefreshToken;
import com.blws.side.auth.repository.RefreshTokenRepository;
import com.blws.side.common.exception.CustomException;
import com.blws.side.user.entity.User;
import com.blws.side.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User createUser(User userInfo) {
        User user = User.builder()
                .email(userInfo.getEmail())
                .password(bCryptPasswordEncoder.encode(userInfo.getPassword()))
                .email(userInfo.getEmail())
                .build();
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.BAD_REQUEST));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.BAD_REQUEST));
    }

    public User getUserByRefreshToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken).orElse(null);
        if (token != null) {
            return userRepository.findByUserId(token.getId())
                    .orElseThrow(() -> new CustomException("User not found", HttpStatus.BAD_REQUEST));
        }

        return null;
    }

    @Transactional
    public void updateUser(Long id, User user) {
        User target = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.BAD_REQUEST));
        userRepository.save(target.update(user));
    }

}
