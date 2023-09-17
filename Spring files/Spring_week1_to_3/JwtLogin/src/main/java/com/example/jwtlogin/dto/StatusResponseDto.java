package com.example.jwtlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class StatusResponseDto {
    private String status;
    private String msg;

}
