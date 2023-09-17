package com.example.jwtlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class HomeComtroller {


    /** 단순조회 컨트롤러
     *  signupForm: 회원가입 폼 페이지
     *  login: 로그인 폼 패이지
     *  mypage: 마이페이지 폼 패이지
     * @return html 페이지
     */

    @GetMapping("signupForm")
    public String signupPage() {
        return "signupForm";
    }
    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("mypage")
    public String mypage() {
        return "mypage";
    }

}

