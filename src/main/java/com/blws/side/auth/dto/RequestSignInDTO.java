package com.blws.side.auth.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestSignInDTO {
    private String username;
    private String password;
}
