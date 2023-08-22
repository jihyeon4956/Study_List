package com.sparta.springmvc.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    private static long visitCount = 0;

    @GetMapping("/static-hello")
    public String hello() {
        return "hello.html";  // 반환타입이 String일때 return의 html로 찾아간다.
                              // 단, Controller에서 정의된 경우 empates에서 찾는다
                              // 주소창에 직접 8080/hello.html을 적던지 타임리프를 끄던지 (Controller이용 안함)
                              // "redirect:/hello.html" 을 이용한다. (Controller 이용함)
    }

    @GetMapping("/html/redirect")
    public String htmlStatic() {
        return "redirect:/hello.html";
    }

    @GetMapping("/html/templates")
    public String htmlTemplates() {
        return "hello";
    }

    @GetMapping("/html/dynamic")
    public String htmlDynamic(Model model) {  // 데이터를 모델에 넣어서 제공함
        visitCount++;
        model.addAttribute("visits", visitCount);
        return "hello-visit";
    }
}