package com.blws.side.auth.dto;

import com.blws.side.user.entity.User;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ResponseCreateUserDTO {
    private Long id;
    private String username;

    public static ResponseCreateUserDTO of(User user) {
        return builder()
                .id(user.getUserId())
                .username(user.getEmail())
                .build();
    }
}
