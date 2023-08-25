package com.sparta.springauth.controller;

import com.sparta.springauth.dto.LoginRequestDto;
import com.sparta.springauth.dto.SignupRequestDto;
import com.sparta.springauth.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
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
    public String signupPage(SignupRequestDto requestDto) {
        userSerive.signup(requestDto);

        return "redirect:/api/user/login-page";
    }

    // 로그인히고 인증에 성공하면 쿠키에 토큰 담아서 주기
    // 로그인하는 API요청을 받는 부분
    @PostMapping("user/login") // 로그인할때 데이터가 넘어오니까 post
    public String login(LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userSerive.login(requestDto, res);
        } catch (Exception e) {
            return "redirect:/api/user/login-page?error";
        }

        return "redirect:/";
    }

}