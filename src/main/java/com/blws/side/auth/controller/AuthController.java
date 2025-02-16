package com.blws.side.auth.controller;

import com.blws.side.auth.dto.*;
import com.blws.side.auth.service.AuthService;
import com.blws.side.user.dto.RequestCreateUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseCreateTokenDTO signup(@RequestBody RequestSignInDTO requestSignInDTO) {
        return ResponseCreateTokenDTO.of(authService.signin(requestSignInDTO.getUsername(), requestSignInDTO.getPassword()));
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCreateUserDTO signup(@RequestBody RequestCreateUserDTO requestCreateUserDTO) {
        return ResponseCreateUserDTO.of(authService.signup(requestCreateUserDTO.toEntity()));
    }

    @GetMapping("/signout")
    public void signout() {

    }

    @PostMapping("/token")
    public ResponseCreateNewAccessTokenDTO createNewAccessToken(@RequestBody RequestCreateAccessTokenDTO requestDto) {
        String newAccessToken = authService.createNewAccessToken(requestDto.getRefreshToken());

        return new ResponseCreateNewAccessTokenDTO(newAccessToken);
    }

}
