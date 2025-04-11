package com.blws.side.auth.service;

import com.blws.side.auth.domain.UserDetailsImpl;

import com.blws.side.user.entity.User;
import com.blws.side.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(Username)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found: " + Username));
        return new UserDetailsImpl(user);
    }
}
