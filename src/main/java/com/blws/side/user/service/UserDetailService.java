package com.blws.side.user.service;

import com.blws.side.common.exception.CustomException;
import com.blws.side.user.entity.User;
import com.blws.side.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.BAD_REQUEST));
    }

}
