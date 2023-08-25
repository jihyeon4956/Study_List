package com.sparta.springauth.controller;

import com.sparta.springauth.dto.SignupRequestDto;
import com.sparta.springauth.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userSerive;

    public UserController(UserService userSerive) {
        this.userSerive = userSerive;
    }


    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")  // 회원가입 데이터를 받아와야함, @ModelAttribute 생략
    public  String signupPage(SignupRequestDto requestDto){
        userSerive.signup(requestDto);
        return "redirect:/api/user/login-page";
    }
}