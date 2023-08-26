package com.sparta.springauth.controller;

import com.sparta.springauth.entity.User;
import com.sparta.springauth.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//JWT검증하고 유저정보 받아와서 검증하려고 만든 컨트롤러임
@Controller
@RequestMapping("/api")
public class ProductController {

    // 아래와 다른방식_더 편한버전
    @GetMapping("/products")
    public String getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // Authentication의 Principle 를 가져와서 사용함
        User user = userDetails.getUser();
        System.out.println("user.getUsername() = " + user.getUsername());
        System.out.println("user.getEmail() = " + user.getEmail());

        return "redirect:/";
    }

//    @GetMapping("/products")
//    public String getProducts(HttpServletRequest req) {
//        System.out.println("ProductController.getProducts : 인증 완료");
//        User user = (User) req.getAttribute("user");  // AuthFilter를 사용해서 거기서 넣어준 user를 가져와서 사용했음
//        System.out.println("user.getUsername() = " + user.getUsername());
//
//        return "redirect:/";
//    }
}
