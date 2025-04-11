package com.blws.side.user.dto;

import com.blws.side.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestCreateUserDTO {

    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .email(this.username)
                .password(this.password)
                .build();
    }
}
