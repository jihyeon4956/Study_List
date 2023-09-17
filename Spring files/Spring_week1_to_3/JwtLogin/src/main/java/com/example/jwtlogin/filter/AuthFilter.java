package com.example.jwtlogin.filter;

import com.example.jwtlogin.entity.User;
import com.example.jwtlogin.jwt.JwtUtil;
import com.example.jwtlogin.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@Component
@RequiredArgsConstructor
@Order(1)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                (url.startsWith("/api/user/login") ||
                        url.startsWith("/api/user/signup") ||
                        url.startsWith("/css") ||
                        url.startsWith("/js"))) {
            log.info("인증처리를 하지 않는 URL: " + url);
            chain.doFilter(request, response);
        } else {
            // 나머지 API 요청은 인증 처리 진행
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            // 토큰이 존재하면 검증 시작
            if (StringUtils.hasText(tokenValue)) {
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                // getSubject() : 유저이름 가져오기
                User user = userRepository.findByUsername(info.getSubject()).orElseThrow(() ->
                        new NullPointerException("Not Found User")
                );

                request.setAttribute("user", user);
                chain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}