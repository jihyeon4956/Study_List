package com.example.jwtlogin.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String password;
    private String nickname;

}
