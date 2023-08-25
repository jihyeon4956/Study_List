package com.sparta.springauth.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @GetMapping("/create-cookie")
    public String createCookie(HttpServletResponse res) {
        addCookie("Robbie Auth", res);

        return "createCookie";
    }

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue(AUTHORIZATION_HEADER) String value) {
        System.out.println("value = " + value);

        return "getCookie : " + value;
    }

    // 세션생성
    @GetMapping("/create-session")
    public String createSession(HttpServletRequest req) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 새로운 세션을 생성한 후 반환: true
        HttpSession session = req.getSession(true);

        // 세션에 저장될 정보 Name - Value 를 추가합니다.
        session.setAttribute(AUTHORIZATION_HEADER, "Robbie Auth");

        return "createSession";
    }

    // 세션 가져오기
    @GetMapping("/get-session")
    public String getSession(HttpServletRequest req) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 null 반환 : false
        HttpSession session = req.getSession(false);  // 요청리퀘스트에서 getSession메서드를 실행시킴
        // HttpSession 세션을 유일무이한 값으로 만들어줌

        String value = (String) session.getAttribute(AUTHORIZATION_HEADER); // 가져온 세션에 저장된 Value 를 Name 을 사용하여 가져옵니다.
        System.out.println("value = " + value);

        return "getSession : " + value;
    }



    // 쿠키를 저장하는 메소드
    public static void addCookie(String cookieValue, HttpServletResponse res) {
        // 일단 String cookieValue을 받아옴.
        // 서블릿에서 만들어준 Response 객체는 어떤 데이터를 담으면 클라이언트로 반환된다
        try {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8").replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 encoding 진행
            // 공백있으면 오류나서 .replaceAll("\\+", "%20"); -> 공백을 모두 바꾸겠다는 의미임 (공백 => %20)

            // 쿠키 만들기
            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, cookieValue); // Name-Value
            cookie.setPath("/");        // 적용되는 경로
            cookie.setMaxAge(30 * 60);  // 만료기한

            // Response 객체에 Cookie 추가
            res.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}