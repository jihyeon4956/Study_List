package com.example.jwtlogin.controller;

import com.example.jwtlogin.dto.LoginRequestDto;
import com.example.jwtlogin.dto.SignupRequestDto;
import com.example.jwtlogin.dto.StatusResponseDto;
import com.example.jwtlogin.dto.UserUpdateRequestDto;
import com.example.jwtlogin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public StatusResponseDto signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {

        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) { // 에러가 있을 경우
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getField() + " 필드: " + fieldError.getDefaultMessage());
            }
            return new StatusResponseDto(String.valueOf(HttpStatus.BAD_REQUEST), "회원가입에 실패했습니다");
        }
        return userService.signup(requestDto);
    }

    // 로그인
    @PostMapping("/login")
    public StatusResponseDto login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        return userService.login(requestDto, res);

    }

    // 로그아웃
    @PostMapping("/logout")
    public StatusResponseDto logout(HttpServletRequest req, HttpServletResponse res) {
        return userService.logout(req, res);
    }

    // 유저 정보 수정
    @PutMapping("/update/{userId}")
    public StatusResponseDto update(@PathVariable Long userId,
                                    @RequestBody UserUpdateRequestDto requestDto,
                                    HttpServletRequest req) {
        return userService.updateUser(userId, requestDto, req);
    }

    // 회원 탈퇴
    @DeleteMapping("/delete/{userId}")
    public StatusResponseDto delete(@PathVariable Long userId, HttpServletRequest req, HttpServletResponse res) {
        return userService.deleteUser(userId, req, res);
    }
}

