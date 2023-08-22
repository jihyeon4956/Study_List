package com.sparta.w1_springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody // html이 아니라 순수한 문자열로 반환하는것
    public String hello() {
        return "Hello World";  // 반환값을 templates에서 이 이름의 html이 있는지 찾아봄
    }
    @GetMapping("/get")
    @ResponseBody
    public String get() {
        return "Get Method 요청";
    }

    @PostMapping("/post")
    @ResponseBody
    public String post() {
        return "Post Method 요청";
    }
    @PutMapping("/put")
    @ResponseBody
    public String put() {
        return "Put Method 요청";
    }
    @DeleteMapping("/delete")
    @ResponseBody
    public String delete() {
        return "Delete Method 요청";
    }
}
